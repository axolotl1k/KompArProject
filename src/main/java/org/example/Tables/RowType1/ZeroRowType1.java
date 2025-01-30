package org.example.Tables.RowType1;

import org.example.BinNumbers.SimpleBinNum;

public class ZeroRowType1 extends MainRowType1{
    public ZeroRowType1(int id, SimpleBinNum rg1Number, SimpleBinNum rg2Number, SimpleBinNum rg3Number, String counter, String operations) {
        super(id, rg1Number, rg2Number, rg3Number, counter, operations);
    }

    @Override
    public String getRg1AsString() {
        return rg1Number.getFirst().toString();
    }

    @Override
    public String getRg2AsString() {
        return rg2Number.getFirst().toString();
    }
}
