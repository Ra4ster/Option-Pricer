package com.ra4ster.roserank.model.analysis;

import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.OptionContract;
import com.ra4ster.roserank.model.core.Underlying;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "rose_ranks")
public class RoseRank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id")
    private OptionContract optionContract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Scoring Engine (0.0 to 100.0)
    @Column(name = "rank_score")
    private Double rankScore;

    @Column(name = "edge_score")
    private Double edgeScore;

    @Column(name = "liquidity_score")
    private Double liquidityScore;

    @Column(name = "volatility_score")
    private Double volatilityScore;

    @Column(name = "momentum_score")
    private Double momentumScore;

    @Column(name = "risk_reward_score")
    private Double riskRewardScore;

    @Column(name = "leap_efficiency_score")
    private Double leapEfficiencyScore;

    @Column(name = "income_score")
    private Double incomeScore;

    // Categorical Analysis
    public enum DirectionalBias { BULLISH, BEARISH, NEUTRAL }
    
    @Enumerated(EnumType.STRING)
    @Column(name = "directional_bias")
    private DirectionalBias directionalBias;

    public enum RecommendedAction { WATCH, BUY, SELL, AVOID }

    @Enumerated(EnumType.STRING)
    @Column(name = "recommended_action")
    private RecommendedAction recommendedAction;

    @Column(name = "recommended_structure")
    private String recommendedStructure; // e.g., "IRON_CONDOR", "BULL_PUT_SPREAD"

    @Column(columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}