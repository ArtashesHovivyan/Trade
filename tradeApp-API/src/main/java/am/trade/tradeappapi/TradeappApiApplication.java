package am.trade.tradeappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"am.trade.tradeappapi.*", "am.trade.tradeappcommon.*"})
@EntityScan(basePackages = "am.trade.tradeappcommon.model")
@EnableJpaRepositories(basePackages = "am.trade.tradeappcommon.repository")

public class TradeappApiApplication{

    public static void main(String[] args) {
        SpringApplication.run(TradeappApiApplication.class, args);
    }

}
