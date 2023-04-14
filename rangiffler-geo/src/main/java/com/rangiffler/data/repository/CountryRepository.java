package com.rangiffler.data.repository;

import com.rangiffler.data.CountryEntity;
import com.rangiffler.model.CountryJson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CountryRepository extends JpaRepository<CountryEntity, UUID> {
     Optional<CountryEntity> findById(UUID id);
}
