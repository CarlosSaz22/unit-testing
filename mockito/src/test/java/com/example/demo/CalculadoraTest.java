package com.example.demo;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CalculadoraTest {

    @Mock
    CalculadoraGoogle google;

    @InjectMocks
    Calculadora calculadora;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        when(google.sumar(5,5)).thenReturn(10);
    }

    @Test
    public void sumaNuestraTest() {
        assertEquals(10,calculadora.sumaNuestra(5,5));

    }
}
