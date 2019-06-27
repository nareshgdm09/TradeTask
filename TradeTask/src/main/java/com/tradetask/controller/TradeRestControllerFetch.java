package com.tradetask.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v2")
public class TradeRestControllerFetch {
	@Autowired
	TradeService tradeService;

	@GetMapping("/trades")
	public List<Trade> getTrades() {
		return tradeService.getAllTrades();
	}

	@GetMapping("/trades/{id}")
	public Optional<Trade> getTrade(@PathVariable int id) {
		try {
			tradeService.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Exception : Trade not found for this id :: " + id));
		} catch (ResourceNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
