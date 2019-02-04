package com.rkm.tdd.shuowen.db.sqlasset

import android.content.Context
import android.database.DatabaseErrorHandler
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper

internal class AssetSQLiteOpenHelper(
    context: Context, name: String?,
    callback: SupportSQLiteOpenHelper.Callback
) : SupportSQLiteOpenHelper {
    private val mDelegate: OpenHelper

    init {
        mDelegate = createDelegate(context, name, callback)
    }

    private fun createDelegate(context: Context, name: String?, callback: SupportSQLiteOpenHelper.Callback): OpenHelper {
        val dbRef = arrayListOf<SupportSQLiteDatabase>()
        val errorHandler = DatabaseErrorHandler {
            val database = dbRef[0]
            callback.onCorruption(database)
        }
        return object : OpenHelper(context, name, dbRef, callback, errorHandler) {
            override fun onCreate(db: SQLiteDatabase) {
                mWrappedDb = FrameworkSQLiteDatabase(db)
                callback.onCreate(mWrappedDb)
            }

            override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                callback.onUpgrade(getWrappedDb(db), oldVersion, newVersion)
            }

            override fun onConfigure(db: SQLiteDatabase) {
                callback.onConfigure(getWrappedDb(db))
            }

            override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
                callback.onDowngrade(getWrappedDb(db), oldVersion, newVersion)
            }

            override fun onOpen(db: SQLiteDatabase) {
                callback.onOpen(getWrappedDb(db))
            }
        }
    }

    override fun getDatabaseName(): String {
        return mDelegate.databaseName
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    override fun setWriteAheadLoggingEnabled(enabled: Boolean) {
        mDelegate.setWriteAheadLoggingEnabled(enabled)
    }

    override fun getWritableDatabase(): SupportSQLiteDatabase {
        return mDelegate.writableSupportDatabase
    }

    override fun getReadableDatabase(): SupportSQLiteDatabase {
        return mDelegate.readableSupportDatabase
    }

    override fun close() {
        mDelegate.close()
    }

    internal abstract class OpenHelper(
        context: Context,
        name: String?,
        private val dbRef: ArrayList<SupportSQLiteDatabase>,
        callback: SupportSQLiteOpenHelper.Callback,
        errorHandler: DatabaseErrorHandler
    ) : SQLiteAssetHelper(context, name, null, null, callback.version, errorHandler) {

        var mWrappedDb: FrameworkSQLiteDatabase? = null

        val writableSupportDatabase: SupportSQLiteDatabase
            get() {
                val db = super.getWritableDatabase()
                return getWrappedDb(db)
            }

        val readableSupportDatabase: SupportSQLiteDatabase
            get() {
                val db = super.getReadableDatabase()
                return getWrappedDb(db)
            }

        fun getWrappedDb(sqLiteDatabase: SQLiteDatabase): FrameworkSQLiteDatabase {
            if (mWrappedDb == null) {
                val database = FrameworkSQLiteDatabase(sqLiteDatabase)
                mWrappedDb = database
                dbRef.add(0, database)
            }
            return mWrappedDb as FrameworkSQLiteDatabase
        }

        @Synchronized
        override fun close() {
            super.close()
            mWrappedDb = null
        }
    }
}
