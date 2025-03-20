package org.example.LabBuilders;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

public abstract class LabBuilder {
    protected int variant;

    public LabBuilder(int variant) {
        this.variant = variant;
    }

    public void setVariant(int variant) {
        this.variant = variant;
    }

    public abstract XWPFDocument buildDocument(XWPFDocument document);
}
