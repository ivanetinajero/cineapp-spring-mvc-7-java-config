package net.itinajero.app.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "net.itinajero.app.repository")
@ComponentScan(basePackages = {"net.itinajero.app.controller", "net.itinajero.app.service"})
public class RootContextConfig implements WebMvcConfigurer {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        ds.setUrl("jdbc:mysql://127.0.0.1:3308/cineapp?useSSL=false&serverTimezone=America/Mexico_City&allowPublicKeyRetrieval=true");
        ds.setUsername("root"); // Cambia por tu usuario
        ds.setPassword("admin"); // Cambia por tu contraseña
        return ds;
    }

    @Bean
	public JpaVendorAdapter jpaVendorAdapter(){		
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(false);
		adapter.setShowSql(true);
		return adapter;		
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();		
		em.setPackagesToScan(new String[] { "net.itinajero.app.model" } );
		em.setJpaVendorAdapter(jpaVendorAdapter());
		em.setDataSource(dataSource());
		return em;
	}
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

}
