package org.example.BinNumbers;

import org.example.BinNumbers.Multiplier.MultipliedNum;
import org.example.BinNumbers.Multiplier.MultiplyStrategy;

public interface BinNum {
    BinNum sum(BinNum num, int carry);
    default BinNum sum(BinNum num) {
        return sum(num, 0);
    }
    BinNum shiftLeft(int shiftValue);
    BinNum shiftRight(int shiftValue);
    default BinNum shiftLeft() {
        return shiftLeft(0);
    }
    default BinNum shiftRight() {
        return shiftRight(0);
    }
    BinNum invert();
    String toString();
    BinNumType getBinNumType();

    MultipliedNum multiply(BinNum multipliedNum, MultiplyStrategy strategy);
}
