package com.rangiffler.data.dao.mappers;

import com.rangiffler.data.dao.entities.CountryEntity;
import lombok.NonNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class CountryEntityMapper implements RowMapper<CountryEntity> {

    @Override
    public CountryEntity mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
        return CountryEntity.builder()
                .id(UUID.fromString(rs.getString("country_id")))
                .name(rs.getString("country_name"))
                .code(rs.getString("country_code"))
                .build();
    }
}
