package sgabs.difa.currency_exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableConfigurationProperties(Configuration.class)
public class CurrencyExchangeApplication implements CommandLineRunner{
	Logger logger = LoggerFactory.getLogger(CurrencyExchangeApplication.class);
	private final Configuration configuration;

	public CurrencyExchangeApplication(Configuration configuration){
		this.configuration = configuration;
		logger.info("constructor******************\n\n\n ");
		logger.info(configuration.toString());
	}

	public static void main(String[] args) {
		SpringApplication.run(CurrencyExchangeApplication.class, args);
	}

	@Bean
	public TimedAspect timedAspect(MeterRegistry registry) {
		return new TimedAspect(registry);
	}

	@Override
	public void run(String... args) {



		logger.info("----------------------------------------");
		logger.info("Configuration properties");
		logger.info("   example.username is {}", configuration.getUsername());
		logger.info("   example.password is {}", configuration.getPassword());
		logger.info("----------------------------------------");
	}

}
