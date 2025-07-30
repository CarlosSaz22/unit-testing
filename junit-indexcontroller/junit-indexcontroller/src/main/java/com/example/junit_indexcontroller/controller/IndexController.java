package com.example.junit_indexcontroller.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
    @PostMapping("/welcome")
    @ResponseBody
    public String welcome(@RequestParam(required = false, name = "param") String[] paramArray) {
        String msg = "";
        int i = 0;
        if (paramArray == null) {
            msg = "El array esta vacio";
        } else {
            for (String paramArrayItem : paramArray) {
                msg += "param[" +  i+ "]" + paramArrayItem + "\n";
                i++;
            }
        }
        return msg;
    }
}
