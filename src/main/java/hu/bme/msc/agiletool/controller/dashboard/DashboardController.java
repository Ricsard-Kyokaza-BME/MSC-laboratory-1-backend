package hu.bme.msc.agiletool.controller.dashboard;

import hu.bme.msc.agiletool.controller.PredefineBaseController;
import hu.bme.msc.agiletool.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class DashboardController implements PredefineBaseController {
    @Autowired
    private DashboardRepository dashboardRepository;

    @RequestMapping(value = "/dashboard/find", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('PO','USER')")
    @ResponseBody
    ResponseEntity findDashboards(@RequestBody List<String> dashboards) {
        if (dashboards == null){
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(dashboardRepository.findAll(dashboards),HttpStatus.OK);
    }
}
