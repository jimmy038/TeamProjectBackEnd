package com.example.PipiShrimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Cart;

@Repository
public interface CartDao extends JpaRepository<Cart, Integer>{

}
