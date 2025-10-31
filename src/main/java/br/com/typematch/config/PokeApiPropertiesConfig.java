package br.com.typematch.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "pokeapi")
public class PokeApiPropertiesConfig {
    private String baseUrl = "https://pokeapi.co/api/v2";
    private boolean warmupOnStartup = true;
    private long cacheTtlSeconds = 86400;

    public String getBaseUrl() { return baseUrl; }
    public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

    public boolean isWarmupOnStartup() { return warmupOnStartup; }
    public void setWarmupOnStartup(boolean warmupOnStartup) { this.warmupOnStartup = warmupOnStartup; }

    public long getCacheTtlSeconds() { return cacheTtlSeconds; }
    public void setCacheTtlSeconds(long cacheTtlSeconds) { this.cacheTtlSeconds = cacheTtlSeconds; }
}
