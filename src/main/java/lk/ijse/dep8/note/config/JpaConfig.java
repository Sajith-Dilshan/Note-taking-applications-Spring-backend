package lk.ijse.dep8.note.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JpaConfig {

    private final Environment env;

    public JpaConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource ds,
                                                                       JpaVendorAdapter jpaVendorAdapter) {
        final LocalContainerEntityManagerFactoryBean lcemf = new LocalContainerEntityManagerFactoryBean();
        lcemf.setDataSource(ds);
        lcemf.setJpaVendorAdapter(jpaVendorAdapter);
        lcemf.setPackagesToScan("lk.ijse.dep8.note.entity");
        return lcemf;
    }

    @Bean
    public JpaVendorAdapter jpaVendorAdapter() {
        final HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setShowSql(env.getProperty("jpa.show-sql", Boolean.class, false));
        jpaVendorAdapter.setGenerateDdl(env.getProperty("jpa.generate-ddl", Boolean.class, false));
        jpaVendorAdapter.setDatabasePlatform(env.getRequiredProperty("jpa.dialect"));
        return jpaVendorAdapter;
    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl(env.getRequiredProperty("hikari.jdbc-url"));
        hikariDataSource.setUsername(env.getRequiredProperty("hikari.username"));
        hikariDataSource.setPassword(env.getRequiredProperty("hikari.password"));
        hikariDataSource.setDriverClassName(env.getRequiredProperty("hikari.driver-classname"));
        hikariDataSource.setMaximumPoolSize(env.getRequiredProperty("hikari.max-pool-size", Integer.class));
        return hikariDataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        return new JpaTransactionManager(emf);
    }
}
