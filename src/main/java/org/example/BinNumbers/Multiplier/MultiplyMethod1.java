package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;
import org.example.Tables.RowType1.MainRowType1;
import org.example.Tables.RowType1.ZeroRowType1;
import org.example.Tables.Table;

import java.util.ArrayList;

public class MultiplyMethod1 implements MultiplyStrategy {
    @Override
    public MultipliedNum multiply(SimpleBinNum a, SimpleBinNum b) {
        ArrayList<Integer> firstOperand = new ArrayList<>(a.getNumber());
        ArrayList<Integer> secondOperand = new ArrayList<>(b.getNumber());
        int maxLength = Math.max(firstOperand.size(), secondOperand.size());
        while (firstOperand.size() < maxLength) {
            firstOperand.addFirst(0);
        }
        while (secondOperand.size() < maxLength) {
            secondOperand.addFirst(0);
        }
        ArrayList<String> header = new ArrayList<>();
        header.add("№");
        header.add("RG1\n" + (maxLength + 1) + " - 1");
        header.add("RG2\n" + maxLength + " - 1");
        header.add("RG3\n" + maxLength + " - 1");
        header.add("CT");
        header.add("Мікрооперації");
        Table multiplyTable = new Table(header);

        StringBuilder rg1 = new StringBuilder();
        for (int i = 0; i < maxLength + 1; i++) {
            rg1.append("0");
        }
        SimpleBinNum rg1Num = new SimpleBinNum(rg1.toString());
        SimpleBinNum rg2Num = new SimpleBinNum(firstOperand, 0, 0);
        SimpleBinNum rg3Num = new SimpleBinNum(secondOperand, 0, 0);
        int counter = 0;
        String zeroRowOper = "RG1 = 0\nRG2 = X\nRG3 = Y\n CT = " + maxLength;
        Row zeroRowType1 = new ZeroRowType1(counter, rg1Num, rg2Num, rg3Num, String.valueOf(maxLength-counter), zeroRowOper);
        multiplyTable.addRow(zeroRowType1);
        String operation1 = "RG2 = RG1[1].r(RG2)\n" +
                "RG1 = 0.r(RG1)\n" +
                "CT = CT - 1";
        String operation2 = "RG1 = RG1 + RG3\n" +
                "RG2 = RG1[1].r(RG2)\n" +
                "RG1 = 0.r(RG1)\n" +
                "CT = CT - 1";
        int flag;
        SimpleBinNum dopRg3Num = new SimpleBinNum("0"+rg3Num);
        for (counter = 1; counter < maxLength + 1; counter++) {
            flag = rg2Num.getNumber().getLast();
            Row row;
            if (flag == 0) {
                rg1Num = (SimpleBinNum) rg1Num.shiftRight();
                rg2Num = (SimpleBinNum) rg2Num.shiftRight(rg1Num.getDroppedBit());
                row = new MainRowType1(counter, rg1Num, rg2Num, rg3Num, String.valueOf(maxLength-counter), operation1);
            } else {
                ArrayList<SimpleBinNum> rg1List = new ArrayList<>();
                rg1List.add(rg1Num);
                rg1List.add(dopRg3Num);
                rg1Num = (SimpleBinNum) rg1Num.sum(dopRg3Num);
                rg1List.add(rg1Num);
                rg1Num = (SimpleBinNum) rg1Num.shiftRight();
                rg1List.add(rg1Num);
                rg2Num = (SimpleBinNum) rg2Num.shiftRight(rg1Num.getDroppedBit());
                row = new MainRowType1(counter, rg1List, rg2Num, rg3Num, String.valueOf(maxLength-counter), operation2);
            }
            multiplyTable.addRow(row);
        }
        SimpleBinNum resultNum = new SimpleBinNum(new StringBuilder(multiplyTable.getRow(counter-1).getRg1Number().getLast().toString()).deleteCharAt(0) + rg2Num.toString());
        return new MultipliedNum(resultNum, multiplyTable);
    }
}
