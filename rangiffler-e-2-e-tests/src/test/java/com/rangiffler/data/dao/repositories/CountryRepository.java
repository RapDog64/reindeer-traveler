package com.rangiffler.data.dao.repositories;

import com.rangiffler.data.dao.DataSourceContext;
import com.rangiffler.data.dao.entities.CountryEntity;
import com.rangiffler.data.dao.mappers.CountryEntityMapper;
import io.qameta.allure.Step;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.JdbcTransactionManager;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.rangiffler.data.DataBase.GEO;

public class CountryRepository {

    private final JdbcTemplate countryJdbcTpl;

    public CountryRepository() {
        JdbcTransactionManager countryData = new JdbcTransactionManager(DataSourceContext.INSTANCE.getDataSource(GEO));
        this.countryJdbcTpl = new JdbcTemplate(Objects.requireNonNull(countryData.getDataSource()));
    }

    @Step("Receive all countries from the database")
    public Optional<List<CountryEntity>> getAllCountries() {
        return Optional.of(countryJdbcTpl.query("SELECT * FROM countries", new CountryEntityMapper()));
    }
}

