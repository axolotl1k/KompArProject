package org.example.BinNumbers.Multiplier;

import org.example.BinNumbers.SimpleBinNum;

public class Multiplier {
    private MultiplyStrategy multiplyStrategy;

    public Multiplier(MultiplyStrategy multiplyStrategy) {
        this.multiplyStrategy = multiplyStrategy;
    }

    public void setMultiplyStrategy(MultiplyStrategy multiplyStrategy) {
        this.multiplyStrategy = multiplyStrategy;
    }

    public MultipliedNum multiply(SimpleBinNum num1, SimpleBinNum num2) {
        return multiplyStrategy.multiply(num1, num2);
    }

}
