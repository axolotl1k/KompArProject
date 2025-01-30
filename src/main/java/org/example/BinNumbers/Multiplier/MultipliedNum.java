package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.BinNum;
import org.example.Tables.Table;

public class MultipliedNum {
    private BinNum number;
    private Table multiplyTable;

    public MultipliedNum(BinNum number, Table multiplyTable) {
        this.number = number;
        this.multiplyTable = multiplyTable;
    }

    public BinNum getNumber() {
        return number;
    }

    public Table getMultiplyTable() {
        return multiplyTable;
    }
}
