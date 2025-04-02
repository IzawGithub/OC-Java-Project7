package com.poseidoncapitalsolutions.aggregator.domains;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BidList implements IDomain {
    @Builder.Default
    @Id
    @NonNull private UUID id = UUID.randomUUID();

    private String account;
    private String type;
    private Double bidQuantity;
    private Double askQuantity;
    private Double bid;
    private Double ask;
    private String benchmark;
    private Timestamp bidListDate;
    private String commentary;
    private String security;
    private String status;
    private String trader;
    private String book;
    private String creationName;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Timestamp creationDate = Timestamp.from(Instant.now());

    private String revisionName;
    private Timestamp revisionDate;
    private String dealName;
    private String dealType;
    private String sourceListId;
    private String side;
}
