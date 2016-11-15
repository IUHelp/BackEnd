package edu.iu.iuhelp.controllers;

<<<<<<< HEAD
import edu.iu.iuhelp.models.ResultModel;
import org.json.JSONObject;
=======
import java.io.IOException;
import java.util.List;

import org.apache.lucene.queryparser.classic.ParseException;
>>>>>>> 893a7510f03dfbd26145b57628232191d4da4eb0
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

<<<<<<< HEAD
import java.util.ArrayList;
=======
import edu.iu.iuhelp.services.PageResult;
>>>>>>> 893a7510f03dfbd26145b57628232191d4da4eb0

/**
 * Created by Sujeet Kumar on 11/4/16.
 */

@RestController
public class SearchController {

<<<<<<< HEAD
    @Autowired
    private ResultModel resultModel;

    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery){


        JSONObject jsonObject = new JSONObject();
        ArrayList<String> list = new ArrayList<String>();
        list.add("Dummy link 1");
        list.add("Dummy link 2");
        resultModel.setTextResult(" Dummy Result");
        resultModel.setLinksResult(list);

        jsonObject.put("results",resultModel);
        return jsonObject.toString();
=======
	@Autowired
	PageResult pageResult;
	
    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery) throws IOException, ParseException{
    	List<String> resultList = pageResult.getResult(searchQuery);
    	
        return resultList.toString();
>>>>>>> 893a7510f03dfbd26145b57628232191d4da4eb0
    }

}
