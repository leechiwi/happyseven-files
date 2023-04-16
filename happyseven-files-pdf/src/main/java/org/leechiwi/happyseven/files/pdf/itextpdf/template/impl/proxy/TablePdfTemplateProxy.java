package org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.proxy;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.leechiwi.happyseven.files.pdf.itextpdf.model.PdfTemplateElement;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.PdfTemplate;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.annotation.PdfField;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.enums.PdfImageType;
import org.leechiwi.happyseven.files.pdf.itextpdf.template.impl.TablePdfTemplate;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
@Slf4j
public class TablePdfTemplateProxy implements PdfTemplate {
    private TablePdfTemplate pdfTemplate;

    public TablePdfTemplateProxy(InputStream inputStream, PdfTemplateElement pdfTemplateElement) {
        this.pdfTemplate = new TablePdfTemplate(inputStream, pdfTemplateElement);
    }

    private void beanToData(TablePdfTemplate pdfTemplate) {
        PdfTemplateElement pdfTemplateElement=pdfTemplate.getPdfTemplateElement();
        Object object = pdfTemplateElement.getObject();
        if (Objects.nonNull(object)) {
            List<List<String>> tableDataList = parseAnnotation(pdfTemplateElement.getColumnWidth().length,pdfTemplate.getColLeftOfRow(), object);
            pdfTemplateElement.setTableDataList(tableDataList);
        }
    }

    private List<List<String>> parseAnnotation(int columnWidth,Map<Integer, Integer> colLeftOfRow, Object t) {
        List<List<String>> result = new ArrayList<>();
        Field[] declaredFields = t.getClass().getDeclaredFields();
        Function<Field, Integer> compareFunction = f -> {
            f.setAccessible(true);
            return f.getAnnotation(PdfField.class).order();
        };
        List<Field> sortedField = Arrays.asList(declaredFields).stream()
                .filter(f -> f.isAnnotationPresent(PdfField.class))
                .sorted(Comparator.comparing(compareFunction))
                .collect(Collectors.toList());
       /* while (rowIndex <=colLeftOfRow.size()) {
            List<Field> collect = sortedField.stream().skip(rowIndex - 1).limit(colLeftOfRow.get(rowIndex)).collect(Collectors.toList());
        }*/
        int currentIndex = 0;
        int rowIndex = 1;
        List<String> lst = new ArrayList<>();
        for (Field declaredField : sortedField) {
            Integer numOfOneRow = Optional.ofNullable(colLeftOfRow.get(rowIndex)).orElse(columnWidth);
            PdfField pdfField = declaredField.getAnnotation(PdfField.class);
            Object o = null;
            try {
                o = declaredField.get(t);
            } catch (IllegalAccessException e) {
                log.error("PdfField get value fail",e);
            }
            String valueOfString = getObjectVaule(o, pdfField);
            if (StringUtils.isNotBlank(pdfField.label())) {
                lst.add(pdfField.label());
                currentIndex++;
            }
            lst.add(valueOfString);
            currentIndex++;
            if (currentIndex == numOfOneRow) {
                rowIndex++;
                currentIndex = 0;
                result.add(lst);
                lst = new ArrayList<>();
            }
        }
        return result;
    }

    private String getObjectVaule(Object o, PdfField pdfField) {
        String result = StringUtils.EMPTY;
        if (Objects.isNull(o)) {
            return result;
        }
        if(PdfImageType.getUsedTypeSet().contains(pdfField.imageType())){
            return pdfField.imageType().imageToString(o);
        }
        if (o instanceof Integer) {
            return o.toString();
        } else if (o instanceof Date) {
            String formatStr = pdfField.dateFormat();
            return DateFormatUtils.format((Date) o, formatStr);
        } else if (o instanceof String) {
            result = o.toString();
        }
        return result;
    }
    @Override
    public boolean create(OutputStream out) {
        beanToData(this.pdfTemplate);
        return pdfTemplate.create(out);
    }
}
