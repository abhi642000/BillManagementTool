package com.example.billManagementTool.mappers;

import com.example.billManagementTool.DTOs.BillDTO;
import com.example.billManagementTool.entities.BillEntity;
import com.example.billManagementTool.models.BillModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface BillMapper {
    BillMapper MAPPER = Mappers.getMapper(BillMapper.class);

    BillModel billDTOToModel(BillDTO billDTO);

    BillEntity billModelToEntity(BillModel billModel);

    BillModel billEntityToModel(BillEntity bill);

    BillDTO billModelToDTO(BillModel billModel);
}
