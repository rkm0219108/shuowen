package com.rkm.tdd.shuowen.db.sqlasset

import android.database.sqlite.SQLiteStatement
import androidx.sqlite.db.SupportSQLiteStatement

/**
 * Delegates all calls to a [SQLiteStatement].
 */
internal class FrameworkSQLiteStatement(private val mDelegate: SQLiteStatement) : SupportSQLiteStatement {

    override fun bindNull(index: Int) {
        mDelegate.bindNull(index)
    }

    override fun bindLong(index: Int, value: Long) {
        mDelegate.bindLong(index, value)
    }

    override fun bindDouble(index: Int, value: Double) {
        mDelegate.bindDouble(index, value)
    }

    override fun bindString(index: Int, value: String) {
        mDelegate.bindString(index, value)
    }

    override fun bindBlob(index: Int, value: ByteArray) {
        mDelegate.bindBlob(index, value)
    }

    override fun clearBindings() {
        mDelegate.clearBindings()
    }

    override fun execute() {
        mDelegate.execute()
    }

    override fun executeUpdateDelete(): Int {
        return mDelegate.executeUpdateDelete()
    }

    override fun executeInsert(): Long {
        return mDelegate.executeInsert()
    }

    override fun simpleQueryForLong(): Long {
        return mDelegate.simpleQueryForLong()
    }

    override fun simpleQueryForString(): String {
        return mDelegate.simpleQueryForString()
    }

    override fun close() {
        mDelegate.close()
    }
}
