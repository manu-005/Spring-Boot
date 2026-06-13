package com.learning.eCommerce.mapper;

import com.learning.eCommerce.dto.customerDTO.CustomerDTO;
import com.learning.eCommerce.dto.customerDTO.CustomerResponseDTO;
import com.learning.eCommerce.entity.customerEntity.CustomerEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")  // makes it a Spring Bean
public interface CustomerMapper {

    // Entity → ResponseDTO
    CustomerResponseDTO entityToRespDTO(CustomerEntity entity);

    // RequestDTO → Entity
    CustomerEntity customerToEntity(CustomerDTO dto);

    // List of Entities → List of DTOs
    List<CustomerResponseDTO> entityToRespDTOList(List<CustomerEntity> entities);
}