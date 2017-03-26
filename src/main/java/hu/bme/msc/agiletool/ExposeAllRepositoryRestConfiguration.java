package hu.bme.msc.agiletool;

import hu.bme.msc.agiletool.model.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class ExposeAllRepositoryRestConfiguration extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        config.exposeIdsFor(BacklogItem.class);
        config.exposeIdsFor(User.class);
        config.exposeIdsFor(UserStory.class);
        config.exposeIdsFor(Task.class);
        config.exposeIdsFor(Bug.class);
        config.exposeIdsFor(Project.class);
        config.exposeIdsFor(Dashboard.class);
    }


//    @Override
//    protected void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
//        config.exposeIdsFor(BacklogItem.class);
//    }

//    @Override
//    public boolean isIdExposedFor(Class<?> domainType) {
//        return true;
//    }

}
