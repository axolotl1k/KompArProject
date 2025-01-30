package org.example.Tables.RowType3;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;

import java.util.ArrayList;

public class MainRowType3 extends Row {
    public MainRowType3(int id, ArrayList<SimpleBinNum> rg1Number, ArrayList<SimpleBinNum> rg2Number, SimpleBinNum rg3Number, String counter, String operations) {
        super(id, counter, operations);
        this.rg1Number = rg1Number;
        this.rg2Number = rg2Number;
        ArrayList<SimpleBinNum> rg3List = new ArrayList<>();
        rg3List.add(rg3Number);
        this.rg3Number = rg3List;
    }

    public MainRowType3(int id, SimpleBinNum rg1Number, SimpleBinNum rg2Number, SimpleBinNum rg3Number, String counter, String operations) {
        super(id, counter, operations);
        ArrayList<SimpleBinNum> rg1List = new ArrayList<>();
        rg1List.add(rg1Number);
        this.rg1Number = rg1List;
        ArrayList<SimpleBinNum> rg2List = new ArrayList<>();
        rg2List.add(rg2Number);
        this.rg2Number = rg2List;
        ArrayList<SimpleBinNum> rg3List = new ArrayList<>();
        rg3List.add(rg3Number);
        this.rg3Number = rg3List;
    }

    @Override
    public String getRg1AsString() {
        if (rg1Number.size() == 2) {
            return rg1Number.get(0).toString() + "\n<<" + rg1Number.get(1).toString();
        }
        return rg1Number.get(0).toString() + "\n+" + rg1Number.get(1).toString() + "\n=" + rg1Number.get(2).toString() + "\n<<" + rg1Number.get(3).toString();
    }

    @Override
    public String getRg2AsString() {
        if (rg2Number.size() == 2) {
            return rg2Number.get(0).toString() + "\n<<" + rg2Number.get(1).toString();
        }
        return rg1Number.get(0).toString() + "\n+" + rg2Number.get(1).toString() + "\n=" + rg2Number.get(2).getNumber().getFirst() + "|" + rg2Number.getFirst().toString() + "\n<<" + rg2Number.get(3).toString();

    }

    @Override
    public String getRg3AsString() {
        return rg3Number.getFirst().toString();
    }
}
