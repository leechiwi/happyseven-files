package org.leechiwi.happyseven.files.base.read;

import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
@Slf4j
public class ImageRead {
    public BufferedImage loadImage(Object file){
        BufferedImage image=null;
        try {
            if (file instanceof BufferedImage) {
                image = (BufferedImage) file;
            } else {
                if (file instanceof String) {
                    file = new File((String) file);
                }else if(file instanceof byte[]){
                    file=new ByteArrayInputStream((byte[])file);
                }
                if (file instanceof InputStream) {
                    image = ImageIO.read((InputStream) file);
                } else if (file instanceof File) {
                    image = ImageIO.read((File) file);
                }
            }
        }catch(IOException e){
            log.error("loadImage error",e);
        }
        return image;
    }
}
