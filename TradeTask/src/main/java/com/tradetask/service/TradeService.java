package com.tradetask.service;

import java.util.List;
import java.util.Optional;

import com.tradetask.entity.Trade;

public interface TradeService {

	public Trade saveTrade(Trade trade) throws Exception;

	public List<Trade> getAllTrades();

	public Optional<Trade> findById(int tradeId);

	public void delete(int tradeId);
	
	public void delete(List<Trade> trades);
	
	public List<Trade> saveAll(List<Trade> trades);

}
