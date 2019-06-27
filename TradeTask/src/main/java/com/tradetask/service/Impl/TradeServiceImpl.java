package com.tradetask.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tradetask.entity.Trade;
import com.tradetask.repository.TradeRepo;
import com.tradetask.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {
	@Autowired
	TradeRepo repository;

	@Override
	public Trade saveTrade(Trade trade) throws Exception {
		return repository.save(trade);
	}

	@Override
	public List<Trade> getAllTrades() {
		return repository.findAll();
	}

	@Override
	public Optional<Trade> findById(int tradeId) {
		return repository.findById(tradeId);
	}

	@Override
	public List<Trade> saveAll(List<Trade> trades) {
		return repository.saveAll(trades);
	}

	@Override
	public void delete(int tradeId) {
		repository.deleteById(tradeId);
	}

	@Override
	public void delete(List<Trade> trades) {
		repository.deleteAll(trades);

	}

}
