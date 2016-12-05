package edu.iu.iuhelp.services;

import edu.iu.iuhelp.models.MatchedWith;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by Sujeet on 11/29/16.
 */

@Service
public class SimiliarQuery {

    //Key is unique user string and value is list of queries fired by a user in a list
    public Set<String> processSimiliarQuery(Map<String,List<String>> queryListSet){

        Set<String> finalResult = new HashSet<String>();

        Iterator<Map.Entry<String,List<String>>> iterator = queryListSet.entrySet().iterator();

        //process each of the unique key and find the closed query
        while(iterator.hasNext()){
            Map.Entry<String,List<String>> singleUserQuery = iterator.next();
            String userUUID = singleUserQuery.getKey();
            something(userUUID,singleUserQuery.getValue(),finalResult);
        }

        return finalResult;
    }

    public void something(String userUUID, List<String> queryList,Set<String> finalResult){

        HashMap<String,List<Integer>> hashPool = new HashMap<String,List<Integer>>();
        for(int i =0; i < queryList.size();i++ ){

            Map<Integer,MatchedWith> matchedWith = new HashMap<Integer,MatchedWith>();
            String[] separateWords = queryList.get(i).split(" ");

            for(int j =0; j < separateWords.length;j++) {

                //key is current word which we are trying to check if it is already in the hash pool
                String keyWord = separateWords[j];

                if (hashPool.containsKey(keyWord)) {

                    //hash pool already have current word
                    List<Integer> queryListWithSameWord = hashPool.get(keyWord);

                    //update the matchedWith List
                    for(int k=0; k < queryListWithSameWord.size();k++){

                        updateMatchedWithList(matchedWith,k,keyWord);
                    }

                    //update the old list
                    queryListWithSameWord.add(i);
                    hashPool.put(keyWord,queryListWithSameWord);

                }else{

                    //hash pool doesn't have current word
                    List<Integer> queryIndex = new ArrayList<Integer>();
                    queryIndex.add(i);
                    hashPool.put(keyWord,queryIndex);
                }

            }
            similarResult(matchedWith,queryList,i,finalResult);
        }
    }


    public void updateMatchedWithList( Map<Integer,MatchedWith> matchedWith, int queryIndex, String word ){

        if(matchedWith.containsKey(queryIndex)){

            // User's other query has matching keyword , update the matching count and words matched
            MatchedWith matchedWithModel = matchedWith.get(queryIndex);

            matchedWithModel.setTotalMatchedWord(matchedWithModel.getTotalMatchedWord()+1);
            matchedWithModel.getMatchedWords().add(word);
            matchedWith.put(queryIndex,matchedWithModel);
        }else {

            MatchedWith matchedWithModel = new MatchedWith();
            matchedWithModel.setQueryIndex(queryIndex);
            matchedWithModel.setTotalMatchedWord(1);
            List<String> temp = new ArrayList<String>();
            temp.add(word);
            matchedWithModel.setMatchedWords(temp);
            matchedWith.put(queryIndex,matchedWithModel);
        }
    }

    public void matchingQuery(Map<Integer,MatchedWith> matchedWith,Set<String> finalResult , ArrayList<String> queryList){

       //find query with max matched count
        Iterator<Map.Entry<Integer,MatchedWith>> iterator = matchedWith.entrySet().iterator();

        while(iterator.hasNext()){

            Map.Entry<Integer,MatchedWith> mapEntry = iterator.next();
            Integer index = mapEntry.getKey();
            MatchedWith matchedWith1 = mapEntry.getValue();
            if(matchedWith1.getTotalMatchedWord() > 3){

            }
        }
    }
    
    public void similarResult(Map<Integer,MatchedWith> matchedWith,List<String> queryList,int queryOwnIndex,Set<String> commonQueryResult){

        for (MatchedWith matchedWithQuery : matchedWith.values()) {

            if(matchedWithQuery.getTotalMatchedWord() > 2){
                commonQueryResult.add(queryList.get(matchedWithQuery.getQueryIndex()));
                commonQueryResult.add(queryList.get(queryOwnIndex));
            }
        }
    }
}
