package com.example.billManagementTool.services;

import com.example.billManagementTool.exceptions.BillNotFoundException;
import com.example.billManagementTool.models.BillModel;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IBillService {
    BillModel addBill(BillModel billModel);

    List<BillModel> getBills();

    BillModel getBillById(long billId) throws BillNotFoundException;

    List<BillModel> getBillsByDueDate(String dueDate) throws ParseException;
}
