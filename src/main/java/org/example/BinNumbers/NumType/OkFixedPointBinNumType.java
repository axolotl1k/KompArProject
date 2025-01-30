package org.example.BinNumbers.NumType;

import org.example.BinNumbers.FixedPointBinNum;
import org.example.BinNumbers.SimpleBinNum;

public class OkFixedPointBinNumType implements FixedPointBinNumType {
    @Override
    public FixedPointBinNum sum(FixedPointBinNum a, FixedPointBinNum b, int carry) {
        SimpleBinNum signBitsA = a.getSignBits();
        SimpleBinNum integerBitsA = a.getIntegerBits();
        SimpleBinNum fractionBitsA = a.getFractionBits();
        SimpleBinNum signBitsB = b.getSignBits();
        SimpleBinNum integerBitsB = b.getIntegerBits();
        SimpleBinNum fractionBitsB = b.getFractionBits();
        int maxLength = Math.max(fractionBitsA.getNumber().size(), fractionBitsB.getNumber().size());
        while (fractionBitsA.getNumber().size() < maxLength) {
            fractionBitsA.getNumber().addLast(signBitsA.getNumber().getFirst());
        }
        while (fractionBitsB.getNumber().size() < maxLength) {
            fractionBitsB.getNumber().addLast(signBitsB.getNumber().getFirst());
        }
        maxLength = Math.max(integerBitsA.getNumber().size(), integerBitsB.getNumber().size());
        while (integerBitsA.getNumber().size() < maxLength) {
            integerBitsA.getNumber().addFirst(signBitsA.getNumber().getFirst());
        }
        while (integerBitsB.getNumber().size() < maxLength) {
            integerBitsB.getNumber().addFirst(signBitsB.getNumber().getFirst());
        }
        maxLength = Math.max(signBitsA.getNumber().size(), signBitsB.getNumber().size());
        while (signBitsA.getNumber().size() < maxLength) {
            signBitsA.getNumber().addFirst(signBitsA.getNumber().getFirst());
        }
        while (signBitsB.getNumber().size() < maxLength) {
            signBitsB.getNumber().addFirst(signBitsB.getNumber().getFirst());
        }
        fractionBitsA = (SimpleBinNum) fractionBitsA.sum(fractionBitsB, carry);
        integerBitsA = (SimpleBinNum) integerBitsA.sum(integerBitsB, fractionBitsA.getCarryBit());
        signBitsA = (SimpleBinNum) signBitsA.sum(signBitsB, integerBitsA.getCarryBit());
        if (signBitsA.getCarryBit() == 0) {
            return new FixedPointBinNum(signBitsA, integerBitsA, fractionBitsA, a.getFixedPointType());
        }
        return sum(new FixedPointBinNum(signBitsA, integerBitsA, fractionBitsA, a.getFixedPointType()), new FixedPointBinNum("0.0,0", a.getFixedPointType()), 1);
    }

    @Override
    public FixedPointBinNum shiftLeft(FixedPointBinNum num) {
        SimpleBinNum signBits = num.getSignBits();
        SimpleBinNum integerBits = num.getIntegerBits();
        SimpleBinNum fractionBits = num.getFractionBits();
        fractionBits = (SimpleBinNum) fractionBits.shiftLeft(signBits.getNumber().getFirst());
        integerBits = (SimpleBinNum) integerBits.shiftLeft(fractionBits.getDroppedBit());
        signBits = (SimpleBinNum) signBits.shiftLeft(integerBits.getDroppedBit());
        return new FixedPointBinNum(signBits, integerBits, fractionBits, num.getFixedPointType());
    }

    @Override
    public FixedPointBinNum shiftRight(FixedPointBinNum num) {
        SimpleBinNum signBits = num.getSignBits();
        SimpleBinNum integerBits = num.getIntegerBits();
        SimpleBinNum fractionBits = num.getFractionBits();
        signBits = (SimpleBinNum) signBits.shiftRight(signBits.getNumber().getFirst());
        integerBits = (SimpleBinNum) integerBits.shiftRight(signBits.getDroppedBit());
        fractionBits = (SimpleBinNum) fractionBits.shiftRight(integerBits.getDroppedBit());
        return new FixedPointBinNum(signBits, integerBits, fractionBits, num.getFixedPointType());
    }

    @Override
    public FixedPointBinNum invertToPk(FixedPointBinNum num) {
        SimpleBinNum signBits = num.getSignBits();
        if (signBits.getNumber().getFirst() == 0) return num;
        SimpleBinNum integerBits = (SimpleBinNum) num.getIntegerBits().invert();
        SimpleBinNum fractionBits = (SimpleBinNum) num.getFractionBits().invert();
        return new FixedPointBinNum(signBits, integerBits, fractionBits, new PkFixedPointBinNumType());
    }

    @Override
    public FixedPointBinNum invertToOk(FixedPointBinNum num) {
        System.out.println("Can`t invert to same type number. Returned same number");
        return num;
    }

    @Override
    public FixedPointBinNum invertToDk(FixedPointBinNum num) {
        System.out.println("Can`t invert to that type number. Returned same number");
        return num;
    }

    @Override
    public FixedPointBinNum simpleInvert(FixedPointBinNum num) {
        SimpleBinNum signBits = (SimpleBinNum) num.getSignBits().invert();
        SimpleBinNum integerBits = (SimpleBinNum) num.getIntegerBits().invert();
        SimpleBinNum fractionBits = (SimpleBinNum) num.getFractionBits().invert();
        return new FixedPointBinNum(signBits, integerBits, fractionBits, num.getFixedPointType());
    }
}
