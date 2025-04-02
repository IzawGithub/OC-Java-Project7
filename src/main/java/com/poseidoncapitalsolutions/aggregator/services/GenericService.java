package com.poseidoncapitalsolutions.aggregator.services;

import com.poseidoncapitalsolutions.aggregator.domains.helper.IDomain;

import jakarta.transaction.Transactional;

import net.xyzsd.dichotomy.Maybe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.UUID;

public class GenericService<T extends IDomain, Repository extends JpaRepository<T, UUID>> {
    // -- CRUD --

    @Transactional
    public void create(@NonNull final T item) {
        repository.save(item);
    }

    public List<T> readAll() {
        return repository.findAll();
    }

    public Maybe<T> maybeRead(@NonNull final T item) {
        return maybeReadById(item.getId());
    }

    public Maybe<T> maybeReadById(@NonNull final UUID id) {
        return Maybe.ofNullable(repository.findById(id).orElseGet(() -> null));
    }

    @Transactional
    public void update(@NonNull final T item) {
        var itemDb = repository.getReferenceById(item.getId());
        itemDb = item;
        repository.save(itemDb);
    }

    @Transactional
    public void delete(@NonNull final UUID id) {
        repository.deleteById(id);
    }

    // -- Beans --

    private final @NonNull Repository repository;

    public GenericService(@NonNull final Repository genericRepository) {
        this.repository = genericRepository;
    }
}
