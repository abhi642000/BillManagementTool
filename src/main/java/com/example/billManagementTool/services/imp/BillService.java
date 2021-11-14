package com.example.billManagementTool.services.imp;

import com.example.billManagementTool.exceptions.BillNotFoundException;
import com.example.billManagementTool.managers.BillManager;
import com.example.billManagementTool.models.BillModel;
import com.example.billManagementTool.services.IBillService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BillService implements IBillService {
    private final BillManager billManager;

    @Override
    public BillModel addBill(BillModel billModel) {
        billModel.setTotalAmount(billModel.getAmount() + billModel.getTaxAmount());
        return billManager.addBill(billModel);
    }

    @Override
    public List<BillModel> getBills() {
        return billManager.getBills();
    }

    @Override
    public BillModel getBillById(long billId) throws BillNotFoundException {
        return billManager.getBillById(billId);
    }

    @Override
    public List<BillModel> getBillsByDueDate(String dueDate) throws ParseException {
        try {
            Date dueDateInDateFormat = new SimpleDateFormat("dd-MM-yyyy").parse(dueDate);
            return billManager.getBillByDueDate(dueDateInDateFormat);
        } catch (ParseException ex) {
            log.info("date {} is not formatted", dueDate);
            throw ex;
        }
    }
}
