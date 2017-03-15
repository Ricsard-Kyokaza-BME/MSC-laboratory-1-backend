package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestResource(path = "task", collectionResourceRel = "task")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface TaskRepository extends MongoRepository<Task, String> {

    List<Task> findById(@RequestBody List<String> taskIds);

}
