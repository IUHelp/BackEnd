package edu.iu.iuhelp.Repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import edu.iu.iuhelp.models.Feedback;
public interface FeedbackRepository extends MongoRepository<Feedback, String>{


}
