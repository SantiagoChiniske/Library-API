package io.github.cursodsousa.libraryapi.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.driver-class-name}")
    String driver;

    /*
    @Bean // Conexão de pequenas escalas;
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource();

        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        ds.setDriverClassName(driver);

        return ds;
    };
*/
    @Bean // Conexão com maior escala
    public DataSource hikariDataSource(){
        HikariConfig config = new HikariConfig();

        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName(driver);
        config.setJdbcUrl(url);

        config.setMaximumPoolSize(10); // Quantidade máxima de conexões
        config.setMinimumIdle(1); // Quantidade miníma liberado de conexões
        config.setPoolName("library-db-pull");
        config.setMaxLifetime(600000); // Máximo de tempo da conexão
        config.setConnectionTimeout(100000);// Timeout para conseguir uma conexão
        config.setConnectionTestQuery("SELECT 1"); // Query de Teste

        return new HikariDataSource(config);
    }

}
