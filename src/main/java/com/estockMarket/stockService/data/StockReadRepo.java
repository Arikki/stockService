package com.estockMarket.stockService.data;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.estockMarket.stockService.model.StockReadModel;




@Repository
@EnableMongoRepositories
public interface StockReadRepo extends MongoRepository<StockReadModel, Integer>{


}
