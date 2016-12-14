package config;

import controllers.MachineController;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import service.config.ServiceConfiguration;

/**
 * Created by pato on 13.12.2016.
 */
@EnableWebMvc
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {MachineController.class})
public class RestConfiguration extends WebMvcConfigurerAdapter {

}
