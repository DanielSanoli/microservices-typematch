package br.com.typematch.config;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate() {
        RestTemplate rt = new RestTemplate();

        ClientHttpRequestInterceptor correlationIdInterceptor = (request, body, execution) -> {
            String correlationId = MDC.get("correlationId");
            if (correlationId != null && !correlationId.isBlank()) {
                request.getHeaders().set("X-Correlation-Id", correlationId);
            }
            return execution.execute(request, body);
        };

        List<ClientHttpRequestInterceptor> interceptors = new ArrayList<>(rt.getInterceptors());
        interceptors.add(correlationIdInterceptor);
        rt.setInterceptors(interceptors);

        return rt;
    }
}
