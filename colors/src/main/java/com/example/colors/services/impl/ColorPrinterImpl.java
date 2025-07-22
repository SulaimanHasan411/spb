package com.example.colors.services.impl;

import com.example.colors.ColorsApplication;
import com.example.colors.services.BluePrinter;
import com.example.colors.services.ColorPrinter;
import com.example.colors.services.GreenPrinter;
import com.example.colors.services.RedPrinter;

public class ColorPrinterImpl implements ColorPrinter {

    private RedPrinter redPrinter;
    private BluePrinter bluePrinter;
    private GreenPrinter greenPrinter;

    public ColorPrinterImpl(RedPrinter redPrinter, BluePrinter bluePrinter, GreenPrinter greenPrinter){
        this.redPrinter = redPrinter;
        this.bluePrinter = bluePrinter;
        this.greenPrinter = greenPrinter;
    }

    @Override
    public String print() {
        return String.join(", ", redPrinter.print(), bluePrinter.print(), greenPrinter.print());
    }
}
