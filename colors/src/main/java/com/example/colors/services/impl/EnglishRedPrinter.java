package com.example.colors.services.impl;

import com.example.colors.services.RedPrinter;
import org.springframework.stereotype.Component;

@Component
public class EnglishRedPrinter implements RedPrinter {
    @Override
    public String print() {
        return "red";
    }
}
