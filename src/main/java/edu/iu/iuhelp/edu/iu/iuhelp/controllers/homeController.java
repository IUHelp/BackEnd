package edu.iu.iuhelp.edu.iu.iuhelp.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Sujeet on 11/6/16.
 */

@Controller
public class homeController {

    @RequestMapping(value ="/")
    public String home(){

        System.out.println(" home ");
        return "index";
    }
}
