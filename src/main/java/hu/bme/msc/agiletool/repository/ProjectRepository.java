package hu.bme.msc.agiletool.repository;


import hu.bme.msc.agiletool.model.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(path = "project", collectionResourceRel = "project")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findById(@Param("id") String id);
    List<Project> findByNameContains(@Param("name") String name);

}
