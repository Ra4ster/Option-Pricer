package com.ra4ster.roserank.model.market;

import java.time.LocalDateTime;

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
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id") // Optional: null for macro/economic events
    private Underlying underlying;

    public enum EventType { 
        EARNINGS, DIVIDEND, SPLIT, MACRO, FED, ECONOMIC 
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(name = "event_date", nullable = false)
    private LocalDateTime eventDate;

    @Column(name = "actual_value")
    private String actualValue; // String to handle percentages, currency, or ratios

    @Column(name = "expected_value")
    private String expectedValue;

    @Column(name = "previous_value")
    private String previousValue;

    public enum ImpactLevel { LOW, MEDIUM, HIGH, CRITICAL }

    @Enumerated(EnumType.STRING)
    @Column(name = "impact_level")
    private ImpactLevel impactLevel;

    @Column(length = 255)
    private String source;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}