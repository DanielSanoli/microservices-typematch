package br.com.typematch.config;

import br.com.typematch.config.PokeApiPropertiesConfig;
import br.com.typematch.service.DynamicTypeEffectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TypeChartWarmup {

    private static final Logger log = LoggerFactory.getLogger(TypeChartWarmup.class);

    @Bean
    ApplicationRunner warmupTypes(DynamicTypeEffectService repo, PokeApiPropertiesConfig props) {
        return args -> {
            if (props.isWarmupOnStartup()) {
                log.info("Warm-up: loading type chart from PokeAPI...");
                repo.refreshAll();
            } else {
                log.info("Warm-up disabled. Type chart will be loaded on demand.");
            }
        };
    }
}