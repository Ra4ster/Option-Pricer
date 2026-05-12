package com.ra4ster.roserank.model.market;

import java.time.LocalDateTime;

import com.ra4ster.roserank.model.core.Underlying;

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
@Table(name = "news_items")
public class NewsItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "underlying_id") // Nullable for market-wide news
    private Underlying underlying;

    @Column(nullable = false, length = 500)
    private String headline;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(length = 1000)
    private String url;

    @Column(length = 100)
    private String source;

    @Column(name = "published_at", nullable = false)
    private LocalDateTime published_at;

    @Column(name = "sentiment_score")
    private Double sentimentScore; // Scale of -1.0 (Bearish) to 1.0 (Bullish)

    @Column(name = "relevance_score")
    private Double relevanceScore; // How closely this news relates to the ticker

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}