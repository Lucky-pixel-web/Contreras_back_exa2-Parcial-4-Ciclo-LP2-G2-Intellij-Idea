package com.example.exa02.mapper;

import com.example.exa02.dto.ProductoDTO;
import com.example.exa02.entity.Producto;
import com.example.exa02.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductoMapper extends BaseMapper<Producto, ProductoDTO> {

    //DTO a Entitu
    @Mapping(target = "categoria", ignore = true)
    Producto toEntity(ProductoDTO dto);

    //Entity a DTO
    @Mapping(source = "categoria.id", target = "categoriaId")
    @Mapping(source = "categoria.nombre", target = "categoriaNombre")
    ProductoDTO toDTO(Producto entity);
}
