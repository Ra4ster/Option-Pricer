package com.ra4ster.roserank.model.core;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name = "option_contracts")
public class OptionContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @Column(name = "occ_symbol", unique = true, nullable = false, length = 50)
    private String occSymbol;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private BigDecimal strike;

    public enum OptionType { CALL, PUT }
    
    @Enumerated(EnumType.STRING)
    @Column(name = "option_type", nullable = false)
    private OptionType optionType;

    public enum OptionStyle { AMERICAN, EUROPEAN }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OptionStyle style = OptionStyle.AMERICAN;

    @Column(nullable = false)
    private Integer multiplier = 100;

    @Column(name = "contract_size", nullable = false)
    private Integer contractSize = 100;

    @Column(name = "is_weekly", nullable = false)
    private Boolean isWeekly = false;

    @Column(name = "is_leap", nullable = false)
    private Boolean isLeap = false;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

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