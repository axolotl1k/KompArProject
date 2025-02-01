package org.example.BinNumbers;

import org.example.BinNumbers.Divider.DivideStrategy;
import org.example.BinNumbers.Divider.DividedNum;
import org.example.BinNumbers.Multiplier.MultipliedNum;
import org.example.BinNumbers.Multiplier.MultiplyStrategy;
import org.example.BinNumbers.FixedPointNumType.FixedPointBinNumType;

public class FixedPointBinNum implements BinNum {
    private SimpleBinNum signBits;
    private SimpleBinNum integerBits;
    private SimpleBinNum fractionBits;
    private FixedPointBinNumType fixedPointType;
    private BinNumType numType = BinNumType.FixedPoint;


    public FixedPointBinNum(String binNumber, FixedPointBinNumType type) {
        String[] firstSplitBits = binNumber.split("\\.");
        String[] lastSplitBits = firstSplitBits[1].split(",");
        this.signBits = new SimpleBinNum(firstSplitBits[0]);
        this.integerBits = new SimpleBinNum(lastSplitBits[0]);
        this.fractionBits = new SimpleBinNum(lastSplitBits[1]);
        this.fixedPointType = type;
    }

    public FixedPointBinNum(SimpleBinNum signBits, SimpleBinNum integerBits, SimpleBinNum fractionBits, FixedPointBinNumType type) {
        this.signBits = signBits;
        this.integerBits = integerBits;
        this.fractionBits = fractionBits;
        this.fixedPointType = type;
    }

    public FixedPointBinNumType getFixedPointType() {
        return fixedPointType;
    }

    public void setFixedPointType(FixedPointBinNumType fixedPointType) {
        this.fixedPointType = fixedPointType;
    }

    public SimpleBinNum getSignBits() {
        return signBits;
    }

    public SimpleBinNum getIntegerBits() {
        return integerBits;
    }

    public SimpleBinNum getFractionBits() {
        return fractionBits;
    }

    @Override
    public BinNumType getBinNumType() {
        return numType;
    }

    @Override
    public BinNum sum(BinNum num, int carry) {
        num = (num.getBinNumType() == BinNumType.Simple) ? new FixedPointBinNum(("0." + num + ",0"), fixedPointType) : num;
        //потім додати ще для чисел з плаваючою комою
        return fixedPointType.sum(this, (FixedPointBinNum) num, carry);
    }

    @Override
    public MultipliedNum multiply(BinNum multipliedNum, MultiplyStrategy strategy) {
        System.out.println("No supported for now. Returned null");
        return null;
    }

    @Override
    public DividedNum divide(BinNum dividedNum, DivideStrategy strategy) {
        System.out.println("No supported for now. Returned null");
        return null;
    }

    @Override
    public BinNum shiftLeft(int shiftValue) {
        //NOTE: shiftValue actually noy used in this method
        return fixedPointType.shiftLeft(this);
    }

    @Override
    public BinNum shiftRight(int shiftValue) {
        //NOTE: shiftValue actually noy used in this method
        return fixedPointType.shiftRight(this);
    }

    @Override
    public BinNum invert() {
        return fixedPointType.simpleInvert(this);
    }

    public FixedPointBinNum invertToPk() {
        return fixedPointType.invertToPk(this);
    }

    public FixedPointBinNum invertToOk() {
        return fixedPointType.invertToOk(this);
    }

    public FixedPointBinNum invertToDk() {
        return fixedPointType.invertToDk(this);
    }

    @Override
    public String toString() {
        return signBits + "." + integerBits + "," + fractionBits;
    }
}
