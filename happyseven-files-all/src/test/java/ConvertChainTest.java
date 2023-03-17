import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.leechiwi.happyseven.files.all.convert.handlers.ConvertChainHandler;
import org.leechiwi.happyseven.files.all.convert.handlers.impl.ImagesToBarcodeHandler;
import org.leechiwi.happyseven.files.all.convert.handlers.impl.PdfInnerImagesHandler;
import org.leechiwi.happyseven.files.all.convert.handlers.impl.PdfToAnyHandler;
import org.leechiwi.happyseven.files.all.convert.handlers.impl.SlideToPdfHandler;
import org.leechiwi.happyseven.files.all.convert.ConvertChain;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.base.enums.ResultOptions;
import org.leechiwi.happyseven.files.pdf.enums.PdfConvertType;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConvertChainTest {
    @Test
    public void sileToImagesConvertChain() {
        OutputStream out = null;
        try {
            out = new FileOutputStream(new java.io.File("d:/test.zip"));
            List<ConvertChainHandler<PdfConvertType>> collect = Stream.of(new SlideToPdfHandler(), new PdfToAnyHandler()).collect(Collectors.toList());
            ConvertChain<PdfConvertType> convertChain = new ConvertChain("d:/test.pptx", ResultOptions.ALL_IN_ZIP, collect);
            boolean result = convertChain.convertAll(out, PdfConvertType.JPEG, new OptionResult());
            System.out.println("result=" + result);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    @Test
    public void pdfToBarcodeConvertChain() {
        OutputStream out = null;
        List<ConvertChainHandler> collect = Stream.of(new PdfInnerImagesHandler(), new ImagesToBarcodeHandler()).collect(Collectors.toList());
        ConvertChain convertChain = new ConvertChain("d:/test.pdf", ResultOptions.MANY, collect);
        OptionResult optionResult = new OptionResult();
        boolean result = convertChain.convertAll(out, PdfConvertType.JPEG, optionResult);
        System.out.println("result=" + result);
        if(result){
            List<String> list = (List<String>)optionResult.getList();
            for (String s : list) {
                System.out.println(s);
            }
        }
    }

}
