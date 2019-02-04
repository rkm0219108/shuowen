package com.rkm.tdd.shuowen.db.sqlasset

import timber.log.Timber
import java.util.*
import java.util.regex.Pattern

/**
 * Compare paths by their upgrade version numbers, instead of using
 * alphanumeric comparison on plain file names. This prevents the upgrade
 * scripts from being applied out of order when they first move to double-,
 * triple-, etc. digits.
 *
 *
 * For example, this fixes an upgrade that would apply 2 different upgrade
 * files from version 9 to 11 (`..._updated_9_10` and
 * `..._updated_10_11`) from using the *incorrect*
 * alphanumeric order of `10_11` before `9_10`.
 *
 */
internal class VersionComparator : Comparator<String> {

    private val pattern = Pattern.compile(".*_upgrade_([0-9]+)-([0-9]+).*")

    /**
     * Compares the two specified upgrade script strings to determine their
     * relative ordering considering their two version numbers. Assumes all
     * database names used are the same, as this function only compares the
     * two version numbers.
     *
     * @param file0 an upgrade script file name
     * @param file1 a second upgrade script file name to compare with file0
     * @return an integer < 0 if file0 should be applied before file1, 0 if
     * they are equal (though that shouldn't happen), and > 0 if
     * file0 should be applied after file1.
     * @throws SQLiteAssetHelper.SQLiteAssetException thrown if the strings are not in the correct upgrade
     * script format of:
     * `databasename_fromVersionInteger_toVersionInteger`
     */
    override fun compare(file0: String, file1: String): Int {
        val m0 = pattern.matcher(file0)
        val m1 = pattern.matcher(file1)

        if (!m0.matches()) {
            Timber.w(TAG, "could not parse upgrade script file: $file0")
            throw SQLiteAssetHelper.SQLiteAssetException("Invalid upgrade script file")
        }

        if (!m1.matches()) {
            Timber.w(TAG, "could not parse upgrade script file: $file1")
            throw SQLiteAssetHelper.SQLiteAssetException("Invalid upgrade script file")
        }

        val v0From = Integer.valueOf(m0.group(1))
        val v1From = Integer.valueOf(m1.group(1))
        val v0To = Integer.valueOf(m0.group(2))
        val v1To = Integer.valueOf(m1.group(2))

        if (v0From == v1From) {
            // 'from' versions match for both; check 'to' version next

            if (v0To == v1To) {
                return 0
            }

            return if (v0To < v1To) -1 else 1
        }

        return if (v0From < v1From) -1 else 1
    }

    companion object {

        private val TAG = SQLiteAssetHelper::class.java.simpleName
    }
}
