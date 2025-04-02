package com.poseidoncapitalsolutions.aggregator.domains;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.NonNull;

import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Rating implements IDomain {
    @Builder.Default
    @Id
    @NonNull private UUID id = UUID.randomUUID();

    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private Integer orderNumber;
}
