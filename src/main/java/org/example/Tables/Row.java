package org.example.Tables;

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

    abstract public String getRg1AsString();

    abstract public String getRg2AsString();

    abstract public String getRg3AsString();
}
