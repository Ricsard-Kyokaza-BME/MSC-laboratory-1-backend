package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BugRepository extends MongoRepository<Bug, String>{

}
