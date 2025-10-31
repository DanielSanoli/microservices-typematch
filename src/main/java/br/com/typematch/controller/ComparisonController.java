package br.com.typematch.controller;

import br.com.typematch.dto.ComparisonResultDTO;
import br.com.typematch.service.ComparisonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compare")
public class ComparisonController {

    private final ComparisonService service;

    public ComparisonController(ComparisonService service) {
        this.service = service;
    }

    @GetMapping("/{id1}/{id2}")
    public ResponseEntity<ComparisonResultDTO> compare(
            @PathVariable String id1,
            @PathVariable String id2
    ) {
        ComparisonResultDTO result = service.compare(id1, id2);
        return ResponseEntity.ok(result);
    }
}
