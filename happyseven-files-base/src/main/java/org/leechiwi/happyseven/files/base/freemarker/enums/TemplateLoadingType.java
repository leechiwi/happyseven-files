package org.leechiwi.happyseven.files.base.freemarker.enums;

import freemarker.template.Configuration;
import lombok.extern.slf4j.Slf4j;
import org.leechiwi.happyseven.files.base.freemarker.FreemarkerDocuments;

import java.io.File;
import java.io.IOException;
@Slf4j
public enum TemplateLoadingType {
    CLAZZ{
        public  void setTemplate(Configuration configuration,String path){
            configuration.setClassForTemplateLoading(FreemarkerDocuments.class, path);
        }
    },
    DIRECTORY{
        public  void setTemplate(Configuration configuration,String path){
            try {
                configuration.setDirectoryForTemplateLoading(new File(path));
            } catch (IOException e) {
                log.error("dir error,not found", e);
            }
        }
    };
    public abstract void setTemplate(Configuration configuration,String path);
}
