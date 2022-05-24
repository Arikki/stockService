package com.estockMarket.stockService.command.api.aggregate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.estockMarket.stockService.command.api.commands.CreateStockCommand;
import com.estockMarket.stockService.command.api.commands.DeleteStockCommand;
import com.estockMarket.stockService.command.api.events.StockCreatedEvent;
import com.estockMarket.stockService.command.api.events.StockDeletedEvent;



@Aggregate
public class StockAggregate {
	private static final Logger logger = LoggerFactory.getLogger(StockAggregate.class);

	@AggregateIdentifier
	private String uuid;
	private String companyCode;
	private BigDecimal stockPrice;
	
	public StockAggregate() {

	}

	@CommandHandler
	public StockAggregate(CreateStockCommand createStockCommand) {
		logger.info("Inside createStockCommand");
		// perform validations here
		StockCreatedEvent stockCreatedEvent = StockCreatedEvent.builder().uuid(createStockCommand.getUuid())
				.companyCode(createStockCommand.getCompanyCode()).stockPrice(createStockCommand.getStockPrice())
				.build();

		AggregateLifecycle.apply(stockCreatedEvent);

	}

	@CommandHandler
	public StockAggregate(DeleteStockCommand deleteStockCommand) {
		// perform validations here

		StockDeletedEvent stockDeletedEvent = StockDeletedEvent.builder().uuid(deleteStockCommand.getUuid())
				.companyCode(deleteStockCommand.getCompanyCode()).build();
		AggregateLifecycle.apply(stockDeletedEvent);
	}


	@EventSourcingHandler
	public void on(StockCreatedEvent stockCreatedEvent) {

		this.companyCode = stockCreatedEvent.getCompanyCode();
		this.stockPrice = stockCreatedEvent.getStockPrice();
		this.uuid = stockCreatedEvent.getUuid();
logger.info("Inside eventsourcing");

	}

	@EventSourcingHandler
	public void on(StockDeletedEvent stockDeletedEvent) {

		this.companyCode = stockDeletedEvent.getCompanyCode();

		this.uuid = stockDeletedEvent.getUuid();

	}
}
