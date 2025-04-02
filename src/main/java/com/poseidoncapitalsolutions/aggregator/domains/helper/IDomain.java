package com.poseidoncapitalsolutions.aggregator.domains.helper;

import lombok.NonNull;

import java.util.UUID;

public interface IDomain {
    @NonNull UUID getId();

    void setId(@NonNull final UUID id);
}
