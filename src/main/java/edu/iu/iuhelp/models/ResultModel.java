package edu.iu.iuhelp.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * Created by Sujeet on 11/15/16.
 */

@Component
public class ResultModel {

    private String textResult;

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public ArrayList<String> getLinksResult() {
        return linksResult;
    }

    public void setLinksResult(ArrayList<String> linksResult) {
        this.linksResult = linksResult;
    }

    private ArrayList<String> linksResult;


}
