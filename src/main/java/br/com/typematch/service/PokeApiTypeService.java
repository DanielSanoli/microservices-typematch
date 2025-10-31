package br.com.typematch.service;

import br.com.typematch.config.PokeApiPropertiesConfig;
import br.com.typematch.dto.PokeApiTypeResponseDTO;
import br.com.typematch.dto.TypeListResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiTypeService {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PokeApiTypeService(RestTemplate restTemplate, PokeApiPropertiesConfig props) {
        this.restTemplate = restTemplate;
        String base = props.getBaseUrl();
        this.baseUrl = base.endsWith("/") ? base.substring(0, base.length()-1) : base;
    }

    public TypeListResponseDTO listTypes(int limit) {
        String url = String.format("%s/type?limit=%d", baseUrl, limit);
        return restTemplate.getForObject(url, TypeListResponseDTO.class);
    }

    public PokeApiTypeResponseDTO getType(String nameOrId) {
        String url = String.format("%s/type/%s", baseUrl, nameOrId);
        return restTemplate.getForObject(url, PokeApiTypeResponseDTO.class);
    }
}
