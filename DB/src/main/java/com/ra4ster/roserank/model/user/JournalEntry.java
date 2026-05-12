package com.ra4ster.roserank.model.user;

import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.OptionContract;
import com.ra4ster.roserank.model.core.Underlying;
import com.ra4ster.roserank.model.portfolio.Strategy;
import com.ra4ster.roserank.model.portfolio.Trade;

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
@Table(name = "journal_entries")
public class JournalEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "portfolio_id")
    private Long portfolioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trade_id")
    private Trade trade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "strategy_id")
    private Strategy strategy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id")
    private Underlying underlying;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "option_contract_id")
    private OptionContract optionContract;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    @Column(length = 255)
    private String tags; // Stored as comma-separated or JSON string

    public enum Emotion { 
        CONFIDENT, ANXIOUS, NEUTRAL, GREEDY, FEARFUL, EXCITED, DISCIPLINED 
    }

    @Enumerated(EnumType.STRING)
    private Emotion emotion;

    public enum MistakeType { 
        NONE, FOMO, OVER_LEVERAGED, EARLY_EXIT, LATE_ENTRY, REVENGE_TRADE, IGNORED_STOP_LOSS 
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "mistake_type")
    private MistakeType mistakeType = MistakeType.NONE;

    @Column(columnDefinition = "TEXT")
    private String lesson;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // --- Lifecycle Hooks ---

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