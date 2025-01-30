package org.example.Tables.RowType3;

import org.example.BinNumbers.SimpleBinNum;

public class ZeroRowType3 extends MainRowType3{
    public ZeroRowType3(int id, SimpleBinNum rg2Number, SimpleBinNum rg1Number, SimpleBinNum rg3Number, String counter, String operations) {
        super(id, rg2Number, rg1Number, rg3Number, counter, operations);
    }

    @Override
    public String getRg1AsString() {
        return rg1Number.getFirst().toString();
    }

    @Override
    public String getRg2AsString() {
        return rg2Number.getFirst().toString();
    }

    @Override
    public String getRg3AsString() {
        return rg3Number.getFirst().toString();
    }
}
