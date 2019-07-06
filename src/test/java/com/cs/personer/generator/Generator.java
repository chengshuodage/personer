package com.cs.personer.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static void main(String[] args) {

        List<String> warnings = new ArrayList<>();
        File configFile = new File("C:\\WorkSpace\\Java\\personer\\src\\test\\resources\\generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = null;
        try {
            config = cp.parseConfiguration(configFile);
        } catch (IOException | XMLParserException e) {
            e.printStackTrace();
        }
        DefaultShellCallback callback = new DefaultShellCallback(true);
        try {
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,
                    callback, warnings);
            myBatisGenerator.generate(null);
        } catch (InvalidConfigurationException | SQLException | IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("生成Mybatis配置成功！");

    }

}
