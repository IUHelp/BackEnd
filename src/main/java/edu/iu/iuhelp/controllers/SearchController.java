package edu.iu.iuhelp.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.iu.iuhelp.services.PageResult;

/**
 * Created by Sujeet Kumar on 11/4/16.
 */

@RestController
public class SearchController {

	@Autowired
	PageResult pageResult;
	
    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery) throws IOException, ParseException{
    	List<String> resultList = pageResult.getResult(searchQuery);
    	
        return resultList.toString();
    }

}
