package br.com.typematch.service;

import br.com.typematch.dto.PokemonDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokedexClientService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PokedexClientService(RestTemplate restTemplate,
                         @Value("${pokedex.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl.endsWith("/") ? baseUrl.substring(0, baseUrl.length() - 1) : baseUrl;
    }

    public PokemonDTO getPokemon(String idOrName) {
        String url = String.format("%s/%s", baseUrl, idOrName);
        try {
            return restTemplate.getForObject(url, PokemonDTO.class);
        } catch (HttpClientErrorException.NotFound nf) {
            return null;
        }
    }
}
