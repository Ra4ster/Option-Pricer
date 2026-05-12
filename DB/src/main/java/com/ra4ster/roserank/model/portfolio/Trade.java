package com.ra4ster.roserank.model.portfolio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.OptionContract;
import com.ra4ster.roserank.model.core.Underlying;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Trades")
public class Trade
{
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "portfolio_id")
	private Portfolio portfolio;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "underlying_id")
	private Underlying underlying;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "option_contract_id")
	private OptionContract contract;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "strategy_id")
	private Strategy strategy;
	
	private enum TradeType { STOCK, OPTION, STRATEGY }
	private enum TradeSide { BUY, SELL, BUY_TO_OPEN, SELL_TO_OPEN, BUY_TO_CLOSE, SELL_TO_CLOSE }
	
	@Enumerated(EnumType.STRING)
	@Column(name = "trade_type", nullable = false)
	private TradeType type;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "trade_side", nullable = false)
	private TradeSide side;
	
	@Column(name = "quantity", nullable = true)
	private Integer quantity = 0;
	
	@Column(name = "price", nullable = true)
	private BigDecimal price = new BigDecimal(0);
	
	@Column(name = "fees", nullable = true)
	private BigDecimal fees = new BigDecimal(0);
	
	@Column(name = "notes", nullable = true, length = 2500)
	private String notes = null;
	
	@Column(name = "executed_at", nullable = true)
	private LocalDateTime executedAt;
	
	@Column(name = "created_at", nullable = true)
	private LocalDateTime createdAt;
	
	@PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
