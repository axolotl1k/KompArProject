package org.example.BinNumbers.Divider;

import org.example.BinNumbers.SimpleBinNum;

public class Divider {
    private DivideStrategy divideStrategy;

    public Divider(DivideStrategy divideStrategy) {
        this.divideStrategy = divideStrategy;
    }

    public void setDivideStrategy(DivideStrategy divideStrategy) {
        this.divideStrategy = divideStrategy;
    }

    public DividedNum divide(SimpleBinNum num1, SimpleBinNum num2) {
        return divideStrategy.divide(num1, num2);
    }
}
