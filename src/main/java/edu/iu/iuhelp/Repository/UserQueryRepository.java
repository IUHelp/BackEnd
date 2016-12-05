package edu.iu.iuhelp.Repository;


import edu.iu.iuhelp.models.UserQueryModel;
import org.springframework.data.domain.*;
import org.springframework.data.repository.*;




/**
 * Created by Sujeet on 12/2/16.
 */

@org.springframework.stereotype.Repository
public interface UserQueryRepository extends  CrudRepository<UserQueryModel,String>{

    UserQueryModel findByTempUserUUID(String tempUserUUID);

}
