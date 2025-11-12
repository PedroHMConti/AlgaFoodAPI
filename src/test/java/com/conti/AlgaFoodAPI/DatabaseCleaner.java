package com.conti.AlgaFoodAPI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
public class DatabaseCleaner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseCleaner.class);

    @Autowired
    private DataSource dataSource;

    /**
     * Sufixo opcional para validar se está rodando em banco de testes.
     * Se for null ou vazio, a checagem é ignorada.
     * Você pode definir isso via variável de ambiente / property se quiser
     * (ex.: "test", "testdb" etc).
     */
    private String testDatabaseSuffix = "test"; // ou deixe "" para desabilitar

    public void clearTables() {
        try (Connection connection = dataSource.getConnection()) {
            // validação opcional
            if (testDatabaseSuffix != null && !testDatabaseSuffix.isBlank()) {
                checkTestDatabase(connection);
            }

            List<String> tableNames = getTableNames(connection);
            if (tableNames.isEmpty()) {
                log.debug("No tables found to truncate.");
                return;
            }

            clear(connection, tableNames);
            log.info("Database cleaned: {} tables truncated.", tableNames.size());
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao limpar tabelas do banco: " + e.getMessage(), e);
        }
    }

    private void checkTestDatabase(Connection connection) throws SQLException {
        String catalog = connection.getCatalog();
        if (catalog == null || !catalog.toLowerCase(Locale.ROOT).endsWith(testDatabaseSuffix.toLowerCase(Locale.ROOT))) {
            throw new RuntimeException(
                    "Cannot clear database tables because '" + catalog + "' is not a test database (suffix '" + testDatabaseSuffix + "' not found).");
        }
    }

    private List<String> getTableNames(Connection connection) throws SQLException {
        List<String> tableNames = new ArrayList<>();

        DatabaseMetaData metaData = connection.getMetaData();
        String catalog = connection.getCatalog();
        // schemaPattern null para MySQL; ajuste para outros bancos se necessário
        try (ResultSet rs = metaData.getTables(catalog, null, "%", new String[]{"TABLE"})) {
            while (rs.next()) {
                String tableName = rs.getString("TABLE_NAME");
                // Ignora a tabela de histórico do Flyway (case-insensitive)
                if (tableName == null) continue;
                if ("flyway_schema_history".equalsIgnoreCase(tableName)) continue;
                tableNames.add(tableName);
            }
        }

        return tableNames;
    }

    private void clear(Connection connection, List<String> tableNames) throws SQLException {
        // Use try-with-resources para fechar Statement
        try (Statement stmt = connection.createStatement()) {
            log.debug("Disabling foreign key checks");
            stmt.addBatch("SET FOREIGN_KEY_CHECKS = 0");

            // Adiciona TRUNCATE com escape para MySQL
            for (String table : tableNames) {
                String sql = String.format("TRUNCATE TABLE `%s`", table);
                log.debug("Adding SQL: {}", sql);
                stmt.addBatch(sql);
            }

            stmt.addBatch("SET FOREIGN_KEY_CHECKS = 1");

            // Executa o batch
            stmt.executeBatch();
        }
    }
}
