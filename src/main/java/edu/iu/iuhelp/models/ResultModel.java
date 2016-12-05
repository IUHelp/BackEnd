package edu.iu.iuhelp.models;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sujeet on 11/15/16.
 */

@Component
public class ResultModel {

    private String textResult;
    private List<ResultDocs> linksResult;

    public String getTextResult() {
        return textResult;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public List<ResultDocs> getLinksResult() {
        return linksResult;
    }

    public void setLinksResult(List<ResultDocs> linksResult) {
        this.linksResult = linksResult;
    }




}
