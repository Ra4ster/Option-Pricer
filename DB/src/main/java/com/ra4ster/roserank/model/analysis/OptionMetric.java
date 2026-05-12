package com.ra4ster.roserank.model.analysis;

import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.OptionContract;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "option_metrics")
public class OptionMetric {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id", nullable = false)
    private OptionContract optionContract;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    private Double moneyness; // S/K for calls, K/S for puts

    @Column(name = "intrinsic_value")
    private Double intrinsicValue;

    @Column(name = "extrinsic_value")
    private Double extrinsicValue;

    @Column(name = "break_even")
    private Double breakEven;

    @Column(name = "days_to_expiration")
    private Integer daysToExpiration;

    @Column(name = "probability_itm")
    private Double probabilityItm;

    @Column(name = "probability_otm")
    private Double probabilityOtm;

    @Column(name = "probability_profit")
    private Double probabilityProfit;

    // Volatility Analysis
    @Column(name = "iv_rank")
    private Double ivRank;

    @Column(name = "iv_percentile")
    private Double ivPercentile;

    // Trading Logic Metrics
    @Column(name = "bid_ask_spread")
    private Double bidAskSpread;

    @Column(name = "liquidity_score")
    private Double liquidityScore;

    @Column(name = "greek_risk_score")
    private Double greekRiskScore;

    @Column(name = "expected_move")
    private Double expectedMove;

    @Column(name = "model_price")
    private Double modelPrice; // Theoretical price (e.g., Black-Scholes)

    @Column(name = "pricing_edge")
    private Double pricingEdge; // Difference between model and market price

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}