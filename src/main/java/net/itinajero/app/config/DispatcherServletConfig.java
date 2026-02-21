package net.itinajero.app.config;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.CacheControl;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="net.itinajero.app.controller")
public class DispatcherServletConfig implements WebMvcConfigurer{
	
	// Le indicamos a Spring MVC a donde debera ir a buscar las vistas (JSPs)
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setOrder(1);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {	
		// Mapeo de los recursos estaticos
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").
    	setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS)); // Set browser to use the cached version of the file for one year.               
    }

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
		resolver.setMaxPageSize(5);
		//resolver.setOneIndexedParameters(true);
		resolvers.add(resolver);
	}

	// Para habilitar Upload Files
	@Bean
	public MultipartResolver multipartResolver() {
	    return new StandardServletMultipartResolver();
	}
	
}
