package edu.iu.iuhelp.controllers;
import edu.iu.iuhelp.models.ResultModel;
import edu.iu.iuhelp.services.SearchIndex;
import org.apache.lucene.queryparser.classic.ParseException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
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
    
    @RequestMapping(value ="/search",method = RequestMethod.GET)
    public String search(@RequestParam(value="searchQuery",required = true) String searchQuery){

        System.out.println(" hitting search");
        JSONObject jsonObject = new JSONObject();
        String jsonValue = null;

        try{
            List<String> list =  searchIndex.getResult(searchQuery);

            ObjectMapper objectMapper = new ObjectMapper();
            resultModel.setTextResult(" Dummy Result");
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

}
