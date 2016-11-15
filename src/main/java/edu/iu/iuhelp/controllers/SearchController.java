package edu.iu.iuhelp.controllers;


import edu.iu.iuhelp.models.ResultModel;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Sujeet Kumar on 11/4/16.
 */

@RestController
public class SearchController {


    @Autowired
    private ResultModel resultModel;

    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery){

        JSONObject jsonObject = new JSONObject();
        try {
            System.out.println(" search controller ");
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayList<String> list = new ArrayList<String>();
            list.add("Dummy link 1");
            list.add("Dummy link 2");
            resultModel.setTextResult(" Dummy Result");
            resultModel.setLinksResult(list);
            String jsonValue = objectMapper.writeValueAsString(resultModel);
            jsonObject.put("results", jsonValue);
        }catch (IOException e){

        }
        return jsonObject.toString();
    }

}
