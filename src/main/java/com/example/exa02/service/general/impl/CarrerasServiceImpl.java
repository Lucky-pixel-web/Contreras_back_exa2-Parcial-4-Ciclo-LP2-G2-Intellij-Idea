package com.example.exa02.service.general.impl;

import com.example.exa02.controller.error.ResourceNotFoundException;
import com.example.exa02.dto.CarrerasDTO;
import com.example.exa02.entity.Carreras;
import com.example.exa02.mapper.CarrerasMapper;
import com.example.exa02.repository.CarrerasRepository;
import com.example.exa02.service.general.service.CarrerasService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CarrerasServiceImpl implements CarrerasService {
    private final CarrerasRepository carrerasRepository;
    private final CarrerasMapper carrerasMapper;

    @Override
    public CarrerasDTO create(CarrerasDTO carrerasDTO) throws ServiceException {
        try {
            Carreras carreras = carrerasMapper.toEntity(carrerasDTO);
            Carreras carrerasGuardada = carrerasRepository.save(carreras);
            return carrerasMapper.toDTO(carrerasGuardada);
        } catch (Exception e) {
            log.error("Error al crear la carrera", e);
            throw new ServiceException("Error al crear la carrera", e);
        }
    }

    @Override
    public CarrerasDTO read(Long aLong) throws ServiceException {
        try {
            Carreras carreras = carrerasRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con código " + aLong + " no encontrada"));
            return carrerasMapper.toDTO(carreras);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al leer la carrera con código " + aLong, e);
        }
    }

    @Override
    public CarrerasDTO update(Long aLong, CarrerasDTO carrerasDTO) throws ServiceException {
        try {
            Carreras carreras = carrerasRepository.findById(aLong)
                    .orElseThrow(() -> new ResourceNotFoundException("Carrera con código " + aLong + " no encontrada"));
            carreras.setNombre(carrerasDTO.getNombre());
            Carreras carrerasActualizada = carrerasRepository.save(carreras);
            return carrerasMapper.toDTO(carrerasActualizada);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al actualizar la carrera con código " + aLong, e);
        }
    }

    @Override
    public void delete(Long aLong) throws ServiceException {
        try {
            if (!carrerasRepository.existsById(aLong)) {
                throw new ResourceNotFoundException("Carrera con código " + aLong + " no encontrada");
            }
            carrerasRepository.deleteById(aLong);
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ServiceException("Error al eliminar la carrera con código " + aLong, e);
        }
    }

    @Override
    public List<CarrerasDTO> listAll() throws ServiceException {
        try {
            List<Carreras> carreras = carrerasRepository.findAll();
            return carrerasMapper.toDTOList(carreras);
        } catch (Exception e) {
            throw new ServiceException("Error al listar todas las carreras", e);
        }
    }
}