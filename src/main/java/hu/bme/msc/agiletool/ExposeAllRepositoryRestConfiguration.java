package hu.bme.msc.agiletool;

import hu.bme.msc.agiletool.model.Bug;
import hu.bme.msc.agiletool.model.Task;
import hu.bme.msc.agiletool.model.User;
import hu.bme.msc.agiletool.model.UserStory;
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
