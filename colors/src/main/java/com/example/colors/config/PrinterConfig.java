package com.example.colors.config;

import com.example.colors.services.BluePrinter;
import com.example.colors.services.ColorPrinter;
import com.example.colors.services.GreenPrinter;
import com.example.colors.services.RedPrinter;
import com.example.colors.services.impl.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrinterConfig {

    @Bean
    public BluePrinter bluePrinter(){
        return new SpanishBluePrinter();
    }

    @Bean
    public RedPrinter redPrinter(){
        return new SpanishRedPrinter();
    }

    @Bean
    public GreenPrinter greenPrinter(){
        return new SpanishGreenPrinter();
    }

    @Bean
    public ColorPrinter colorPrinter(BluePrinter bluePrinter, RedPrinter redPrinter, GreenPrinter greenPrinter){
        return new ColorPrinterImpl(redPrinter, bluePrinter, greenPrinter);
    }
}
