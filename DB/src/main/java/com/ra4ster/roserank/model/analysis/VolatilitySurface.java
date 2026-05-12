package com.ra4ster.roserank.model.analysis;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.Underlying;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "volatility_surfaces")
public class VolatilitySurface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id", nullable = false)
    private Underlying underlying;

    @Column(name = "expiration_date", nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "atm_iv")
    private Double atmIv;

    @Column(name = "iv_skew")
    private Double ivSkew; // Difference between OTM puts and OTM calls

    @Column(name = "iv_term_structure")
    private Double ivTermStructure; // IV relative to other expiries

    @Column(name = "call_wing_iv")
    private Double callWingIv; // Far OTM Call Vol

    @Column(name = "put_wing_iv")
    private Double putWingIv; // Far OTM Put Vol

    @Lob
    @Column(name = "surface_json", columnDefinition = "TEXT")
    private String surfaceJson; // Full coordinates for visualization/modeling

    @Column(nullable = false, length = 100)
    private String source;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}