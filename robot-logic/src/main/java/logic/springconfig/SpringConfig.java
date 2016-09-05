package logic.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "logic")
public class SpringConfig {

    @Bean
    @Scope("singleton")
    public ScheduledBean createBean() {
        return new ScheduledBean();
    }

}
