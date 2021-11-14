package com.example.billManagementTool.controllers;

import com.example.billManagementTool.DTOs.BillDTO;
import com.example.billManagementTool.exceptions.BillNotFoundException;
import com.example.billManagementTool.mappers.BillMapper;
import com.example.billManagementTool.models.BillModel;
import com.example.billManagementTool.services.imp.BillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bills")
public class BillsController {
    private final BillService billService;

    @PostMapping
    public ResponseEntity<BillDTO> addBill(@Valid @RequestBody(required = true) BillDTO billDTO) {
        log.info("Adding a new bill");

        BillModel billModel = BillMapper.MAPPER.billDTOToModel(billDTO);
        BillModel billModelResponse = billService.addBill(billModel);
        BillDTO billDTOResponse = BillMapper.MAPPER.billModelToDTO(billModelResponse);

        log.info("Bill added successfully");
        return ResponseEntity.ok(billDTOResponse);
    }

    @GetMapping
    public ResponseEntity<List<BillDTO>> getAllBills() {
        log.info("Getting all bills");

        List<BillModel> billModels = billService.getBills();
        List<BillDTO> billsResponse = billModels.stream().map(BillMapper.MAPPER::billModelToDTO).collect(Collectors.toList());

        log.info("Got all bills");
        return ResponseEntity.ok(billsResponse);
    }

    @GetMapping("/{billId}")
    public ResponseEntity<BillDTO> getBillById(@PathVariable("billId") long billId) throws BillNotFoundException {
        log.info("Getting bill by id {}", billId);

        BillModel billModel = billService.getBillById(billId);
        BillDTO billDTO = BillMapper.MAPPER.billModelToDTO(billModel);

        log.info("Got the bill by id {}", billId);
        return ResponseEntity.ok(billDTO);
    }

    @GetMapping("/due/{dueDateString}")
    public ResponseEntity<List<BillDTO>> getBillByDueDate(@PathVariable("dueDateString") String dueDateString) throws ParseException {
        log.info("Getting bill by due date {}", dueDateString);
        List<BillModel> billModels = billService.getBillsByDueDate(dueDateString);
        List<BillDTO> billDTOS = billModels.stream().map(BillMapper.MAPPER::billModelToDTO).collect(Collectors.toList());
        log.info("Got bill by due date {}", dueDateString);
        return ResponseEntity.ok(billDTOS);
    }
}
