package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!\n";
    }

    // 動作確認のためのメソッドだったため、コメントアウト
    // @GetMapping("/country")
    // public String country() {
    //     String ret = "japan>" +"tokyo\n";
    //     return ret;
    // }
}
