package edu.iu.iuhelp.services;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by Sujeet on 12/3/16.
 */

@Service
public class Mapper {


    public void wordCount(String text, List<String> keywords){


        StringTokenizer stringTokenizer = new StringTokenizer(text);
        Map<String,Integer> wordCount = new HashMap<String,Integer>();

        while(stringTokenizer.hasMoreElements()){

            String nextWord = stringTokenizer.nextToken();
            if(wordCount.containsKey(nextWord)){

                wordCount.put(nextWord,wordCount.get(nextWord)+1);
            }else{
                wordCount.put(nextWord,1);
            }
        }

    }

    public double getScoreForThisID(Map<String,Integer> wordCount,List<String> keywords){


        double score;
        score = 0.0;

        for (String word : keywords) {

            if(wordCount.containsKey(word)){

                int count = wordCount.get(word);
                score += 10 + (count/1000);
            }
        }

        return score;
    }

}
