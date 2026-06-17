package com.retail.returns.service;

import com.retail.returns.dto.ReturnRequestDTO;
import com.retail.returns.dto.ReturnResponseDTO;

import java.util.List;

public interface ReturnService {
    ReturnResponseDTO createReturn(ReturnRequestDTO requestDTO);

    List<ReturnResponseDTO> getAllReturns();

    ReturnResponseDTO getReturnById(Long id);

    List<ReturnResponseDTO> getReturnsByStatus(String status);
}
