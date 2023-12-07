package com.example.PipiShrimp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.PipiShrimp.entity.Products;

@Repository
public interface ProductsDao extends JpaRepository<Products, String>{

}
