package com.example.demo.Emapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.demo.bo.EmployeeBo;
import com.example.demo.data.EmployeeEo;

@Mapper(componentModel = "spring")
public interface EMapper {
	

	EMapper instance = Mappers.getMapper(EMapper.class);
	
	EmployeeEo boTOEntity(EmployeeBo eBo);
	
	EmployeeBo entityTOBo(EmployeeEo eEo);

}
