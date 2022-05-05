/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.databases;

import de.chojo.sqlutil.jdbc.JdbcConfig;
import de.chojo.sqlutil.jdbc.MariaDbJdbc;
import de.chojo.sqlutil.jdbc.MySQLJdbc;
import de.chojo.sqlutil.jdbc.PostgresJdbc;
import de.chojo.sqlutil.jdbc.SqLiteJdbc;

import java.util.Arrays;

/**
 * Defines a sql type and handles RDBMS specific actions.
 */
public interface SqlType<T extends JdbcConfig<?>> {

    /**
     * The PostgreSQL type.
     */
    SqlType<PostgresJdbc> POSTGRES = new Postgres();
    /**
     * The MariaDb type.
     */
    SqlType<MariaDbJdbc> MARIADB = new MariaDb();

    /**
     * The SqLite type.
     */

    SqlType<SqLiteJdbc> SQLITE = new SqLite();

    /**
     * The MySQL type.
     */
    SqlType<MySQLJdbc> MYSQL = new MySql();

    /**
     * Creates a query to create a version table on the database.
     * <p>
     * The query needs to handle errors when the table exists.
     *
     * @param table table name
     * @return query to create a version table
     */
    String createVersionTableQuery(String table);

    /**
     * Gets a query to read the version from the version table
     *
     * @param table table name
     * @return query to read the version table
     */
    String getVersion(String table);

    /**
     * Get a unique name to identify the database.
     *
     * @return database name
     */
    String getName();

    /**
     * Creates a query to insert a version into the version table.
     * <p>
     * Versions are always inserted and not updated.
     *
     * @param table table name
     * @return query to insert the version.
     */
    String insertVersion(String table);

    /**
     * Creates a query to delete all entries from the version table.
     *
     * @param table table name
     * @return query to clear version table
     */
    String deleteVersion(String table);

    T jdbcBuilder();

    default String[] splitStatements(String queries) {
        return new String[]{queries};
    }

    default String[] cleanStatements(String[] queries) {
        return Arrays.stream(queries).filter(query -> !query.isBlank()).toArray(String[]::new);
    }

}
