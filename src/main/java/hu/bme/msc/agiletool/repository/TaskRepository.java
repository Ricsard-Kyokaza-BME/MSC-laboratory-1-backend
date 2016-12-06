package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
}
