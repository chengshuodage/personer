package com.cs.personer.config.dblog;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.filter.MarkerFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class LogAppender {

    @Autowired
    private Connect connect;

    /**
     * 动态修改配置文件,增加日志Appender
     * 1.建立表字段与对应log4j线程变量对应关系
     * 2.Marker过滤器
     * 3.配置数据库链接与表名
     */
    @PostConstruct
    public void init() {
        LoggerContext context = (LoggerContext) LogManager.getContext(false);
        Configuration config = context.getConfiguration();
        ColumnConfig[] columnConfigs = {
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("app")
                        .setPattern("%X{systemId}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build(),
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("log_level")
                        .setPattern("%level{ERROR=3, WARN=2, INFO=1}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build(),
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("client_ip")
                        .setPattern("%X{ipAddress}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build(),
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("client_agent")
                        .setPattern("%X{clientAgent}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build(),
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("detail")
                        .setPattern("%message}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build(),
                ColumnConfig.newBuilder()
                        .setConfiguration(config)
                        .setName("creator_id")
                        .setPattern("%X{creatorId}")
                        .setLiteral(null)
                        .setEventTimestamp(false)
                        .setUnicode(false)
                        .setClob(false)
                        .build()
        };

        //配置Marker过滤器(标记过滤器)
        MarkerFilter filter = MarkerFilter.createFilter(LogMarket.DB.getName(),
                Filter.Result.ACCEPT, Filter.Result.DENY);

        JdbcAppender jdbcAppender = JdbcAppender.newBuilder()
                .setName("logAppender")
                .setBufferSize(0)
                .setTruncateStrings(true)
                .setFilter(filter)
                .setTableName("system_log")
                .setConnectionSource(connect)
                .setColumnMappings()
                .setColumnConfigs(columnConfigs)
                .setIgnoreExceptions(true)
                .build();

        jdbcAppender.start();
        config.addAppender(jdbcAppender);
        config.getLoggerConfig("ROOT").addAppender(jdbcAppender, Level.INFO, null);
        context.updateLoggers();
    }

}
