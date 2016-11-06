package edu.iu.iuhelp.edu.iu.iuhelp.controllers;

import edu.iu.iuhelp.edu.iu.iuhelp.models.SearchModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Sujeet Kumar on 11/4/16.
 */

@RestController
public class SearchController {


    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery){


        return "Dummy Search result";
    }

}
