package com.example.billManagementTool.repositories;

import com.example.billManagementTool.entities.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long> {
    List<BillEntity> findByDueDateIsNotNullAndDueDate(Date dueDate);
}
