package com.cs.personer.config.dblog;


import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
@Component
public class Connect implements ConnectionSource {

    @Autowired
    private DataSource mysqlDataSource;

    @Override
    public Connection getConnection() throws SQLException {
        return mysqlDataSource.getConnection();
    }


    @Override
    public State getState() {
        return null;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return false;
    }

    @Override
    public boolean isStopped() {
        return false;
    }
}
