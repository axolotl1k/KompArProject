package org.example.Tables;

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
}
