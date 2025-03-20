package org.example.BinNumbers.Divider;

import org.example.BinNumbers.SimpleBinNum;
import org.example.Tables.Row;
import org.example.Tables.RowType6.MainRowType6;
import org.example.Tables.RowType6.ZeroRowType6;
import org.example.Tables.Table;

import java.util.ArrayList;
import java.util.List;

public class DivideMethod2 implements DivideStrategy {
    @Override
    public DividedNum divide(SimpleBinNum a, SimpleBinNum b) {
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
        header.add("RG3\n" + (maxLength + 1) + " - 1");
        header.add("RG2\n" + (maxLength * 2 + 1) + " - 1");
        header.add("RG1\n" + (maxLength * 2 + 1) + " - 1");
        header.add("");
        header.add("Мікрооперації");
        Table divideTable = new Table(header);

        String rg3 = "1".repeat(maxLength + 1);
        firstOperand.addFirst(0);
        secondOperand.addFirst(0);
        for (int i = 0; i < maxLength; i++) {
            firstOperand.addLast(0);
            secondOperand.addLast(0);
        }
        SimpleBinNum rg1NumPlus = new SimpleBinNum(secondOperand, 0, 0);
        SimpleBinNum rg1NumMinus = (SimpleBinNum) rg1NumPlus.invert().sum(new SimpleBinNum("0"), 1);
        SimpleBinNum rg2Num = new SimpleBinNum(firstOperand, 0, 0);
        SimpleBinNum rg3Num = new SimpleBinNum(rg3);
        int counter = 0;
        String zeroRowOper = "RG3 = 1..11\nRG2 = X.0..0\nRG1 = Y.0..0";
        ArrayList<SimpleBinNum> rg1List = new ArrayList<>(List.of(rg1NumPlus, rg1NumMinus));
        Row zeroRowType6 = new ZeroRowType6(counter, rg3Num, rg2Num, rg1List, zeroRowOper);
        divideTable.addRow(zeroRowType6);
        String operationPlus = "RG2 = RG2+!RG1+D\n" +
                "RG3 = l(RG3).SM(p)\n" +
                "RG1 = 0.r(RG1)";
        String operationMinus = "RG2 = RG2+RG1\n" +
                "RG3 = l(RG3).SM(p)\n" +
                "RG1 = 0.r(RG1)";

        while (rg3Num.getNumber().getFirst() != 0){
            ArrayList<SimpleBinNum> rg2List = new ArrayList<>();
            rg2List.add(rg2Num);
            SimpleBinNum rg1Num; String operation;
            if (rg2Num.getNumber().getFirst() == 0) {
                rg1Num = rg1NumMinus;
                operation = operationMinus;
            } else {
                rg1Num = rg1NumPlus;
                operation = operationPlus;
            }
            rg2List.add(rg1Num);
            rg2Num = (SimpleBinNum) rg2Num.sum(rg1Num);
            rg2List.add(rg2Num);
            rg3Num = (SimpleBinNum) rg3Num.shiftLeft(rg2Num.getCarryBit());
            rg1NumMinus = (SimpleBinNum) rg1NumMinus.shiftRight(1);
            rg1NumPlus = (SimpleBinNum) rg1NumPlus.shiftRight();
            rg1List = new ArrayList<>(List.of(rg1NumPlus, rg1NumMinus));
            counter++;
            Row row = new MainRowType6(counter, rg3Num, rg2List, rg1List, operation);
            divideTable.addRow(row);
        }
        String returnString = divideTable.getRow(counter).getRg3Number().getLast().toString();
        SimpleBinNum resultNum = new SimpleBinNum(returnString.substring(1));
        return new DividedNum(resultNum, divideTable);
    }
}
