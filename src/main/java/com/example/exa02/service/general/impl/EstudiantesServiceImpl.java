package com.example.exa02.service.general.impl;

import com.example.exa02.controller.error.ResourceNotFoundException;
import com.example.exa02.dto.EstudiantesDTO;
import com.example.exa02.entity.Carreras;
import com.example.exa02.entity.Estudiantes;
import com.example.exa02.mapper.CarrerasMapper;
import com.example.exa02.mapper.EstudiantesMapper;
import com.example.exa02.repository.CarrerasRepository;
import com.example.exa02.repository.EstudiantesRepository;
import com.example.exa02.service.general.service.EstudiantesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EstudiantesServiceImpl implements EstudiantesService {
    private final EstudiantesRepository estudiantesRepository;
    private final EstudiantesMapper estudiantesMapper;
    private final CarrerasMapper carrerasMapper;
    private final CarrerasRepository carrerasRepository;

    @Override
    public EstudiantesDTO create(EstudiantesDTO estudiantesDTO) throws ServiceException {
        try {
            Carreras carreras = carrerasRepository.findById(estudiantesDTO.getCarreraCodigo())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con código " + estudiantesDTO.getCarreraCodigo() + " no encontrada"));

            Estudiantes estudiante = this.estudiantesMapper.toEntity(estudiantesDTO);
            estudiante.setCarreras(carreras);
            Estudiantes newEstudiante = estudiantesRepository.save(estudiante);
            return this.estudiantesMapper.toDTO(newEstudiante);
        } catch (Exception e) {
            log.error("Error al crear el estudiante", e);
            throw new ServiceException("Error al crear el estudiante", e);
        }
    }

    @Override
    public EstudiantesDTO read(Long aLong) throws ServiceException {
        try {
            Estudiantes estudiante = estudiantesRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado"));
            return estudiantesMapper.toDTO(estudiante);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer el estudiante con id " + aLong, e);
        }
    }

    @Override
    public EstudiantesDTO update(Long aLong, EstudiantesDTO estudiantesDTO) throws ServiceException {
        try {
            Estudiantes estudiante = estudiantesRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado"));
            Carreras carreras = carrerasRepository.findById(estudiantesDTO.getCarreraCodigo())
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera no encontrada"));
            estudiante.setNombre(estudiantesDTO.getNombre());
            estudiante.setApellido(estudiantesDTO.getApellido());
            estudiante.setFechaNacimiento(estudiantesDTO.getFechaNacimiento());
            estudiante.setCarreras(carreras);
            Estudiantes estudianteActualizado = estudiantesRepository.save(estudiante);
            return this.estudiantesMapper.toDTO(estudianteActualizado);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar el estudiante con id " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!estudiantesRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Estudiante con id " + aLong + " no encontrado");
            }
            estudiantesRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar el estudiante con id " + aLong, e);
        }
    }

    @Override
    public List<EstudiantesDTO> listAll() throws ServiceException {
        try {
            List<Estudiantes> estudiantes = estudiantesRepository.findAll();
            return estudiantesMapper.toDTOList(estudiantes);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todos los estudiantes", e);
        }
    }
}