import org.junit.Test;
import org.leechiwi.happyseven.files.base.entity.OptionResult;
import org.leechiwi.happyseven.files.html.ap.ApHtml;
import org.leechiwi.happyseven.files.html.enums.HtmlConvertType;
import org.leechiwi.happyseven.files.html.enums.SourceType;

public class ApHtmlConvertTest {
    @Test
    public void convert() {
        boolean convert = new ApHtml("d:/test.html", SourceType.HTML).convertAll("d:/test.pdf", HtmlConvertType.PDF, new OptionResult());
        System.out.println("result=" + convert);
    }
}
