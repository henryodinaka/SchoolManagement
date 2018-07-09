package sch.man.com.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Juliet
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "sch.man.com")
public class SchoolConfig {
    
}
