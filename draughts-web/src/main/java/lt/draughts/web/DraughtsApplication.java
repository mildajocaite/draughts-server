package lt.draughts.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({
        "lt.draughts"
})
@EntityScan({
        "lt.draughts"
})
@EnableJpaRepositories("lt.draughts")
@EnableAspectJAutoProxy
public class DraughtsApplication {
    public static void main(String[] args) {
        SpringApplication.run(DraughtsApplication.class, args);
    }
}