package org.leechiwi.happyseven.files.html.ap.convert;

import com.aspose.html.rendering.image.ImageFormat;
import com.aspose.html.saving.*;
import org.leechiwi.happyseven.files.base.exception.HappysevenException;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;

public class SaveOptions {
    public  static void accept(AbstractApHtmlConvert apHtmlConvert,HtmlConvertType htmlConvertType) throws HappysevenException {
        switch (htmlConvertType) {
            case PDF:
                apHtmlConvert.convertTo(new PdfSaveOptions());
                break;
            case JPEG:
                apHtmlConvert.convertTo(new ImageSaveOptions(ImageFormat.Jpeg));
                break;
            case BMP:
                apHtmlConvert.convertTo(new ImageSaveOptions(ImageFormat.Bmp));
                break;
            case TIFF:
                apHtmlConvert.convertTo( new ImageSaveOptions(ImageFormat.Tiff));
                break;
            case GIF:
                apHtmlConvert.convertTo( new ImageSaveOptions(ImageFormat.Gif));
                break;
            case PNG:
                apHtmlConvert.convertTo( new ImageSaveOptions(ImageFormat.Png));
                break;
            case MHTML:
                apHtmlConvert.convertTo( new MHTMLSaveOptions());
            case MARKDOWN:
                apHtmlConvert.convertTo( new MarkdownSaveOptions());
                break;
            case XPS:
                apHtmlConvert.convertTo( new XpsSaveOptions());
                break;
            case HTML:
                apHtmlConvert.convertTo(htmlConvertType);
                break;
            default:
                throw new HappysevenException("unsupport htmlsource");
        }
    }
}
