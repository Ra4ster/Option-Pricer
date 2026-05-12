package com.ra4ster.roserank.model.portfolio;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.Underlying;
import com.ra4ster.roserank.model.user.User;

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
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "strategies")
public class Strategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "strategy_type", nullable = false)
    private String strategyType; // e.g., "IRON CONDOR", "VERTICAL SPREAD"

    public enum DirectionalBias { BULLISH, BEARISH, NEUTRAL, VOLATILITY_LONG, VOLATILITY_SHORT }

    @Enumerated(EnumType.STRING)
    @Column(name = "directional_bias")
    private DirectionalBias directionalBias;

    @Column(columnDefinition = "TEXT")
    private String thesis;

    @Column(name = "max_profit")
    private BigDecimal maxProfit;

    @Column(name = "max_loss")
    private BigDecimal maxLoss;

    @Column(name = "break_even_low")
    private BigDecimal breakEvenLow;

    @Column(name = "break_even_high")
    private BigDecimal breakEvenHigh;

    @Column(name = "probability_of_profit")
    private Double probabilityProfit;

    @Column(name = "expected_value")
    private BigDecimal expectedValue;

    public enum StrategyStatus { DRAFT, ACTIVE, CLOSED, ARCHIVED }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StrategyStatus status = StrategyStatus.DRAFT;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // Lifecycle Hooks

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}