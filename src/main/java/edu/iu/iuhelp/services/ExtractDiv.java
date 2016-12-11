package edu.iu.iuhelp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Prashanth on 12/3/16.
 */

@Service
public class ExtractDiv {

    @Autowired
    private ExtractUrl extractUrl;

    public String getRelevantDiv(String topUrl,String searchQuery){
        // calling nethra's code to fetch html page data as string

        String searchText = extractUrl.getUrlContents(topUrl);
        searchText = searchText.toLowerCase();


        // search query after stemming and removing stop word removal
        searchQuery = searchQuery.toLowerCase().trim();
        String[] searchQueryTokens = searchQuery.split("\\s+");
        for (int i = 0; i < searchQueryTokens.length; i++) {

            //splitting words. removing special characters within a word
            searchQueryTokens[i] = searchQueryTokens[i].replaceAll("[^\\w]", "");
        }



        // getting starting offset and ending offset
        int startOffset = searchText.lastIndexOf(searchQueryTokens[0]);
        int endOffset = searchText.lastIndexOf(searchQueryTokens[0]);

        for (int i=1;i<searchQueryTokens.length;i++){

            int index = searchText.indexOf(searchQueryTokens[i]);
             if (index >= endOffset){
                endOffset=index;
                continue;
            }

            if (index <= startOffset){
                startOffset = index;
            }
          }

        // getting starting index of <div> just before starting offset
        int index=0;
        int divIndex = searchText.indexOf("<");
        while (divIndex >= 0) {
            divIndex = searchText.indexOf("<", divIndex + 1);
            if (divIndex <= startOffset){
                index = divIndex;
            }else{
                break;
            }

        }
        divIndex = index;

        // getting starting index of </div> just after ending offset
        int closedivIndex = searchText.indexOf(">");

        while (closedivIndex >= 0) {
            closedivIndex = searchText.indexOf(">", closedivIndex + 1);
            if (closedivIndex >= endOffset){
                index = closedivIndex;
                break;
            }
        }

        // prints required text that is to be sent to front end for display
        String text = searchText.substring(divIndex, closedivIndex);
        return text;
    }
}
