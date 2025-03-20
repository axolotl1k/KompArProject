package org.example.LabBuilders;

import org.apache.poi.xwpf.usermodel.*;

public class MyParagraphBuilder {
    private XWPFDocument document;
    private XWPFParagraph paragraph;
    private XWPFRun run;

    public MyParagraphBuilder(XWPFDocument document) {
        this.document = document;
        paragraph = document.createParagraph();
        run = paragraph.createRun();
    }

    public MyParagraphBuilder createCellParagraphAndRun(XWPFTableCell cell) {
        paragraph = cell.getParagraphs().getFirst();
        run = paragraph.createRun();
        return this;
    }

    public MyParagraphBuilder createNewParagraph() {
        paragraph = document.createParagraph();
        return this;
    }

    public MyParagraphBuilder createNewRun() {
        run = paragraph.createRun();
        return this;
    }

    public MyParagraphBuilder createNewParagraphAndRun() {
        paragraph = document.createParagraph();
        run = paragraph.createRun();
        return this;
    }

    public MyParagraphBuilder setText(String text) {
        run.setText(text);
        return this;
    }

    public MyParagraphBuilder setFont(String family) {
        run.setFontFamily(family);
        return this;
    }

    public MyParagraphBuilder setFontDefolt() {
        return setFont("Times New Roman");
    }

    public MyParagraphBuilder setFontMonospaced() {
        return setFont("Calibri");
    }

    public MyParagraphBuilder setFontSize(float size) {
        run.setFontSize(size);
        return this;
    }

    public MyParagraphBuilder setFontBold(boolean bold) {
        run.setBold(bold);
        return this;
    }

    public MyParagraphBuilder setFontItalic(boolean italic) {
        run.setItalic(italic);
        return this;
    }

    public MyParagraphBuilder setAlignment(ParagraphAlignment alignment) {
        paragraph.setAlignment(alignment);
        return this;
    }
}
