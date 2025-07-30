package com.example.junit_indexcontroller.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

public class IndexControllerTest {
    @Test
    public void welcomeTest() {
        IndexController indexController = new IndexController();
        String[] paramArray = null;
        String resultActual="El array esta vacio";
        String resultExpected=indexController.welcome(paramArray);
        Assertions.assertEquals(resultActual,resultExpected);
    }

    @Test
    public void arraySinDatosTest() {
        IndexController indexController = new IndexController();
        String[] paramArray = new String[3];
        String resultActual="param[0]null\n"+"param[1]null\n"+"param[2]null\n";
        String resultExpected=indexController.welcome(paramArray);
        Assertions.assertEquals(resultActual,resultExpected);
    }

    @Test
    public void arrayConDatos() {
        IndexController indexController = new IndexController();
        String[] paramArray = new String[]{"java","hola","angular"};
        String resultActual="param[0]java\n"+"param[1]hola\n"+"param[2]angular\n";
        String resultExpected=indexController.welcome(paramArray);
        Assertions.assertEquals(resultActual,resultExpected);
    }
}
