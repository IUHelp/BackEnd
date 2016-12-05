package edu.iu.iuhelp.write.service.impl;

import edu.iu.iuhelp.Repository.UserQueryRepository;
import edu.iu.iuhelp.models.UserQueryModel;
import edu.iu.iuhelp.write.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

/**
 * Created by Sujeet on 12/2/16.
 */

@Service
public class UserQueryServiceImpl implements UserQueryService{

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Override
    public void insertUserQueries(UserQueryModel userQueryModel) {

        UserQueryModel userQueryModel1 = userQueryRepository.findByTempUserUUID(userQueryModel.getTempUserUUID());
        if(userQueryModel1 != null){
            userQueryModel1.getQueries().addAll(userQueryModel.getQueries());
            userQueryRepository.save(userQueryModel1);
        }else{
            userQueryRepository.save(userQueryModel);
        }
    }

    @Override
    public Iterable<UserQueryModel> getAllUserQueries() {

        return userQueryRepository.findAll();
    }


}
