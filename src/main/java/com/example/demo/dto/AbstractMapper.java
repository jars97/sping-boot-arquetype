package com.example.demo.dto;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.entity.AbstractEntity;

@Mapper
public interface AbstractMapper {
	AbstractMapper INSTANCE = Mappers.getMapper( AbstractMapper.class ); 
	AbstractDTO toDTO(AbstractEntity entity);
}
