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
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id")
    private OptionContract contract;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "strategy_id")
    private Strategy strategy;

    public enum PositionType { STOCK, OPTION, STRATEGY }
    public enum PositionSide { LONG, SHORT }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PositionType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PositionSide side;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "avg_entry_price")
    private BigDecimal avgEntryPrice = BigDecimal.ZERO;

    @Column(name = "current_mark")
    private BigDecimal currentMark = BigDecimal.ZERO;

    @Column(name = "unrealized_pnl")
    private BigDecimal unrealizedPnl = BigDecimal.ZERO;

    @Column(name = "realized_pnl")
    private BigDecimal realizedPnl = BigDecimal.ZERO;

    @Column(name = "opened_at", nullable = false, updatable = false)
    private LocalDateTime openedAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Lifecycle Hooks

    @PrePersist
    protected void onCreate() {
        this.openedAt = LocalDateTime.now();
        this.updatedAt = this.openedAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}