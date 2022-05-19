package com.accountpatrol.api.pentadata.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {
    @RequestMapping("/")
    public String getHome(){
        return "index";
    }
}
