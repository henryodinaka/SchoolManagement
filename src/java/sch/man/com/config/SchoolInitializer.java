package sch.man.com.config;

import com.sun.faces.config.FacesInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 * @author Juliet
 */
public class SchoolInitializer extends FacesInitializer implements WebApplicationInitializer{

    @Override
    public void onStartup(ServletContext sc) throws ServletException {
        
        AnnotationConfigWebApplicationContext annotationCfg = new AnnotationConfigWebApplicationContext();
        annotationCfg.setConfigLocation("sch.man.com.config"); 
        sc.addListener(new ContextLoaderListener(annotationCfg));
        ServletRegistration.Dynamic dispatcher = sc.addServlet("mvc-servlet", new DispatcherServlet(annotationCfg));
        dispatcher.setLoadOnStartup(1);
    }
}
