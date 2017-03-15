package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.UserStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RepositoryRestResource(path = "userstory", collectionResourceRel = "userstory")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface UserStoryRepository extends MongoRepository<UserStory, String> {

    List<UserStory> findById(@RequestBody List<String> userstoryIds);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    <S extends UserStory> List<S> save(Iterable<S> entities);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    <S extends UserStory> S save(S entity);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    void delete(String s);

    @Override
    @PreAuthorize("hasAuthority('PO')")
    void delete(UserStory entity);
}
