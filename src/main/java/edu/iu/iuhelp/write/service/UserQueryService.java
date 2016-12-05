package edu.iu.iuhelp.write.service;

import edu.iu.iuhelp.models.UserQueryModel;
import org.springframework.stereotype.Service;

/**
 * Created by Sujeet on 12/2/16.
 */


public interface UserQueryService {


    public void insertUserQueries(UserQueryModel userQueryModel);
    public Iterable<UserQueryModel> getAllUserQueries();
}
