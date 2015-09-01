package su.vistar.springwebsocket.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author dantonov
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebConfig.class, WebSocketConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
       
        return new String[] { "/" };
    }
    
}
