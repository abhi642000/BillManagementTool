package com.example.billManagementTool.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BillModel {
    private long id;
    private String billedTo;
    private Date date;
    private long amount;
    private long taxAmount;
    private long totalAmount;
    private Date dueDate;
}
