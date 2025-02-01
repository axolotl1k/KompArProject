package org.example.Tables.RowType6;

import org.example.BinNumbers.SimpleBinNum;

import java.util.ArrayList;

public class ZeroRowType6 extends MainRowType6{
    public ZeroRowType6(int id, SimpleBinNum rg3Number, SimpleBinNum rg2Number, ArrayList<SimpleBinNum> rg1Number, String operations) {
        super(id, rg3Number, rg2Number, rg1Number, operations);
    }

    @Override
    public String getRg1AsString() {
        return rg1Number.getFirst().toString() + "\n" + rg1Number.getLast().toString();
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
