package org.example.BinNumbers.NumType;

import org.example.BinNumbers.BinNum;
import org.example.BinNumbers.FixedPointBinNum;
import org.example.BinNumbers.SimpleBinNum;

public class PkFixedPointBinNumType implements FixedPointBinNumType {
    @Override
    public FixedPointBinNum sum(FixedPointBinNum a, FixedPointBinNum b, int carry) {
        System.out.println("Can`t add PK numbers. Returned first number");
        return a;
    }

    @Override
    public FixedPointBinNum shiftLeft(FixedPointBinNum num) {
        System.out.println("Can`t shift PK numbers. Returned same number");
        return num;
    }

    @Override
    public FixedPointBinNum shiftRight(FixedPointBinNum num) {
        System.out.println("Can`t shift PK numbers. Returned same number");
        return num;
    }

    @Override
    public FixedPointBinNum invertToOk(FixedPointBinNum num) {
        SimpleBinNum signBits = num.getSignBits();
        if (signBits.getNumber().getFirst() == 0) return num;
        SimpleBinNum integerBits = (SimpleBinNum) num.getIntegerBits().invert();
        SimpleBinNum fractionBits = (SimpleBinNum) num.getFractionBits().invert();
        return new FixedPointBinNum(signBits, integerBits, fractionBits, new OkFixedPointBinNumType());
    }

    @Override
    public FixedPointBinNum invertToDk(FixedPointBinNum num) {
        BinNum signBits = num.getSignBits();
        if (((SimpleBinNum) signBits).getNumber().getFirst() == 0) return num;
        BinNum integerBits = num.getIntegerBits().invert();
        BinNum fractionBits = num.getFractionBits().invert();
        fractionBits = fractionBits.sum(new SimpleBinNum("0"), 1);
        integerBits = integerBits.sum(new SimpleBinNum("0"), ((SimpleBinNum) fractionBits).getCarryBit());
        signBits = signBits.sum(new SimpleBinNum("0"), ((SimpleBinNum) integerBits).getCarryBit());
        return new FixedPointBinNum((SimpleBinNum) signBits, (SimpleBinNum) integerBits, (SimpleBinNum) fractionBits, new DkFixedPointBinNumType());
    }

    @Override
    public FixedPointBinNum invertToPk(FixedPointBinNum num) {
        System.out.println("Can`t invert to same type numbers. Returned same number");
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
