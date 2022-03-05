package com.study.springmvc.coursework7.template;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class MyJdbcTemplate extends JdbcTemplate {

    public MyJdbcTemplate(DataSource dataSource) {
        super(dataSource);
        try {
            Path scriptPath = Paths.get(this.getClass().getClassLoader().getResource("coursework7/initTable.sql").toURI()).toAbsolutePath();
            System.out.println(scriptPath);
            List<String> lines = Files.readAllLines(scriptPath, StandardCharsets.UTF_8);
            String sql = String.join("\n", lines);
            System.out.println(sql);
            System.out.println("Execute SQL...");
            super.execute(sql);
            System.out.println("SQL Done!");
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
