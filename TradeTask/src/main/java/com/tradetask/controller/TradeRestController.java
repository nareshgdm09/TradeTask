package com.tradetask.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tradetask.entity.Trade;
import com.tradetask.service.TradeService;

@RestController
@RequestMapping(value = "/v1")
public class TradeRestController {
	@Autowired
	TradeService tradeService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}

	@PostMapping(path = "/trades", consumes = "application/json", produces = "application/json")
	public Trade newTrade(@RequestBody Trade trade) {
		try {
			return tradeService.saveTrade(trade);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@PutMapping(path = "/trades/{id}", consumes = "application/json", produces = "application/json")
	public Trade updateTrade(@PathVariable(value = "id") int id, @RequestBody Trade tradeDetails)
			throws ResourceNotFoundException {
		Trade trade = tradeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trade not found for this id :: " + id));

		trade.setAmount(tradeDetails.getAmount());
		trade.setBankname(tradeDetails.getBankname());
		trade.setComments(tradeDetails.getComments());
		trade.setCurrency(tradeDetails.getCurrency());
		trade.setLocation(tradeDetails.getLocation());
		Trade updatedtrade=null;
		try {
			updatedtrade = tradeService.saveTrade(trade);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return updatedtrade;
	}

	@DeleteMapping("/trades/{id}")
	public Map<String, Boolean> deleteTrade(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
		tradeService.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Trade not found for this id :: " + id));
		tradeService.delete(id);

		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
