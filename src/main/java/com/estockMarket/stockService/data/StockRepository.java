package com.estockMarket.stockService.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {

	Long deleteByCompanyCode(String companyCode);

}
