package org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.proxy;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.CellElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.PdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation.PdfField;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.TablePdfTemplate;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
public class TablePdfTemplateProxy implements PdfTemplate {
    private TablePdfTemplate pdfTemplate;

    public TablePdfTemplateProxy(InputStream inputStream, PdfTemplateElement pdfTemplateElement) {
        this.pdfTemplate = new TablePdfTemplate(inputStream, pdfTemplateElement);
    }

    private void beanToTableCell(TablePdfTemplate pdfTemplate) {
        PdfTemplateElement pdfTemplateElement = pdfTemplate.getPdfTemplateElement();
        Object object = pdfTemplateElement.getObject();
        if (Objects.nonNull(object)) {
            List list=null;
            Class clazz=null;
            if(object instanceof List){
                list=((List)object);
                //Type genericSuperclass = list.getClass().getGenericSuperclass();
                //clazz=(Class)(((ParameterizedType)genericSuperclass).getActualTypeArguments()[0]);
                if(CollectionUtils.isNotEmpty(list)){
                    clazz=list.get(0).getClass();
                }else{
                    throw new HappysevenException("pdfTemplateElement's property [object] can't be emptyed");
                }
            }else{
                list=Collections.singletonList(object);
                clazz=object.getClass();
            }
            List<CellElement> cellElementList = parseAnnotation(list,clazz);
            List<List<CellElement>> result = prepareCell(pdfTemplateElement.getColumnWidth().length, pdfTemplate.getColLeftOfRow(), cellElementList);
            pdfTemplateElement.setTableCellList(result);
        }
    }

    private List<Field> getSortedField(Class clazz) {
        /*if(Objects.isNull(clazz)){
            clazz=t.getClass();
        }*/
        Field[] declaredFields = clazz.getDeclaredFields();
        Function<Field, Integer> compareFunction = f -> {
            f.setAccessible(true);
            return f.getAnnotation(PdfField.class).order();
        };
        List<Field> sortedField = Arrays.asList(declaredFields).stream()
                .filter(f -> f.isAnnotationPresent(PdfField.class))
                .sorted(Comparator.comparing(compareFunction))
                .collect(Collectors.toList());
        return sortedField;
    }

    private List<List<CellElement>> prepareCell(int columnWidth, Map<Integer, Integer> colLeftOfRow, List<CellElement> cellElementList) {
        List<List<CellElement>> cellElementResultList = new ArrayList<>();
        /////List<Field> sortedField = getSortedField(t);
       /* while (rowIndex <=colLeftOfRow.size()) {
            List<Field> collect = sortedField.stream().skip(rowIndex - 1).limit(colLeftOfRow.get(rowIndex)).collect(Collectors.toList());
        }*/
        int currentColIndexOfRow = 0;
        int rowIndex = 1;
        List<CellElement> celllst = new ArrayList<>();
        for (CellElement cellElement : cellElementList) {
            Integer colNumOfOneRow = Optional.ofNullable(colLeftOfRow.get(rowIndex)).orElse(columnWidth);
           /* CellElement cellElement = null;
            PdfField pdfField = declaredField.getAnnotation(PdfField.class);
            Object o = null;
            try {
                o = declaredField.get(t);
            } catch (IllegalAccessException e) {
                log.error("PdfField get value fail", e);
            }
            if (pdfField.isRelBean()) {
                parseAnnotation(o);
            }
            String valueOfString = getObjectVaule(o, pdfField);
            if (StringUtils.isNotBlank(pdfField.label())) {
                cellElement = getCellElement(pdfField.label(), pdfField);
                celllst.add(cellElement);
                currentColIndexOfRow++;
            }
            cellElement = getCellElement(valueOfString, pdfField);*/
            celllst.add(cellElement);
            currentColIndexOfRow++;
            if (currentColIndexOfRow == colNumOfOneRow) {
                rowIndex++;
                currentColIndexOfRow = 0;
                cellElementResultList.add(celllst);
                celllst = new ArrayList<>();
            }
        }
        return cellElementResultList;
    }

    private List<CellElement> parseAnnotation(List tList,Class clazz) {
        List<CellElement> result = new ArrayList<>();
        List<Field> sortedField = getSortedField(clazz);
        for (Object t : tList) {
            for (Field declaredField : sortedField) {
                CellElement cellElement = null;
                PdfField pdfField = declaredField.getAnnotation(PdfField.class);
                Class<?> type = declaredField.getType();
                Object object = null;
                try {
                    if(Objects.nonNull(t)) {
                        object = declaredField.get(t);
                    }
                } catch (IllegalAccessException e) {
                    log.error("PdfField get value fail", e);
                }
                if(Objects.isNull(object)){
                    if(!pdfField.nullLoaded()){
                        continue;
                    }
                }
                if (isRelType(type,pdfField)) {
                    List objects = Collections.singletonList(object);
                    result.addAll(parseAnnotation(objects,type));
                    continue;
                }
                if(type.isAssignableFrom(List.class)){
                    //此处获取list的泛型类型
                    Class genericClazz=(Class)(((ParameterizedType)declaredField.getGenericType()).getActualTypeArguments()[0]);
                    List list=null;
                    if(Objects.isNull(object)){
                        list=new ArrayList();
                        list.add(object);
                    }else{
                        list=(List)object;
                    }
                    result.addAll(parseAnnotation(list,genericClazz));
                    continue;
                }
                String valueOfString = getObjectVaule(object, pdfField);
                if (StringUtils.isNotBlank(pdfField.label())) {
                    cellElement = getCellElement(pdfField.label(), pdfField);
                    result.add(cellElement);
                }
                cellElement = getCellElement(valueOfString, pdfField);
                result.add(cellElement);
            }
        }
        return result;
    }

    private CellElement getCellElement(String text, PdfField pdfField) {
        CellElement cellElement = new CellElement();
        cellElement.setText(text);
        Font font = this.getFont(pdfField);
        cellElement.setFont(font);
        cellElement.setBorderSide(pdfField.borderSide());
        cellElement.setColumnHeight(pdfField.columnHeight());
        cellElement.setVerticalAlign(pdfField.verticalAlign());
        cellElement.setHorizontalAlign(pdfField.horizontalAlign());
        cellElement.setPrefix(pdfField.prefix());
        return cellElement;
    }

    private Font getFont(PdfField pdfField) {
        BaseFont baseFont = null;
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        float fontSize = pdfField.fontSize();
        int fontType = pdfField.fontStype();
        return new Font(baseFont, fontSize, fontType);
    }

    private String getObjectVaule(Object o, PdfField pdfField) {
        String result = StringUtils.EMPTY;
        if (Objects.isNull(o)) {
            return result;
        }
        if (PdfImageType.getUsedTypeSet().contains(pdfField.imageType())) {
            return pdfField.imageType().imageToString(o);
        }
        if (o instanceof Number) {
            return o.toString();
        } else if (o instanceof Date) {
            String formatStr = pdfField.dateFormat();
            return DateFormatUtils.format((Date) o, formatStr);
        } else if (o instanceof String) {
            result = o.toString();
        }else{
            result = o+"";
        }
        return result;
    }
    private boolean isRelType(Class clazz, PdfField pdfField){
        return !(PdfImageType.getUsedTypeSet().contains(pdfField.imageType()))&&
                !(clazz.isAssignableFrom(Integer.class))&&
                !(clazz.isAssignableFrom(Short.class))&&
                !(clazz.isAssignableFrom(Double.class))&&
                !(clazz.isAssignableFrom(Long.class))&&
                !(clazz.isAssignableFrom(Float.class))&&
                !(clazz.isAssignableFrom(Boolean.class))&&
                !(clazz.isAssignableFrom(String.class))&&
                !(clazz.isAssignableFrom(Date.class))&&
                !(clazz.isAssignableFrom(List.class));
    }
    @Override
    public boolean create(OutputStream out) {
        beanToTableCell(this.pdfTemplate);
        return pdfTemplate.create(out);
    }
}
