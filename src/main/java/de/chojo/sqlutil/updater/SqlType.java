/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.updater;

import java.util.Arrays;

public interface SqlType {

    SqlType POSTGRES = new Postgres();

    SqlType MARIADB = new MariaDb();

    SqlType SQLITE = new SqLite();

    SqlType MYSQL = new MySql();

    String createVersionTableQuery(String table);

    String getVersion(String table);

    String getName();

    String insertVersion(String table);

    String deleteVersion(String table);

    default String[] splitStatements(String queries) {
        return new String[]{queries};
    }

    default String[] cleanStatements(String[] queries) {
        return Arrays.stream(queries).filter(query -> !query.isBlank()).toArray(String[]::new);
    }

    abstract class DefaultType implements SqlType {

        @Override
        public String getVersion(String table) {
            return String.format("SELECT major, patch FROM %s LIMIT 1", table);
        }

        @Override
        public String insertVersion(String table) {
            return String.format("INSERT INTO %s VALUES (?, ?)", table);
        }

        @Override
        public String deleteVersion(String table) {
            return String.format("DELETE FROM %s;", table);
        }

        @Override
        public String createVersionTableQuery(String table) {
            return String.format("CREATE TABLE IF NOT EXISTS %s(major INTEGER, patch INTEGER);", table);
        }
    }

    class MariaDb extends DefaultType {

        @Override
        public String getName() {
            return "mariadb";
        }

        @Override
        public String[] splitStatements(String queries) {
            return cleanStatements(queries.split(";"));
        }
    }

    class MySql extends DefaultType {

        @Override
        public String getName() {
            return "mysql";
        }

        @Override
        public String[] splitStatements(String queries) {
            return cleanStatements(queries.split(";"));
        }
    }

    class Postgres extends DefaultType {

        @Override
        public String getName() {
            return "postgres";
        }
    }

    class SqLite extends DefaultType {
        @Override
        public String createVersionTableQuery(String table) {
            return String.format("CREATE TABLE IF NOT EXISTS %s(major INT, patch INT);", table);
        }

        @Override
        public String getName() {
            return "sqlite";
        }

        @Override
        public String[] splitStatements(String queries) {
            return cleanStatements(queries.split(";"));
        }
    }
}
