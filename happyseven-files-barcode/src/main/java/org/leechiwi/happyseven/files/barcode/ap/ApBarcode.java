package org.leechiwi.happyseven.files.barcode.ap;

import com.aspose.barcode.BaseEncodeType;
import com.aspose.barcode.barcoderecognition.BarCodeReader;
import com.aspose.barcode.barcoderecognition.BarCodeResult;
import com.aspose.barcode.barcoderecognition.SingleDecodeType;
import com.aspose.barcode.generation.BarcodeGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.leechiwi.happyseven.files.barcode.Barcode;
import org.leechiwi.happyseven.files.barcode.ap.encodetype.CustomEncodeType;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApBarcodeClassificationsFactory;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApBarcodeTypeFactory;
import org.leechiwi.happyseven.files.barcode.ap.factory.ApImageFormatFactory;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeClassifications;
import org.leechiwi.happyseven.files.barcode.enums.BarcodeType;
import org.leechiwi.happyseven.files.base.enums.ImageFormat;
import org.leechiwi.happyseven.files.base.read.ImageRead;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@Slf4j
public class ApBarcode extends BarcodeLicense implements Barcode {
    private BarcodeType barcodeType;
    public ApBarcode(BarcodeType barcodeType){
        this.barcodeType=barcodeType;
    }
    @Override
    public List<String> getBarcodeText(Object image) {
        List<String> result = new ArrayList<>();
        ApBarcodeTypeFactory factory=new ApBarcodeTypeFactory();
        SingleDecodeType singleDecodeType = factory.convertBarcode(this.barcodeType);
        BarCodeResult[] barCodeResults = getBarCode(image, singleDecodeType);
        if(Objects.isNull(barCodeResults)||barCodeResults.length==0){
            return result;
        }
        for (BarCodeResult barCodeResult : barCodeResults) {
            result.add(barCodeResult.getCodeText());
        }
        return result;
    }
    public String getSingleBarcodeText(Object image){
        List<String> barcodeTextList = getBarcodeText(image);
        if(CollectionUtils.isEmpty(barcodeTextList)){
            return StringUtils.EMPTY;
        }
        return barcodeTextList.get(0);
    }
    private  BarCodeResult[] getBarCode(Object image, SingleDecodeType codeType){
        BarCodeResult[] result=null;
        BufferedImage bufferedImage = new ImageRead().loadImage(image);
        if(Objects.isNull(bufferedImage)){
            return result;
        }
        BarCodeReader reader = new BarCodeReader(bufferedImage,codeType);
        result = reader.readBarCodes();
        return result;
    }

    @Override
    public List<String> getBarcodeTextAsync(ThreadPoolExecutor pool, List<Object> imageList, int thread) {
        List<String> resultList = new ArrayList<>();
        List<Future<List<String>>> futures = new ArrayList<>();
        Map<String, Object> groupResult = group(imageList.size(), thread);
        int[] group = (int[]) groupResult.get("group");
        int trueThread = (int) groupResult.get("realThread");
        ExecutorService executorService=pool;
        boolean defaultPool=false;
        if (Objects.isNull(pool)) {
            executorService= Executors.newFixedThreadPool(trueThread);
            defaultPool=true;
        }
        int index=0;
        for (int i=0;i<trueThread;i++) {
            final List<Object> lst=imageList.subList(index,index+group[i]);
            index+=group[i];
            Future<List<String>> future = executorService.submit(() -> {
                List<String> list = new ArrayList<>();
                for (Object o : lst) {
                    List<String> codeTexts = this.getBarcodeText(o);
                    list.addAll(codeTexts);
                }
                return list;
            });
            futures.add(future);
        }
        for (Future<List<String>> future : futures) {
            List<String> result=null;
            try {
                result= future.get();
            }catch (Exception e){
                resultList.add(StringUtils.EMPTY);
                log.debug("异常", e);// sonar
                Thread.currentThread().interrupt();
            }
            resultList.addAll(result);
        }
        if(defaultPool) {
            executorService.shutdown();
        }
        return resultList;
    }

    private  Map<String,Object> group(int size, int num){
        Map<String,Object> resultMap=new HashMap<>();
        int realThread=0;
        int[] result = new int[num];
        int per=size/num;
        int modIndex=0;
        int mod=size%num;
        for (int i = 0; i < num; i++) {
            if(size>0){
                realThread++;
                if (modIndex < mod) {
                    modIndex++;
                    result[i]=per + 1;
                } else {
                    result[i]=per;
                }
                size-=result[i];
            }else{
                break;
            }
        }
        resultMap.put("group",result);
        resultMap.put("realThread",realThread);
        return resultMap;
    }

    public BufferedImage CreateBarcode(String text, OutputStream out, BarcodeClassifications barcodeClassifications, ImageFormat imageFormat){
        BufferedImage bufferedImage=null;
        SingleDecodeType singleDecodeType = new ApBarcodeTypeFactory().convertBarcode(this.barcodeType);
        BaseEncodeType baseEncodeType=new CustomEncodeType(singleDecodeType.getTypeIndex(),singleDecodeType.getTypeName(),new ApBarcodeClassificationsFactory().convertBarcodeClassifications(barcodeClassifications));
        BarcodeGenerator barcodeGenerator=new BarcodeGenerator(baseEncodeType, text);
        try {
            bufferedImage = barcodeGenerator.generateBarCodeImage();
            if(Objects.nonNull(out)) {
                com.aspose.barcode.BarCodeImageFormat apBarCodeImageFormat = new ApImageFormatFactory().convertBarCodeImageFormat(imageFormat);
                barcodeGenerator.save(out, apBarCodeImageFormat);
            }
        } catch (IOException e) {
            log.error("CreateBarcode error",e);
        }
        return bufferedImage;
    }
    public void CreateBarcode(String text,OutputStream out){
        CreateBarcode(text,out, BarcodeClassifications.NONE, ImageFormat.JPEG);
    }
}
