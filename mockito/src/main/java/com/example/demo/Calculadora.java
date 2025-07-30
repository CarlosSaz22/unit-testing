package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;

public class Calculadora {
    CalculadoraGoogle google;
    public int sumaNuestra(int a, int b) {
        return google.sumar(a,b);
    }
}
