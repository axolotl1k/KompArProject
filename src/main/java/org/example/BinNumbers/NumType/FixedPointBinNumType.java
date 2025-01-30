package org.example.BinNumbers.NumType;

import org.example.BinNumbers.FixedPointBinNum;

public interface FixedPointBinNumType {
    FixedPointBinNum sum(FixedPointBinNum a, FixedPointBinNum b, int carry);
    FixedPointBinNum shiftLeft(FixedPointBinNum num);
    FixedPointBinNum shiftRight(FixedPointBinNum num);
    FixedPointBinNum invertToDk(FixedPointBinNum num);
    FixedPointBinNum invertToOk(FixedPointBinNum num);
    FixedPointBinNum invertToPk(FixedPointBinNum num);
    FixedPointBinNum simpleInvert(FixedPointBinNum num);
}
