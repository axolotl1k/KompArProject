package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;
import org.example.Tables.RowType4.MainRowType4;
import org.example.Tables.RowType4.ZeroRowType4;
import org.example.Tables.Table;

import java.util.ArrayList;

public class MultiplyMethod4 implements MultiplyStrategy {

    private StringBuilder gNumber; //працює за принципом додавання дробового числа до результату дробового множення

    public MultiplyMethod4() {
        gNumber = new StringBuilder();
    }

    public MultiplyMethod4(String gNumber) {
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
        header.add("RG1\n" + (maxLength * 2 + 1) + " - 1");
        header.add("RG2\n" + maxLength + " - 1");
        header.add("RG3\n" + (maxLength * 2 + 1) + " - 1");
        header.add("");
        header.add("Мікрооперації");
        Table multiplyTable = new Table(header);

        StringBuilder rg1 = new StringBuilder();
        rg1.append("0".repeat(maxLength));
        for (int i = gNumber.length(); i < maxLength; i++) {
            gNumber.append("0");
        }
        SimpleBinNum rg1Num = new SimpleBinNum("0" + gNumber + rg1);
        SimpleBinNum rg2Num = new SimpleBinNum(firstOperand, 0, 0);
        SimpleBinNum rg3Num = new SimpleBinNum("0" + new SimpleBinNum(secondOperand, 0, 0) + rg1);
        int counter = 0;
        String zeroRowOper = "RG1 = 0\nRG2 = X\nRG3 = 0.Y.[0..0]";
        Row zeroRowType2 = new ZeroRowType4(counter, rg1Num, rg2Num, rg3Num, zeroRowOper);
        multiplyTable.addRow(zeroRowType2);
        String operation1 = "RG2 = l(RG2).0\n" +
                "RG3 = 0.r(RG3)\n";
        String operation2 = "RG1 = RG1 + RG3\n" +
                "RG2 = l(RG2).0\n" +
                "RG3 = 0.r(RG3)";
        boolean flag = rg2Num.toString().equals(rg1.toString());
        int flagNum;
        counter++;
        while (!flag) {
            flagNum = rg2Num.getNumber().getFirst();
            Row row;
            if (flagNum == 0) {
                rg2Num = (SimpleBinNum) rg2Num.shiftLeft();
                rg3Num = (SimpleBinNum) rg3Num.shiftRight();
                row = new MainRowType4(counter, rg1Num, rg2Num, rg3Num, operation1);
            } else {
                ArrayList<SimpleBinNum> rg1List = new ArrayList<>();
                rg1List.add(rg1Num);
                rg1List.add(rg3Num);
                rg1Num = (SimpleBinNum) rg1Num.sum(rg3Num);
                rg1List.add(rg1Num);
                rg2Num = (SimpleBinNum) rg2Num.shiftLeft();
                rg3Num = (SimpleBinNum) rg3Num.shiftRight();
                row = new MainRowType4(counter, rg1List, rg2Num, rg3Num, operation2);
            }
            multiplyTable.addRow(row);
            flag = rg1.toString().equals(rg2Num.toString());
            counter++;
        }
        String returnString = multiplyTable.getRow(counter - 1).getRg1Number().getLast().toString();
        SimpleBinNum resultNum = new SimpleBinNum(returnString.substring(0, returnString.length() - 1));
        return new MultipliedNum(resultNum, multiplyTable);
    }
}
