package com.rangiffler.data.repository;

import com.rangiffler.data.CountryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {

    @NotNull Optional<CountryEntity> findById(@NotNull UUID id);
}
