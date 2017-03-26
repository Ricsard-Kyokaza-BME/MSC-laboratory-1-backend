package hu.bme.msc.agiletool.repository;


import hu.bme.msc.agiletool.model.Dashboard;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(path = "dashboard", collectionResourceRel = "dashboard")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface DashboardRepository extends MongoRepository<Dashboard, String> {

    List<Dashboard> findById(@Param("id") String id);
}
