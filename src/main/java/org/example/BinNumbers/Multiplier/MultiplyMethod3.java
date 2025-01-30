package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;
import org.example.Tables.RowType3.MainRowType3;
import org.example.Tables.RowType3.ZeroRowType3;
import org.example.Tables.Table;

import java.util.ArrayList;

public class MultiplyMethod3 implements MultiplyStrategy {

    private StringBuilder gNumber; //працює за принципом додавання дробового числа до результату дробового множення

    public MultiplyMethod3() {
        gNumber = new StringBuilder("");
    }

    public MultiplyMethod3(String gNumber) {
        this.gNumber = new StringBuilder(gNumber);
    }

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
        firstOperand.add(0);
        ArrayList<String> header = new ArrayList<>();
        header.add("№");
        header.add("RG2\n" + (maxLength + 1) + " - 1");
        header.add("RG1\n" + maxLength + " - 1");
        header.add("RG3\n" + maxLength + " - 1");
        header.add("CT");
        header.add("Мікрооперації");
        Table multiplyTable = new Table(header);

        StringBuilder rg1 = new StringBuilder();
        for (int i = gNumber.length(); i < maxLength; i++) {
            gNumber.append("0");
        }
        SimpleBinNum rg1Num = new SimpleBinNum(gNumber.toString());
        SimpleBinNum rg2Num = new SimpleBinNum(firstOperand, 0, 0);
        SimpleBinNum rg3Num = new SimpleBinNum(secondOperand, 0, 0);
        int counter = 0;
        String zeroRowOper = "RG1 = 0\nRG2 = X.0\nRG3 = Y";
        Row zeroRowType3 = new ZeroRowType3(counter, rg2Num, rg1Num, rg3Num, String.valueOf(maxLength-counter), zeroRowOper);
        multiplyTable.addRow(zeroRowType3);
        String operation1 = "RG1 = l(RG1).0\n" +
                "RG2 = l(RG2).RG1[6]\n";
        String operation2 = "RG1 = RG1 + RG3\n" +
                "RG2 = RG2 + 0 + Cl\n" +
                "RG1 = l(RG1).0\n" +
                "RG2 = l(RG2).RG1[6]";
        int flag;
        for (counter = 1; counter < maxLength + 1; counter++) {
            flag = rg2Num.getNumber().getFirst();
            Row row;
            if (flag == 0) {
                rg1Num = (SimpleBinNum) rg1Num.shiftLeft();
                rg2Num = (SimpleBinNum) rg2Num.shiftLeft(rg1Num.getDroppedBit());
                row = new MainRowType3(counter, rg2Num, rg1Num, rg3Num, String.valueOf(maxLength-counter), operation1);
            } else {
                ArrayList<SimpleBinNum> rg1List = new ArrayList<>();
                ArrayList<SimpleBinNum> rg2List = new ArrayList<>();
                rg1List.add(rg1Num);
                rg1List.add(rg3Num);
                rg1Num = (SimpleBinNum) rg1Num.sum(rg3Num);
                rg1List.add(rg1Num);
                rg2List.add(rg2Num);
                SimpleBinNum dopRg2Num = new SimpleBinNum(String.valueOf(rg1Num.getCarryBit()));
                rg2Num = (SimpleBinNum) rg2Num.sum(dopRg2Num);
                rg2List.add(dopRg2Num);
                rg2List.add(rg2Num);
                rg1Num = (SimpleBinNum) rg1Num.shiftLeft();
                rg1List.add(rg1Num);
                rg2Num = (SimpleBinNum) rg2Num.shiftLeft(rg1Num.getDroppedBit());
                rg2List.add(rg2Num);
                row = new MainRowType3(counter, rg2List, rg1List, rg3Num, String.valueOf(maxLength-counter), operation2);
            }
            multiplyTable.addRow(row);
        }
        String returnString = multiplyTable.getRow(counter - 1).getRg2Number().getLast().toString() + multiplyTable.getRow(counter - 1).getRg1Number().getLast().toString();
        SimpleBinNum resultNum = new SimpleBinNum(returnString.substring(0, returnString.length()-1));
        return new MultipliedNum(resultNum, multiplyTable);
    }
}
