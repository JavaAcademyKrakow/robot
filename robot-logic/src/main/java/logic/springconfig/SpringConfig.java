package logic.springconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = "logic")
class SpringConfig {

    /**
     * The main Spring configuration of the module. It is used to scan for REST controller and also
     * create bean that represents a cyclical task.
     * @return ScheduledBean object which is a task to be performed cyclically every four hours.
     */
    @Bean
    @Scope("singleton")
    public ScheduledBean createBean() {
        return new ScheduledBean();
    }

}
