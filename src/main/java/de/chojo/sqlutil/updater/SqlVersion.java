/*
 *     SPDX-License-Identifier: AGPL-3.0-only
 *
 *     Copyright (C) 2022 RainbowDashLabs and Contributor
 */

package de.chojo.sqlutil.updater;

/**
 * Class representing a version maintained by the {@link SqlUpdater}.
 * <p>
 * A version id defined by a major and a patch version.
 */
public class SqlVersion {
    private final int major;
    private final int patch;

    public SqlVersion(int major, int patch) {
        this.major = major;
        this.patch = patch;
    }

    /**
     * Major version
     *
     * @return major
     */
    public int major() {
        return major;
    }

    /**
     * Patch version
     *
     * @return patch
     */
    public int patch() {
        return patch;
    }
}
