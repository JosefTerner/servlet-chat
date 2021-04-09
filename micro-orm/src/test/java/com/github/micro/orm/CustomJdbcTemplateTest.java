package com.github.micro.orm;

import com.github.chat.config.DatabaseConfig;
import com.github.micro.orm.impl.UserRowMapper;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public class CustomJdbcTemplateTest {

    CustomJdbcTemplate jdbcTemplate = new CustomJdbcTemplate(new DatabaseConfig());
    UserRowMapper rowMapper;

    @Test
    public void findAll() {
        Collection act = jdbcTemplate.findAll("SELECT * FROM ", rowMapper);
        System.out.println(act + "jj");
    }

}