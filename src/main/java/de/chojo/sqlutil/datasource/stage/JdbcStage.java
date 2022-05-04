/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.datasource.stage;

import de.chojo.sqlutil.datasource.DbConfig;

import java.util.function.Consumer;

/**
 * A stage which represents a property creator for a data source.
 */
public interface JdbcStage<T> {

    JdbcStage<T> configure(Consumer<T> builder);

    /**
     * Create a configuration with these properties.
     *
     * @return a configuration stage with a configuration with applied properties.
     */
    ConfigurationStage create();
}
