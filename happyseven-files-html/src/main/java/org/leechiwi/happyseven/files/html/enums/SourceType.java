package org.leechiwi.happyseven.files.html.enums;

public enum SourceType {
    EPUB(".epub"),
    HTML(".html"),
    MHTML(".mht"),
    SVG(".svg"),
    MARKDOWN(".md");
    private String ext;
    private SourceType(String ext) {
        this.ext = ext;
    }
    public String getExt() {
        return this.ext;
    }
}
