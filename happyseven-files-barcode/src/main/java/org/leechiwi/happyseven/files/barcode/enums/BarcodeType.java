package org.leechiwi.happyseven.files.barcode.enums;

public enum BarcodeType {
    NONE ((short)-1, "NONE"),
    CODABAR  ((short)0, "Codabar"),
    CODE_11  ((short)1, "Code11"),
    CODE_39_STANDARD  ((short)2, "Code39Standard"),
    CODE_39_EXTENDED  ((short)3, "Code39Extended"),
    CODE_93_STANDARD  ((short)4, "Code93Standard"),
    CODE_93_EXTENDED  ((short)5, "Code93Extended"),
    CODE_128  ((short)6, "Code128"),
    GS_1_CODE_128  ((short)7, "GS1Code128"),
    EAN_8  ((short)8, "EAN8"),
    EAN_13  ((short)9, "EAN13"),
    EAN_14  ((short)10, "EAN14"),
    SCC_14  ((short)11, "SCC14"),
    SSCC_18  ((short)12, "SSCC18"),
    UPCA  ((short)13, "UPCA"),
    UPCE  ((short)14, "UPCE"),
    ISBN  ((short)15, "ISBN"),
    STANDARD_2_OF_5  ((short)16, "Standard2of5"),
    INTERLEAVED_2_OF_5  ((short)17, "Interleaved2of5"),
    MATRIX_2_OF_5  ((short)18, "Matrix2of5"),
    ITALIAN_POST_25  ((short)19, "ItalianPost25"),
    IATA_2_OF_5  ((short)20, "IATA2of5"),
    ITF_14  ((short)21, "ITF14"),
    ITF_6  ((short)22, "ITF6"),
    MSI  ((short)23, "MSI"),
    VIN  ((short)24, "VIN"),
    DEUTSCHE_POST_IDENTCODE  ((short)25, "DeutschePostIdentcode"),
    DEUTSCHE_POST_LEITCODE  ((short)26, "DeutschePostLeitcode"),
    OPC  ((short)27, "OPC"),
    PZN  ((short)28, "PZN"),
    PHARMACODE  ((short)29, "Pharmacode"),
    DATA_MATRIX  ((short)30, "DataMatrix"),
    GS_1_DATA_MATRIX  ((short)31, "GS1DataMatrix"),
    QR  ((short)32, "QR"),
    AZTEC  ((short)33, "Aztec"),
    PDF_417  ((short)34, "Pdf417"),
    MACRO_PDF_417  ((short)35, "MacroPdf417"),
    MICRO_PDF_417  ((short)36, "MicroPdf417"),
    CODABLOCK_F  ((short)65, "CodablockF"),
    AUSTRALIA_POST  ((short)37, "AustraliaPost"),
    POSTNET  ((short)38, "Postnet"),
    PLANET  ((short)39, "Planet"),
    ONE_CODE  ((short)40, "OneCode"),
    RM_4_SCC  ((short)41, "RM4SCC"),
    MAILMARK  ((short)66, "Mailmark"),
    DATABAR_OMNI_DIRECTIONAL  ((short)42, "DatabarOmniDirectiona"),
    DATABAR_TRUNCATED  ((short)43, "DatabarTruncated"),
    DATABAR_LIMITED  ((short)44, "DatabarLimited"),
    DATABAR_EXPANDED  ((short)45, "DatabarExpanded"),
    DATABAR_STACKED_OMNI_DIRECTIONAL  ((short)53, "DatabarStacke"),
    DATABAR_STACKED  ((short)54, "DatabarStacked"),
    DATABAR_EXPANDED_STACKED  ((short)55, "DatabarExpandedStacke"),
    PATCH_CODE  ((short)46, "PatchCode"),
    ISSN  ((short)47, "ISSN"),
    ISMN  ((short)48, "ISMN"),
    SUPPLEMENT  ((short)49, "Supplement"),
    AUSTRALIAN_POSTE_PARCEL  ((short)50, "AustralianPosteParcel"),
    SWISS_POST_PARCEL  ((short)51, "SwissPostParcel"),
    CODE_16_K  ((short)52, "Code16K"),
    MICRO_QR  ((short)56, "MicroQR"),
    COMPACT_PDF_417  ((short)57, "CompactPdf417"),
    GS_1_QR  ((short)58, "GS1QR"),
    MAXI_CODE  ((short)59, "MaxiCode"),
    MICR_E_13_B  ((short)60, "MicrE13B"),
    CODE_32  ((short)61, "Code32"),
    DATA_LOGIC_2_OF_5  ((short)62, "DataLogic2of5"),
    DOT_CODE  ((short)63, "DotCode"),
    DUTCH_KIX  ((short)64, "DutchKIX");
    private String name;
    private short code;
    private BarcodeType(short code,String name){
     this.name=name;
     this.code=code;
    }
    public String getName() {
        return name;
    }

    public short getCode() {
        return code;
    }
}
