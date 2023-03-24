package org.leechiwi.happyseven.files.base.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.freemarker.enums.TemplateLoadingType;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class FreemarkerDocuments {
    private static final String STR_UTF8 = "utf-8";
    private TemplateLoadingType templateLoadingType;

    public FreemarkerDocuments(TemplateLoadingType templateLoadingType) {
        this.templateLoadingType = templateLoadingType;
    }

    public boolean create(Object dataModel, String filePath, String fileName, OutputStream out) {
        boolean result=true;
        try {
            Configuration configuration = new Configuration(Configuration.VERSION_2_3_22);
            configuration.setDefaultEncoding(STR_UTF8);
            templateLoadingType.setTemplate(configuration, filePath);
            Writer writer = new BufferedWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
            Template template = configuration.getTemplate(fileName, STR_UTF8);
            template.process(dataModel, writer);
            writer.flush();
        } catch (IOException e) {
            log.error("", e);
            result=false;
        } catch (TemplateException e) {
            log.error("", e);
            result=false;
        }
        return result;
    }

    public boolean create(Object dataModel, String filePath, String fileName, File file) {
        boolean result = true;
        try (FileOutputStream out = new FileOutputStream(file)) {
            result=this.create(dataModel, filePath, fileName, out);
        } catch (IOException e) {
            log.error("", e);
            return false;
        }
        return result;
    }
    public boolean create(Object dataModel, String filePath, String fileName, String path) {
           return  this.create(dataModel, filePath, fileName,new File(path));
    }
    public byte[] createByteArray(Object dataModel, String filePath, String fileName) {
        byte[] result = null;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            if(this.create(dataModel, filePath, fileName, out)){
                result=out.toByteArray();
            }
        } catch (IOException e) {
            log.error("", e);
        }
        return result;
    }
    public InputStream createInputStream(Object dataModel, String filePath, String fileName) {
        InputStream result=null;
        byte[] bytes=createByteArray(dataModel, filePath, fileName);
        if(Objects.nonNull(bytes)){
            result = new ByteArrayInputStream(bytes);
        }
        return result;
    }
}
