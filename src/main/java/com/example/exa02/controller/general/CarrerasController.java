package com.example.exa02.controller.general;

import com.example.exa02.dto.CarrerasDTO;
import com.example.exa02.service.general.service.CarrerasService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/carreras")
@CrossOrigin(origins = "http://localhost:4200")
public class CarrerasController {
    private final CarrerasService carrerasService;

    @GetMapping
    public ResponseEntity<List<CarrerasDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(carrerasService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarrerasDTO> read(@PathVariable Long id) throws ServiceException {
        CarrerasDTO dto = carrerasService.read(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CarrerasDTO> create(@RequestBody CarrerasDTO carrerasDTO) throws ServiceException {
        CarrerasDTO created = carrerasService.create(carrerasDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarrerasDTO> update(@PathVariable Long id, @RequestBody CarrerasDTO carrerasDTO) throws ServiceException {
        CarrerasDTO updated = carrerasService.update(id, carrerasDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        carrerasService.delete(id);
        return ResponseEntity.noContent().build();
    }
}