package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;
import org.example.Tables.RowType2.MainRowType2;
import org.example.Tables.RowType2.ZeroRowType2;
import org.example.Tables.Table;

import java.util.ArrayList;

public class MultiplyMethod2 implements MultiplyStrategy {

    private StringBuilder gNumber; //працює за принципом додавання дробового числа до результату дробового множення

    public MultiplyMethod2() {
        gNumber = new StringBuilder();
    }

    public MultiplyMethod2(String gNumber) {
        this.gNumber = new StringBuilder(gNumber);
    }

    @Override
    public MultipliedNum multiply(SimpleBinNum a, SimpleBinNum b) {
        ArrayList<Integer> firstOperand = new ArrayList<>(a.getNumber());
        ArrayList<Integer> secondOperand = new ArrayList<>(b.getNumber());
        int maxLength = Math.max(firstOperand.size(), secondOperand.size());
        while (firstOperand.size() < maxLength) {
            firstOperand.addLast(0);
        }
        while (secondOperand.size() < maxLength) {
            secondOperand.addLast(0);
        }
        ArrayList<String> header = new ArrayList<>();
        header.add("№");
        header.add("RG1\n" + (maxLength * 2) + " - 1");
        header.add("RG2\n" + maxLength + " - 1");
        header.add("RG3\n" + (maxLength * 2) + " - 1");
        header.add("");
        header.add("Мікрооперації");
        Table multiplyTable = new Table(header);

        StringBuilder rg1 = new StringBuilder();
        for (int i = 0; i < maxLength; i++) {
            rg1.append("0");
        }
        for (int i = gNumber.length(); i < maxLength; i++) {
            gNumber.append("0");
        }
        SimpleBinNum rg1Num = new SimpleBinNum(gNumber + rg1.toString());
        SimpleBinNum rg2Num = new SimpleBinNum(firstOperand, 0, 0);
        SimpleBinNum rg3Num = new SimpleBinNum(rg1 + new SimpleBinNum(secondOperand, 0, 0).toString());
        int counter = 0;
        String zeroRowOper = "RG1 = 0\nRG2 = X\nRG3 = Y";
        Row zeroRowType2 = new ZeroRowType2(counter, rg1Num, rg2Num, rg3Num, zeroRowOper);
        multiplyTable.addRow(zeroRowType2);
        String operation1 = "RG2 = 0.r(RG2)\n" +
                "RG3 = l(RG3).0";
        String operation2 = "RG1 = RG1 + RG3\n" +
                "RG2 = 0.r(RG2)\n" +
                "RG3 = l(RG3).0";
        boolean flag = rg2Num.toString().equals(rg1.toString());
        int flagNum;
        counter++;
        while (!flag) {
            flagNum = rg2Num.getNumber().getLast();
            Row row;
            if (flagNum == 0) {
                rg2Num = (SimpleBinNum) rg2Num.shiftRight();
                rg3Num = (SimpleBinNum) rg3Num.shiftLeft();
                row = new MainRowType2(counter, rg1Num, rg2Num, rg3Num, operation1);
            } else {
                ArrayList<SimpleBinNum> rg1List = new ArrayList<>();
                rg1List.add(rg1Num);
                rg1List.add(rg3Num);
                rg1Num = (SimpleBinNum) rg1Num.sum(rg3Num);
                rg1List.add(rg1Num);
                rg2Num = (SimpleBinNum) rg2Num.shiftRight();
                rg3Num = (SimpleBinNum) rg3Num.shiftLeft();
                row = new MainRowType2(counter, rg1List, rg2Num, rg3Num, operation2);
            }
            multiplyTable.addRow(row);
            flag = rg1.toString().equals(rg2Num.toString());
            counter++;
        }
        SimpleBinNum resultNum = new SimpleBinNum(multiplyTable.getRow(counter - 1).getRg1Number().getLast().toString());
        return new MultipliedNum(resultNum, multiplyTable);
    }
}
