package com.example.PipiShrimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Orders;

@Repository
public interface OrdersDao extends JpaRepository<Orders, String>{

}
