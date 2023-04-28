package org.leechiwi.happyseven.files.pdf.itextpdf.model;

import com.itextpdf.text.Font;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellElement {
    private String text;

    private Font font;

    private int borderSide;

    private float columnHeight;

    private int verticalAlign;

    private int horizontalAlign;

    private String prefix;
}
