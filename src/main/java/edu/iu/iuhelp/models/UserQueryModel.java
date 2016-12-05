package edu.iu.iuhelp.models;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Sujeet on 12/2/16.
 */

@Component
public class UserQueryModel {

    @Id
    private String tempUserUUID;

    public String getTempUserUUID() {
        return tempUserUUID;
    }

    public void setTempUserUUID(String tempUserUUID) {
        this.tempUserUUID = tempUserUUID;
    }

    public List<String> getQueries() {
        return queries;
    }

    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    private List<String> queries;

}
