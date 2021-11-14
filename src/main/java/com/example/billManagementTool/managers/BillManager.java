package com.example.billManagementTool.managers;

import com.example.billManagementTool.entities.BillEntity;
import com.example.billManagementTool.exceptions.BillNotFoundException;
import com.example.billManagementTool.mappers.BillMapper;
import com.example.billManagementTool.models.BillModel;
import com.example.billManagementTool.repositories.BillRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
@RequiredArgsConstructor
public class BillManager {
    private final BillRepository billRepository;

    public BillModel addBill(BillModel billModel) {
        BillEntity bill = BillMapper.MAPPER.billModelToEntity(billModel);
        BillEntity billResponse = billRepository.save(bill);
        return BillMapper.MAPPER.billEntityToModel(billResponse);
    }

    public List<BillModel> getBills() {
        List<BillEntity> bills = billRepository.findAll();
        return bills.stream().map(BillMapper.MAPPER::billEntityToModel).collect(Collectors.toList());
    }

    public BillModel getBillById(long billId) throws BillNotFoundException {
        Optional<BillEntity> bill = billRepository.findById(billId);
        if (!bill.isPresent()) {
            log.error("bill {} not found", billId);
            throw new BillNotFoundException("Bill not found");
        }
        return BillMapper.MAPPER.billEntityToModel(bill.get());
    }

    public List<BillModel> getBillByDueDate(Date dueDate) {
        List<BillEntity> billEntities = billRepository.findByDueDateIsNotNullAndDueDate(dueDate);
        return billEntities.stream().map(BillMapper.MAPPER::billEntityToModel).collect(Collectors.toList());
    }
}
