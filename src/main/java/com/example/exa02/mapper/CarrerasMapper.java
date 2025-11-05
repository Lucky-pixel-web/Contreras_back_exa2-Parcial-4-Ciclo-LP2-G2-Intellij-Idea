package com.example.exa02.mapper;

import com.example.exa02.dto.CarrerasDTO;
import com.example.exa02.entity.Carreras;
import com.example.exa02.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CarrerasMapper extends BaseMapper<Carreras, CarrerasDTO> {
    CarrerasMapper mapper = Mappers.getMapper(CarrerasMapper.class);
}