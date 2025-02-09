package org.example.Tables;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import java.util.ArrayList;

public class Table {
    private ArrayList<String> header;
    private ArrayList<Row> tableRows;

    public Table(ArrayList<String> header) {
        this.header = header;
        this.tableRows = new ArrayList<>();
    }

    public void addRow(Row row) {
        tableRows.add(row);
    }

    public Row getRow(int index) {
        return tableRows.get(index);
    }

    public XWPFTable createWordTable(XWPFDocument doc) {
        XWPFTable table = doc.createTable();
        XWPFTableRow headerRow = table.getRow(0);
        int shiftSize = 0;
        for (int i = 0; i < header.size(); i++) {
            if (!header.get(i).isEmpty()) {
                if (headerRow.getCell(i - shiftSize) == null) headerRow.createCell();
                Row.addCellWithNewLines(headerRow, i - shiftSize, header.get(i));
            } else {
                shiftSize++;
            }
        }
        for (Row mainRow : tableRows) {
            mainRow.getTableRow(table);
        }
        //table.removeRow(0);
        return table;
    }
}
