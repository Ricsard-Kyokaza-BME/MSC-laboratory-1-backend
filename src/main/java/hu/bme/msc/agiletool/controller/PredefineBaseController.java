package hu.bme.msc.agiletool.controller;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public interface PredefineBaseController {
}
