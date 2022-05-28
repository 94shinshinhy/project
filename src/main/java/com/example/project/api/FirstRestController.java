package com.example.project.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestAPI용 컨트롤러 데이터(JSON)을 반환
public class FirstRestController {

    @GetMapping("/api/hello")
    public String hello(){
        return "hello";
    }

}
