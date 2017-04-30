package hu.bme.msc.agiletool.controller;

import hu.bme.msc.agiletool.model.BacklogItem;
import hu.bme.msc.agiletool.repository.BugRepository;
import hu.bme.msc.agiletool.repository.TaskRepository;
import hu.bme.msc.agiletool.repository.UserStoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BacklogItemController {

    @Autowired
    private BugRepository bugRepository;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserStoryRepository userStoryRepository;

    @RequestMapping(value = "/backlog-item", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    Map<String, List> getAllBacklogItem() {
        Map<String, List> allItem = new HashMap<>();
        allItem.put("userStory", userStoryRepository.findAll());
        allItem.put("task", taskRepository.findAll());
        allItem.put("bug", bugRepository.findAll());
        return allItem;
    }

    public static void mapByStatus(List<? extends BacklogItem> items,
                                   Map<String, Map<String, List<BacklogItem>>> target,
                                   String subTarget) {
        Map<String, List<BacklogItem>> subMap = new HashMap<>();
        subMap.put("backlog", new ArrayList<>());
        subMap.put("todo", new ArrayList<>());
        subMap.put("inProgress", new ArrayList<>());
        subMap.put("done", new ArrayList<>());
        if (items != null) {
            for (BacklogItem item : items) {
                switch (item.getStatus()) {
                    case BACKLOG:
                        subMap.get("backlog").add(item);
                        break;
                    case TODO:
                        subMap.get("todo").add(item);
                        break;
                    case IN_PROGRESS:
                        subMap.get("inProgress").add(item);
                        break;
                    case DONE:
                        subMap.get("done").add(item);
                        break;
                    default:
                        break;
                }
            }

            target.put(subTarget, subMap);
        }
    }
}
