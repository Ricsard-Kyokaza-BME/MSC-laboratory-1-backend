package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.Sprint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RepositoryRestResource(path = "sprint", collectionResourceRel = "sprint")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface SprintRepository extends MongoRepository<Sprint, String> {
    List<Sprint> findById(@Param("id") String id);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    <S extends Sprint> List<S> save(Iterable<S> entities);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    <S extends Sprint> S save(S entity);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    void delete(String s);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    void delete(Sprint entity);
}
