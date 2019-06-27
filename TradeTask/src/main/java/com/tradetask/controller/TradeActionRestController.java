package com.tradetask.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v1")
public class TradeActionRestController {
	@Autowired
	TradeService tradeService;

	@PostMapping(path = "action/review/trades")
	public List<Trade> reviewTradeAction(@RequestBody List<Trade> listTrades) {

		return updateTrades(listTrades, "reviewed");

	}

	@PostMapping(path = "action/approve/trades", consumes = "application/json", produces = "application/json")
	public List<Trade> approveTradeAction(@RequestBody List<Trade> listTrades) {
		return updateTrades(listTrades, "approved");
	}

	@PostMapping(path = "action/reject/trades", consumes = "application/json", produces = "application/json")
	public List<Trade> rejectTradeAction(@RequestBody List<Trade> listTrades) {
		return updateTrades(listTrades, "rejected");
	}

	@DeleteMapping(path = "action/delete/trades", consumes = "application/json", produces = "application/json")
	public Map<String, Boolean> deleteTradeAction(@RequestBody List<Trade> listTrades) {

		listTrades.forEach(trade -> {
			try {
				tradeService.findById(trade.getTradeId()).orElseThrow(
						() -> new ResourceNotFoundException("Trade not found for this id :: " + trade.getTradeId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		tradeService.delete(listTrades);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	private List<Trade> updateTrades(List<Trade> listTrades, String action) {
		listTrades.forEach(trade -> {
			try {
				tradeService.findById(trade.getTradeId()).orElseThrow(
						() -> new ResourceNotFoundException("Trade not found for this id :: " + trade.getTradeId()));
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		listTrades.forEach(trade -> {
			trade.setStatus(action);
		});

		return tradeService.saveAll(listTrades);
	}

}
