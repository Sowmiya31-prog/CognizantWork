package com.example.demo.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParameterizedAddTest {

    CalculatorService calculatorService = new CalculatorService();

    @ParameterizedTest
    @CsvSource({
        "1,2,3",
        "2,3,5",
        "10,20,30",
        "0,0,0"
    })
    void testAdd(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }
}