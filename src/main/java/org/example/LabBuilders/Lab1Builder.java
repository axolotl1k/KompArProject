package org.example.LabBuilders;

import org.apache.poi.xwpf.usermodel.*;
import org.example.BinNumbers.BinNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STMerge;
import org.example.BinNumbers.FixedPointBinNum;
import org.example.BinNumbers.FixedPointNumType.DkFixedPointBinNumType;
import org.example.BinNumbers.FixedPointNumType.FixedPointBinNumType;
import org.example.BinNumbers.FixedPointNumType.OkFixedPointBinNumType;
import org.example.BinNumbers.FixedPointNumType.PkFixedPointBinNumType;
import org.example.BinNumbers.SimpleBinNum;

public class Lab1Builder extends LabBuilder {
    public Lab1Builder(int variant) {
        super(variant);
    }

    @Override
    public XWPFDocument buildDocument(XWPFDocument document) {
        MyParagraphBuilder textBuilder = new MyParagraphBuilder(document);
        textBuilder.setText("Підготовка до лабораторної роботи")
                .setFontBold(true)
                .setFontDefolt()
                .setAlignment(ParagraphAlignment.CENTER);

        String binVariant = Integer.toBinaryString(variant);
        char[] usedNumbers = {binVariant.charAt(binVariant.length() - 1), binVariant.charAt(binVariant.length() - 2),
                binVariant.charAt(binVariant.length() - 3), binVariant.charAt(binVariant.length() - 4),
                binVariant.charAt(binVariant.length() - 5), binVariant.charAt(binVariant.length() - 6),
                binVariant.charAt(binVariant.length() - 7)};
        textBuilder.createNewParagraphAndRun()
                .setText("Визначення варіанта завдання.")
                .setFontDefolt()
                .createNewParagraphAndRun()
                .setText("Номер варіанта " + variant + " = " + binVariant)
                .setFontDefolt();

        String aIndex = "a7 = " + usedNumbers[6] +
                ", a6 = " + usedNumbers[5] +
                ", a5 = " + usedNumbers[4] +
                ", a4 = " + usedNumbers[3] +
                ", a3 = " + usedNumbers[2] +
                ", a2 = " + usedNumbers[1] +
                ", a1 = " + usedNumbers[0];

        textBuilder.createNewParagraphAndRun()
                .setText(aIndex)
                .setFontDefolt();

        SimpleBinNum fBinNumber = new SimpleBinNum("1" + usedNumbers[6] + usedNumbers[5] + usedNumbers[4] + usedNumbers[3] + "1");
        SimpleBinNum gBinNumber = new SimpleBinNum("1011" + usedNumbers[2] + usedNumbers[1] + usedNumbers[0] + "1");
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("F = " + fBinNumber + ", G = " + gBinNumber);

        String xNum = "X = -F,G = -" + fBinNumber + "," + gBinNumber;
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText(xNum)
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Коди у 15-розрядній сітці")
                .setAlignment(ParagraphAlignment.CENTER);

        FixedPointBinNumType pkType = new PkFixedPointBinNumType();
        FixedPointBinNumType okType = new OkFixedPointBinNumType();
        FixedPointBinNumType dkType = new DkFixedPointBinNumType();

        FixedPointBinNum xPk15 = new FixedPointBinNum("1." + fBinNumber + "," + gBinNumber, pkType);
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Xпк = " + xPk15)
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Xок = " + xPk15.invertToOk())
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Xдк = " + xPk15.invertToDk())
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setAlignment(ParagraphAlignment.CENTER)
                .setText("Коди у 16-розрядній сітці");


        FixedPointBinNum xOk16 = new FixedPointBinNum("1" + xPk15, pkType).invertToOk();
        FixedPointBinNum xDk16 = new FixedPointBinNum("1" + xPk15, pkType).invertToDk();
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Xок = " + xOk16)
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Xдк = " + xDk16)
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setAlignment(ParagraphAlignment.CENTER)
                .setText("Арифметичний зсув")
                .createNewParagraphAndRun()
                .setFontDefolt()
                .setAlignment(ParagraphAlignment.CENTER)
                .setText("Хок");

        shiftTables(xOk16, document, textBuilder);

        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setAlignment(ParagraphAlignment.CENTER)
                .setText("Хдк");

        shiftTables(xDk16, document, textBuilder);

        String dopNumber = "101" + usedNumbers[3] + usedNumbers[2] + ",1" + usedNumbers[1] + usedNumbers[0] + "10";
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Y = X + " + dopNumber);

        FixedPointBinNum dopNumberOk = new FixedPointBinNum("0."+dopNumber, okType);
        FixedPointBinNum dopNumberDk = new FixedPointBinNum("0."+dopNumber, dkType);

        FixedPointBinNum yOk = (FixedPointBinNum) xOk16.sum(dopNumberOk);
        FixedPointBinNum yDk = (FixedPointBinNum) xDk16.sum(dopNumberDk);

        addTabels(xOk16, dopNumberOk, yOk, "Yок =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");
        addTabels(xDk16, dopNumberDk, yDk, "Yдк =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");

        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("Z = X + Y");

        FixedPointBinNum zOk = (FixedPointBinNum) xOk16.sum(yOk);
        FixedPointBinNum zDk = (FixedPointBinNum) xDk16.sum(yDk);

        addTabels(xOk16, yOk, zOk, "Zок =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");
        addTabels(xDk16, yDk, zDk, "Zдк =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");

        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("N = X + (-Y)");

        FixedPointBinNum minusY = (FixedPointBinNum) yOk.invert();
        textBuilder.createNewParagraphAndRun()
                .setFontDefolt()
                .setText("(-Yок) = (-Yдк) = -(" + yOk + ") = " + minusY);

        FixedPointBinNum nOk = (FixedPointBinNum) xOk16.sum(minusY);
        FixedPointBinNum nDk = (FixedPointBinNum) xDk16.sum(minusY);

        addTabels(xOk16, minusY, nOk, "Nок =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");
        addTabels(xDk16, minusY, nDk, "Nдк =", document, textBuilder);
        textBuilder.createNewParagraphAndRun().setText("");

        return document;
    }

    private void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
        for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
            XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
            if (rowIndex == fromRow) {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
            } else {
                cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
            }
        }
    }

    private void shiftTables(FixedPointBinNum num, XWPFDocument document, MyParagraphBuilder textBuilder) {
        XWPFTable table = document.createTable(4, 2);
        mergeCellsVertically(table, 0, 0, 1);
        mergeCellsVertically(table, 0, 2, 3);
        table.setWidth("100%");
        textBuilder.createCellParagraphAndRun(table.getRow(0).getCell(0))
                .setAlignment(ParagraphAlignment.CENTER)
                .setFontDefolt()
                .setFontBold(true)
                .setText("<")
                .createCellParagraphAndRun(table.getRow(0).getCell(1))
                .setFontMonospaced()
                .setText(num.toString());
        textBuilder.createCellParagraphAndRun(table.getRow(2).getCell(0))
                .setAlignment(ParagraphAlignment.CENTER)
                .setFontDefolt()
                .setFontBold(true)
                .setText(">")
                .createCellParagraphAndRun(table.getRow(2).getCell(1))
                .setFontMonospaced()
                .setText(num.toString());
        textBuilder.createCellParagraphAndRun(table.getRow(1).getCell(1))
                .setFontMonospaced()
                .setText(num.shiftLeft().toString())
                .createNewRun()
                .setFontDefolt()
                .setText(" - відбувається переповнення")
                .createCellParagraphAndRun(table.getRow(3).getCell(1))
                .setFontMonospaced()
                .setText(num.shiftRight().toString())
                .createNewRun()
                .setFontDefolt()
                .setText(" - переповнення не відбувається");
    }

    private void addTabels(FixedPointBinNum numA, FixedPointBinNum numB, FixedPointBinNum numC, String type, XWPFDocument document, MyParagraphBuilder textBuilder) {
        XWPFTable table = document.createTable(3, 2);
        table.setInsideHBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setInsideVBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setBottomBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setTopBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setLeftBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");
        table.setRightBorder(XWPFTable.XWPFBorderType.NONE, 0, 0, "FFFFFF");

        textBuilder.createCellParagraphAndRun(table.getRow(0).getCell(1))
                .setFontMonospaced()
                .setAlignment(ParagraphAlignment.RIGHT)
                .setText(numA.toString())
                .createCellParagraphAndRun(table.getRow(1).getCell(1))
                .setFontMonospaced()
                .setAlignment(ParagraphAlignment.RIGHT)
                .setText("+" + numB);
        BinNum numD = new FixedPointBinNum(numA.toString(), new DkFixedPointBinNumType()).sum(new FixedPointBinNum(numB.toString(), new DkFixedPointBinNumType()));
        if (!numC.toString().equals(numD.toString())) {
            table.createRow();
            table.createRow();
            textBuilder.createCellParagraphAndRun(table.getRow(2).getCell(1))
                    .setFontMonospaced()
                    .setAlignment(ParagraphAlignment.RIGHT)
                    .setText("=" + numD)
                    .createCellParagraphAndRun(table.getRow(3).getCell(1))
                    .setFontMonospaced()
                    .setAlignment(ParagraphAlignment.RIGHT)
                    .setText("+1")
                    .createCellParagraphAndRun(table.getRow(4).getCell(1))
                    .setFontMonospaced()
                    .setAlignment(ParagraphAlignment.RIGHT)
                    .setText("=" + numC)
                    .setFontBold(true);

        } else {
            textBuilder.createCellParagraphAndRun(table.getRow(2).getCell(1))
                    .setFontMonospaced()
                    .setAlignment(ParagraphAlignment.RIGHT)
                    .setText("=" + numC)
                    .setFontBold(true);
        }
        mergeCellsVertically(table, 0, 0, table.getRows().size() - 1);
        textBuilder.createCellParagraphAndRun(table.getRow(0).getCell(0))
                .setFontBold(true)
                .setFontDefolt()
                .setText(type);
    }
}
