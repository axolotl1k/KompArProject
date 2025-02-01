package org.example.BinNumbers;

import org.example.BinNumbers.Divider.DivideStrategy;
import org.example.BinNumbers.Divider.DividedNum;
import org.example.BinNumbers.Divider.Divider;
import org.example.BinNumbers.Multiplier.MultipliedNum;
import org.example.BinNumbers.Multiplier.Multiplier;
import org.example.BinNumbers.Multiplier.MultiplyStrategy;

import java.util.ArrayList;

public class SimpleBinNum implements BinNum {

    private ArrayList<Integer> number;
    private int carryBit;
    private int droppedBit;
    private BinNumType numType = BinNumType.Simple;

    public SimpleBinNum(String binNumber) {
        ArrayList<Integer> digits = new ArrayList<>();
        for (char c : binNumber.toCharArray()) {
            digits.add(Character.getNumericValue(c));
        }
        this.number = digits;
        this.carryBit = 0;
        this.droppedBit = 0;
    }

    public SimpleBinNum(ArrayList<Integer> number, int carryBit, int droppedBit) {
        this.number = number;
        this.carryBit = carryBit;
        this.droppedBit = droppedBit;
    }

    public int getCarryBit() {
        return carryBit;
    }

    public int getDroppedBit() {
        return droppedBit;
    }

    public ArrayList<Integer> getNumber() {
        return number;
    }

    @Override
    public BinNum sum(BinNum num, int carry) {
        if (num.getBinNumType() == BinNumType.FixedPoint) {
            //NOTE: returned a fixed point number.
            return num.sum(this, carry);
        }
        ArrayList<Integer> firstOperand = new ArrayList<>(number);
        ArrayList<Integer> secondOperand = ((SimpleBinNum) num).getNumber();
        int maxLength = Math.max(firstOperand.size(), secondOperand.size());

        while (firstOperand.size() < maxLength) {
            firstOperand.addFirst(0);
        }
        while (secondOperand.size() < maxLength) {
            secondOperand.addFirst(0);
        }
        ArrayList<Integer> result = new ArrayList<>();
        for (int i = firstOperand.size() - 1; i >= 0; i--) {
            int rawSum = firstOperand.get(i) + secondOperand.get(i) + carry;
            int processedSum;
            if (rawSum >= 2) {
                processedSum = rawSum - 2;
                carry = 1;
            } else {
                processedSum = rawSum;
                carry = 0;
            }
            result.addFirst(processedSum);
        }
        return new SimpleBinNum(result, carry, 0);
    }

    @Override
    public MultipliedNum multiply(BinNum multipliedNum, MultiplyStrategy strategy) {
        Multiplier multiplier = new Multiplier(strategy);
        //реалізувати логіку для інших форматів чисел
        return multiplier.multiply(this, (SimpleBinNum) multipliedNum);
    }

    @Override
    public DividedNum divide(BinNum dividerNum, DivideStrategy strategy) {
        Divider divider = new Divider(strategy);
        //реалізувати логіку для інших форматів чисел
        return divider.divide(this, (SimpleBinNum) dividerNum);
    }

    @Override
    public BinNum shiftLeft(int shiftValue) {
        ArrayList<Integer> newNumber = new ArrayList<>(number);
        newNumber.addLast(shiftValue);
        int dropBit = newNumber.removeFirst();
        return new SimpleBinNum(newNumber, 0, dropBit);
    }

    @Override
    public BinNum shiftRight(int shiftValue) {
        ArrayList<Integer> newNumber = new ArrayList<>(number);
        newNumber.addFirst(shiftValue);
        int dropBit = newNumber.removeLast();
        return new SimpleBinNum(newNumber, 0, dropBit);
    }

    @Override
    public BinNum invert() {
        ArrayList<Integer> newNumber = new ArrayList<>(number);
        for (int i = 0; i < newNumber.size(); i++) {
            int inversDigit = (newNumber.get(i)==1) ? 0 : 1;
            newNumber.set(i, inversDigit);
        }
        return new SimpleBinNum(newNumber, 0, 0);
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Integer i : number) {
            str.append(i);
        }
        return str.toString();
    }

    @Override
    public BinNumType getBinNumType() {
        return numType;
    }
}
