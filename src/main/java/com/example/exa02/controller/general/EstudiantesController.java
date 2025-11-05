package com.example.exa02.controller.general;

import com.example.exa02.dto.EstudiantesDTO;
import com.example.exa02.service.general.service.EstudiantesService;
import lombok.RequiredArgsConstructor;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "http://localhost:4200")
public class EstudiantesController {
    private final EstudiantesService estudiantesService;

    @GetMapping
    public ResponseEntity<List<EstudiantesDTO>> listAll() throws ServiceException {
        return ResponseEntity.ok(estudiantesService.listAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudiantesDTO> read(@PathVariable Long id) throws ServiceException {
        EstudiantesDTO dto = estudiantesService.read(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EstudiantesDTO> create(@RequestBody EstudiantesDTO estudiantesDTO) throws ServiceException {
        EstudiantesDTO created = estudiantesService.create(estudiantesDTO);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudiantesDTO> update(@PathVariable Long id, @RequestBody EstudiantesDTO estudiantesDTO) throws ServiceException {
        EstudiantesDTO updated = estudiantesService.update(id, estudiantesDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceException {
        estudiantesService.delete(id);
        return ResponseEntity.noContent().build();
    }
}