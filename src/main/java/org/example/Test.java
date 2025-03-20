package org.example;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.example.BinNumbers.BinNum;
import org.example.BinNumbers.Divider.DivideMethod1;
import org.example.BinNumbers.Divider.DivideMethod2;
import org.example.BinNumbers.Divider.DividedNum;
import org.example.BinNumbers.FixedPointBinNum;
import org.example.BinNumbers.Multiplier.*;
import org.example.BinNumbers.FixedPointNumType.DkFixedPointBinNumType;
import org.example.BinNumbers.FixedPointNumType.OkFixedPointBinNumType;
import org.example.BinNumbers.FixedPointNumType.PkFixedPointBinNumType;
import org.example.BinNumbers.SimpleBinNum;
import org.example.LabBuilders.Lab1Builder;
import org.example.LabBuilders.LabBuilder;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {
    public static void main(String[] args) {
//        BinNum num = new SimpleBinNum("0000000");
//        System.out.println(num);
//
//        BinNum num2 = num.shiftLeft(1);
//        System.out.println(num2);
//
//        BinNum num3 = num.shiftRight(1);
//        System.out.println(num3);
//
//        BinNum num4 = num.invert();
//        System.out.println(num4);
//
//        BinNum num5 = num.sum(new SimpleBinNum("0111100"));
//        System.out.println(num5);
//
//        FixedPointBinNum fixedNum = new FixedPointBinNum("11.110001,10111001", new PkFixedPointBinNumType());
//        System.out.println(fixedNum);
//
//        BinNum fixedNum2 = fixedNum.invertToOk();
//        System.out.println(fixedNum2);
//
//        BinNum fixedNum3 = fixedNum.invertToDk();
//        System.out.println(fixedNum3);
//
//        BinNum fixedNum4 = fixedNum2.shiftLeft();
//        System.out.println(fixedNum4);
//
//        BinNum fixedNum5 = fixedNum3.shiftLeft();
//        System.out.println(fixedNum5);
//
//        BinNum fixedNum6 = fixedNum2.shiftRight();
//        System.out.println(fixedNum6);
//
//        BinNum fixedNum7 = fixedNum3.shiftRight();
//        System.out.println(fixedNum7);
//
//        BinNum fixedNum8 = fixedNum2.sum(new FixedPointBinNum("00.010101,1001", new OkFixedPointBinNumType()));
//        System.out.println(fixedNum8);
//
//        BinNum fixedNum9 = fixedNum2.sum(fixedNum8);
//        System.out.println(fixedNum9);
//
//        BinNum fixedNum10 = fixedNum3.sum(fixedNum3.sum(new FixedPointBinNum("00.010101,1001", new DkFixedPointBinNumType())));
//        System.out.println(fixedNum10);
//
//        BinNum fixedNum11 = ((FixedPointBinNum) fixedNum10).invertToPk();
//        System.out.println(fixedNum11);
//
//        BinNum testNum = new SimpleBinNum("01001");
//        BinNum testSum = fixedNum2.sum(testNum);
//        System.out.println(testSum);
//
//        BinNum testSum2 = testNum.sum(fixedNum2);
//        System.out.println(testSum2);

        BinNum testMult1 = new SimpleBinNum("10010");
        BinNum testMult2 = new SimpleBinNum("10011");

        MultipliedNum testMultResult1 = testMult1.multiply(testMult2, new MultiplyMethod2());
        System.out.println(testMultResult1.getNumber().toString());

        BinNum testDiv1 = new SimpleBinNum("0110");
        BinNum testDiv2 = new SimpleBinNum("1100");

        DividedNum testDivResult = testDiv1.divide(testDiv2, new DivideMethod2());
        System.out.println(testDivResult.getNumber().toString());

        XWPFDocument document = new XWPFDocument();
        testMultResult1.getMultiplyTable().createWordTable(document);
        try (FileOutputStream out = new FileOutputStream("TableExample.docx")) {
            document.write(out);
            System.out.println("Word document created successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }

//        LabBuilder lab1Builder = new Lab1Builder(4104);
//        XWPFDocument document2 = new XWPFDocument();
//        lab1Builder.buildDocument(document2);
//        try (FileOutputStream out = new FileOutputStream("Lab1Example.docx")) {
//            document2.write(out);
//            System.out.println("Word document created successfully!");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
