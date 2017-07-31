package rostelecom.sha.hrmtech2.controller2;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import rostelecom.sha.hrmtech2.controller2.storage.StorageProperties;
import rostelecom.sha.hrmtech2.controller2.storage.StorageService;

/**
 * Точка запуска spring-boot приложения
 * для загрузки, парсинга и создания графа
 */
@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
            storageService.deleteAll();
            storageService.init();
		};
	}
}
