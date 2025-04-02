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
public class CurvePoint implements IDomain {
    @Builder.Default
    @Id
    @NonNull private UUID id = UUID.randomUUID();

    private Integer curveId;
    private Timestamp asOfDate;
    private Double term;
    private Double value;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    private Timestamp creationDate = Timestamp.from(Instant.now());
}
