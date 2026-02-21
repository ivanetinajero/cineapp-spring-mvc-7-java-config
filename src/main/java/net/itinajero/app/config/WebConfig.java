package net.itinajero.app.config;

import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import jakarta.servlet.FilterRegistration;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;
import net.itinajero.app.security.DatabaseSecurityConfig;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		// Registrar el filtro DelegatingFilterProxy para Spring Security
		FilterRegistration.Dynamic securityFilter = servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy());
		securityFilter.addMappingForUrlPatterns(null, false, "/*");
		servletContext.setSessionTimeout(180); // minutos
		super.onStartup(servletContext);
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { RootContextConfig.class, DatabaseSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { DispatcherServletConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected void customizeRegistration(Dynamic registration) {
		// Requerido para Uploads
        registration.setMultipartConfig(getMultipartConfigElement());
        /**
         * REQUERIMIENTO 1: PARA PERSONALIZAR LA PAGINA DE ERROR HTTP 404 (JAVA CONFIG)
         * https://stackoverflow.com/questions/13356549/handle-error-404-with-spring-controller
         */
        registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
	}

	// Requerido para Uploads
	private MultipartConfigElement getMultipartConfigElement(){		
		//String LOCATION = "C:/tmp"; // Windows
		String LOCATION = "/tmp"; // Linux
	    long MAX_FILE_SIZE = 1024 * 1024 * 300; //300MB
	    long MAX_REQUEST_SIZE = 1024 * 1024 * 300; //300MB
	    int FILE_SIZE_THRESHOLD = 0;
        MultipartConfigElement multipartConfigElement = new MultipartConfigElement(LOCATION, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        return multipartConfigElement;
    }
		
}
