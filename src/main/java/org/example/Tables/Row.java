package org.example.Tables;

import org.apache.poi.xwpf.usermodel.*;
import org.example.BinNumbers.SimpleBinNum;

import java.util.ArrayList;

public abstract class Row {
    protected int id;
    protected ArrayList<SimpleBinNum> rg1Number;
    protected ArrayList<SimpleBinNum> rg2Number;
    protected ArrayList<SimpleBinNum> rg3Number;
    protected String counter;
    protected String operations;

    public Row(int id, String counter, String operations) {
        this.id = id;
        this.counter = counter;
        this.operations = operations;
    }

    public int getId() {
        return id;
    }

    public ArrayList<SimpleBinNum> getRg1Number() {
        return rg1Number;
    }

    public ArrayList<SimpleBinNum> getRg2Number() {
        return rg2Number;
    }

    public ArrayList<SimpleBinNum> getRg3Number() {
        return rg3Number;
    }

    public String getCounter() {
        return counter;
    }

    public String getOperations() {
        return operations;
    }

    public XWPFTableRow getTableRow(XWPFTable table) {
        XWPFTableRow row = table.createRow();
        addCellWithNewLines(row, 0, String.valueOf(id));
        addCellWithNewLines(row, 1, getRg1AsString());
        addCellWithNewLines(row, 2, getRg2AsString());
        addCellWithNewLines(row, 3, getRg3AsString());
        if (!getCounter().isEmpty()) {
            addCellWithNewLines(row, 4, getCounter());
            addCellWithNewLines(row, 5, getOperations());
        } else addCellWithNewLines(row, 4, getOperations());
        return row;
    }

    public static void addCellWithNewLines(XWPFTableRow row, int cellIndex, String text) {
        XWPFTableCell cell;

        if (row.getCell(cellIndex) == null) {
            cell = row.createCell();
        } else {
            cell = row.getCell(cellIndex);
        }

        cell.removeParagraph(0);

        XWPFParagraph paragraph = cell.addParagraph();
        XWPFRun run = paragraph.createRun();

        String[] lines = text.split("\n");
        for (int i = 0; i < lines.length; i++) {
            run.setText(lines[i]);
            if (i < lines.length - 1) {
                run.addBreak();
            }
        }
    }

    abstract public String getRg1AsString();

    abstract public String getRg2AsString();

    abstract public String getRg3AsString();
}
