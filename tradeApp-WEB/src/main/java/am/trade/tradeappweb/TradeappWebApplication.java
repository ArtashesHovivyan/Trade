package am.trade.tradeappweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@ComponentScan(basePackages = {"am.trade.tradeappweb.*","am.trade.tradeappcommon.*"})
@EntityScan(basePackages = "am.trade.tradeappcommon.model")
@EnableJpaRepositories(basePackages = "am.trade.tradeappcommon.repository")
public class TradeappWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(TradeappWebApplication.class, args);
    }

}
