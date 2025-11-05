package com.example.exa02.mapper;

import com.example.exa02.dto.EstudiantesDTO;
import com.example.exa02.entity.Estudiantes;
import com.example.exa02.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EstudiantesMapper extends BaseMapper<Estudiantes, EstudiantesDTO> {

    //DTO a Entity
    @Mapping(target = "carreras", ignore = true)
    Estudiantes toEntity(EstudiantesDTO dto);

    //Entity a DTO
    @Mapping(source = "carreras.codigo", target = "carreraCodigo")
    @Mapping(source = "carreras.nombre", target = "carreraNombre")
    EstudiantesDTO toDTO(Estudiantes entity);
}