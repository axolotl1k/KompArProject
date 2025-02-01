package org.example.Tables.RowType6;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;

import java.util.ArrayList;

public class MainRowType6 extends Row {
    public MainRowType6(int id, SimpleBinNum rg3Number, ArrayList<SimpleBinNum> rg2Number, ArrayList<SimpleBinNum> rg1Number, String operations) {
        super(id, "", operations);
        this.rg2Number = rg2Number;
        this.rg1Number = rg1Number;
        ArrayList<SimpleBinNum> rg3List = new ArrayList<>();
        rg3List.add(rg3Number);
        this.rg3Number = rg3List;
    }

    public MainRowType6(int id, SimpleBinNum rg3Number,SimpleBinNum rg2Number, ArrayList<SimpleBinNum> rg1Number, String operations) {
        super(id, "", operations);
        this.rg1Number = rg1Number;
        ArrayList<SimpleBinNum> rg2List = new ArrayList<>();
        rg2List.add(rg2Number);
        this.rg2Number = rg2List;
        ArrayList<SimpleBinNum> rg3List = new ArrayList<>();
        rg3List.add(rg3Number);
        this.rg3Number = rg3List;
    }

    @Override
    public String getRg2AsString() {
        if (rg2Number.size() == 1) {
            return rg2Number.getFirst().toString();
        }
        return rg2Number.get(0).toString() + "\n+" + rg2Number.get(1).toString() + "\n=" + rg2Number.get(2).getCarryBit() + rg2Number.get(2).toString();
    }

    @Override
    public String getRg1AsString() {
        return ">>" + rg1Number.get(0).toString() + "\n>>" + rg1Number.get(1).toString();
    }

    @Override
    public String getRg3AsString() {
        return ">>" + rg3Number.getFirst().toString();
    }
}
