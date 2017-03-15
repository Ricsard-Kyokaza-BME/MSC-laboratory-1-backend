package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.Bug;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestResource(path = "bug", collectionResourceRel = "bug")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface BugRepository extends MongoRepository<Bug, String>{

    List<Bug> findById(@RequestBody List<String> bugIds);

}
