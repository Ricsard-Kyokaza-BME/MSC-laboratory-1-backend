package hu.bme.msc.agiletool.repository;

import hu.bme.msc.agiletool.model.BacklogStatus;
import hu.bme.msc.agiletool.model.UserStory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RepositoryRestResource(path = "userstory", collectionResourceRel = "userstory")
@PreAuthorize("hasAnyAuthority('PO','USER')")
public interface UserStoryRepository extends MongoRepository<UserStory, String> {

    List<UserStory> findById(@Param("id") String id);

    //Part of the BacklogItem abstract class. Need some smart work on it.
    //Not nice to write it on every type.
    List<UserStory> findByTitleContainingIgnoreCase(@Param("title") String title);
    List<UserStory> findByKeywordsContaining(@RequestParam List<String> keywords);
    List<UserStory> findByAssigneeContaining(@RequestParam List<String> assignee);
    List<UserStory> findByDependingContaining(@RequestParam List<String> depending);
    List<UserStory> findByStatus(@RequestParam BacklogStatus status);


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
