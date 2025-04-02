package com.poseidoncapitalsolutions.aggregator.domains;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Trade implements IDomain {
    @Builder.Default
    @Id
    @NonNull private UUID id = UUID.randomUUID();

    private String account;
    private String type;
    private Double buyQuantity;
    private Double sellQuantity;
    private Double buyPrice;
    private Double sellPrice;
    private String benchmark;
    private Timestamp tradeDate;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;

    @CreationTimestamp
    @EqualsAndHashCode.Exclude
    private Timestamp creationDate;

    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;
}
