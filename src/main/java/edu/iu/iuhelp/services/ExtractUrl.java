package edu.iu.iuhelp.services;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Object;
import org.jsoup.Jsoup;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

/**
 * Created by Sujeet on 12/3/16.
 */

@Service
public class ExtractUrl {


    public String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        String linkInnerH = "";
        // many of these calls can throw exceptions, so i've just
        // wrapped them all in one try/catch statement.
        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();


        String HTMLPage = content.toString();
        org.jsoup.nodes.Document doc = Jsoup.parse(HTMLPage);
        Element link = doc.select("body").first();
        linkInnerH = link.html();
        linkInnerH = removeScriptTags(linkInnerH);

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return linkInnerH;
    }




    public String removeScriptTags(String message) {
        String scriptRegex = "<(/)?[ ]*script[^>]*>";
        Pattern pattern2 = Pattern.compile(scriptRegex);

        if(message != null) {
            Matcher matcher2 = pattern2.matcher(message);
            StringBuffer str = new StringBuffer(message.length());
            while(matcher2.find()) {
                matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
            }
            matcher2.appendTail(str);
            message = str.toString();
        }

        String commentRegex = "<(/)?[ ]*![^>]*>";
        Pattern pattern3 = Pattern.compile(commentRegex);

        if(message != null) {
            Matcher matcher2 = pattern3.matcher(message);
            StringBuffer str = new StringBuffer(message.length());
            while(matcher2.find()) {
                matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
            }
            matcher2.appendTail(str);
            message = str.toString();
        }

        String footerRegex = "<(/)?[ ]*footer[^>]*>";
        Pattern pattern4 = Pattern.compile(footerRegex);

        if(message != null) {
            Matcher matcher2 = pattern4.matcher(message);
            StringBuffer str = new StringBuffer(message.length());
            while(matcher2.find()) {
                matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
            }
            matcher2.appendTail(str);
            message = str.toString();
        }

        String headerRegex = "<(/)?[ ]*header[^>]*>";
        Pattern pattern5 = Pattern.compile(headerRegex);

        if(message != null) {
            Matcher matcher2 = pattern5.matcher(message);
            StringBuffer str = new StringBuffer(message.length());
            while(matcher2.find()) {
                matcher2.appendReplacement(str, Matcher.quoteReplacement(" "));
            }
            matcher2.appendTail(str);
            message = str.toString();
        }

        return message;
    }


}


