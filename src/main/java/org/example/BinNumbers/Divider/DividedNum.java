package org.example.BinNumbers.Divider;

import org.example.BinNumbers.BinNum;
import org.example.Tables.Table;

public class DividedNum {
    private BinNum number;
    private Table divideTable;

    public DividedNum(BinNum number, Table divideTable) {
        this.number = number;
        this.divideTable = divideTable;
    }

    public BinNum getNumber() {
        return number;
    }

    public Table getDivideTable() {
        return divideTable;
    }
}
