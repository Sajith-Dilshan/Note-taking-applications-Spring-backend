package lk.ijse.dep8.note.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import java.util.Properties;

@Configuration
@Import(JpaConfig.class)
public class WebRootConfig {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public static YamlPropertiesFactoryBean yamlPropertiesFactory(ConfigurableEnvironment env) {
        YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setResources(new ClassPathResource("application.yaml"));
        Properties yamlProps = yamlFactory.getObject();
        env.getPropertySources().addLast(new PropertiesPropertySource("yaml", yamlProps));
        return yamlFactory;
    }

}
