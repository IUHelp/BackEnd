package edu.iu.iuhelp.edu.iu.iuhelp.models;

import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by Sujeet on 11/4/16.
 */

@Component
public class SearchModel implements Serializable {

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    private String searchQuery;


}
