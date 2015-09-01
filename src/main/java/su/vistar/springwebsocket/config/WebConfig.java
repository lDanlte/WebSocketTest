package su.vistar.springwebsocket.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 *
 * @author dantonov
 */

@Configuration
@ComponentScan(basePackages = {"su.vistar.springwebsocket.controllers", "su.vistar.springwebsocket.service"})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter{
    
    
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer){
        Map<String, MediaType> mediaTypes = new HashMap<>();
        mediaTypes.put("json", MediaType.APPLICATION_JSON);
        mediaTypes.put("html", MediaType.TEXT_HTML);
        configurer.mediaTypes(mediaTypes);
    }
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry resources){
        resources.addResourceHandler("/lib/**").addResourceLocations("/WEB-INF/lib/");
    }
    
    @Bean
    public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager){
        ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
        resolver.setContentNegotiationManager(manager);
        List<ViewResolver> resolvers = new ArrayList<>();
        
        resolvers.add(jsonViewResolver());
        //resolvers.add(htmlViewResolver());
        resolvers.add(jspViewResolver());
        
        resolver.setViewResolvers(resolvers);
        return resolver;
    }
    
    @Bean
    public ViewResolver jsonViewResolver(){
        return (String viewName, Locale locale) -> {
            MappingJackson2JsonView view = new MappingJackson2JsonView();
            view.setPrettyPrint(true);
            return view;
        };
    }
    
    /*@Bean
    public ViewResolver htmlViewResolver(){
        InternalResourceViewResolver resolver  = new InternalResourceViewResolver();
        
        resolver.setPrefix("/WEB-INF/html/");
        resolver.setSuffix(".html");
        
        return resolver;
    }*/
    
    @Bean
    public ViewResolver jspViewResolver(){
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        
        resolver.setPrefix("/WEB-INF/jsp/");
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        resolver.setContentType("text/html;charset=UTF-8");
        
        return resolver;
    }
    
    
    
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){
        configurer.enable();
    }
    
}
