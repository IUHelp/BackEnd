package edu.iu.iuhelp.controllers;

import edu.iu.iuhelp.models.ResultModel;
import edu.iu.iuhelp.models.UserQueryModel;
import edu.iu.iuhelp.services.*;
import edu.iu.iuhelp.write.service.impl.UserQueryServiceImpl;
import edu.iu.iuhelp.services.SearchIndex;
import org.apache.lucene.queryparser.classic.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.*;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Sujeet Kumar on 11/4/16.
 */

@RestController
public class SearchController {

    @Autowired
    private ResultModel resultModel;

    @Autowired
    private SearchIndex searchIndex;

    @Autowired
    private SimiliarQuery similiarQuery;

    @Autowired
    private UserQueryServiceImpl userQueryService;

    @Autowired
    private StopWordRemoval stopWordRemoval;

    @Autowired
    private ExtractUrl extractUrl;

    @Autowired
    private ExtractDiv extractDiv;

    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery,
                         @RequestParam(value="tempUserUUID",required = true) String tempUserUUID){

        System.out.println(" hitting search "+searchQuery);
        System.out.println(" tempUserUUID "+tempUserUUID);

        UserQueryModel userQueryModel = new UserQueryModel();
        List<String> ls = new ArrayList<String>();
        ls.add(searchQuery);
        userQueryModel.setTempUserUUID(tempUserUUID);
        userQueryModel.setQueries(ls);
     //   userQueryService.insertUserQueries(userQueryModel);

        JSONObject jsonObject = new JSONObject();
        String jsonValue = null;

        try{
            List<String> list =  searchIndex.getResult(searchQuery);

            String result = "";

            if(list.size()!=0){
                result = extractDiv.getRelevantDiv(list.get(0),searchQuery);
//                extractUrl.getUrlContents(list.get(0));
            }

            ObjectMapper objectMapper = new ObjectMapper();
            resultModel.setTextResult(result);
            resultModel.setLinksResult(list);


            jsonValue = objectMapper.writeValueAsString(resultModel);
            jsonObject.put("results", jsonValue);
        }catch (ParseException e){
            e.printStackTrace();
        }
        catch (IOException e  ) {
            e.printStackTrace();
        }

     //   System.out.println(jsonObject.toString().replaceAll("\\",""));
        System.out.println(jsonValue);
//        return jsonObject.toString().replaceAll("\\.","");
        return jsonValue;
    }
    
    private static String generateContent(String url){
    	// TODO Auto-generated method stub
		
        System.out.println("Fetching "+url);
        String result = null;
        try {
        		         
            org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
			Element body = doc.body();
			body.select("script, header, style, .hidden, form .search-bar").remove();
			result = body.text();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
        return result;
    	
    }

    @RequestMapping(value="/test")
    public String stringValue() throws Exception {

//        Map<String,List<String>> stringListMap = new HashMap<String,List<String>>();
//        List<String> ls = new ArrayList<String>();
//
//        ls.add("How make AJAX call get JSON data");
//        ls.add("How handle JSON data after AJAX call");
//        ls.add("How JSON data after AJAX call");
//        stringListMap.put("userone",ls);
//        similiarQuery.processSimiliarQuery(stringListMap);
        stopWordRemoval.removeStopWords("Dymmu");
        return "";
    }

    @RequestMapping(value="/updateSuggestion")
    public String improveSuggestion(){

        Iterator<UserQueryModel> userQueryModelIterator =  userQueryService.getAllUserQueries().iterator();
        Map<String,List<String>> stringListMap = new HashMap<String,List<String>>();

        while(userQueryModelIterator.hasNext()){

            UserQueryModel userQueryModel = userQueryModelIterator.next();

            stringListMap.put(userQueryModel.getTempUserUUID(),userQueryModel.getQueries());
        }

//        List<String> ls = new ArrayList<String>();
//
//        ls.add("How make AJAX call get JSON data");
//        ls.add("How handle JSON data after AJAX call");
//        ls.add("How JSON data after AJAX call");
//        stringListMap.put("userone",ls);
          Set<String> suggestion = similiarQuery.processSimiliarQuery(stringListMap);

        for (String value :suggestion) {

            System.out.println(value);
        }

        return "";
    }

}
