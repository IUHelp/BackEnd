package edu.iu.iuhelp.models;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sujeet on 11/29/16.
 */

@Component
public class MatchedWith {

    private int queryIndex;

    public int getQueryIndex() {
        return queryIndex;
    }

    public void setQueryIndex(Integer queryIndex) {
        this.queryIndex = queryIndex;
    }

    public List<String> getMatchedWords() {
        return matchedWords;
    }

    public void setMatchedWords(List<String> matchedWords) {
        this.matchedWords = matchedWords;
    }

    public Integer getTotalMatchedWord() {
        return totalMatchedWord;
    }

    public void setTotalMatchedWord(Integer totalMatchedWord) {
        this.totalMatchedWord = totalMatchedWord;
    }

    private List<String> matchedWords;
    private Integer totalMatchedWord;

}
