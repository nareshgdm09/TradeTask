package com.tradetask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tradetask.entity.Trade;

@Repository
public interface TradeRepo extends JpaRepository<Trade, Integer> {

}
