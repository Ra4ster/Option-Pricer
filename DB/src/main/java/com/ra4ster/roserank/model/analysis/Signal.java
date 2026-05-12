package com.ra4ster.roserank.model.analysis;

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
import jakarta.persistence.Lob;
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
@Table(name = "signals")
public class Signal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id") // Optional: signal could be on the ticker generally
    private OptionContract optionContract;

    public enum SignalType { VOLATILITY, EARNINGS, FLOW, MISPRICING, MOMENTUM }

    @Enumerated(EnumType.STRING)
    @Column(name = "signal_type", nullable = false)
    private SignalType signalType;

    public enum Direction { BULLISH, BEARISH, NEUTRAL }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Direction direction;

    @Column
    private BigDecimal confidence; // e.g., 0.85 for 85%

    @Column
    private BigDecimal severity; // (e.g., z-score)

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(columnDefinition = "TEXT")
    private String thesis;

    @Lob
    @Column(name = "trigger_data_json", columnDefinition = "TEXT")
    private String triggerDataJson; // Snapshot of the data that tripped the signal

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}