package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.BacklogItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BacklogItemRepository extends MongoRepository<BacklogItem, String>{

}
