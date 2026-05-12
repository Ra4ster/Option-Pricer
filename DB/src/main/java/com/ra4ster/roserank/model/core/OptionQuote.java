package com.ra4ster.roserank.model.core;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
@Table(name = "option_quotes")
public class OptionQuote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id", nullable = false)
    private OptionContract optionContract;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column
    private BigDecimal bid;

    @Column
    private BigDecimal ask;

    @Column
    private BigDecimal mid;

    @Column
    private BigDecimal last;

    @Column
    private BigDecimal mark;

    private Long volume;

    @Column(name = "open_interest")
    private Long openInterest;

    @Column(name = "implied_volatility")
    private BigDecimal impliedVolatility;

    // Option Greeks
    private BigDecimal delta;
    private BigDecimal gamma;
    private BigDecimal theta;
    private BigDecimal vega;
    private BigDecimal rho;

    @Column(name = "bid_size")
    private Integer bidSize;

    @Column(name = "ask_size")
    private Integer askSize;

    @Column(nullable = false, length = 100)
    private String source;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}