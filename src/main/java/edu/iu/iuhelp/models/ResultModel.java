package edu.iu.iuhelp.models;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sujeet on 11/15/16.
 */

@Component
public class ResultModel {

    private String textResult;
    private List<String> linksResult;

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public List<String> getLinksResult() {
        return linksResult;
    }

    public void setLinksResult(List<String> linksResult) {
        this.linksResult = linksResult;
    }




}
