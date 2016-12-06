package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.UserStory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserStoryRepository extends MongoRepository<UserStory, String> {
}
