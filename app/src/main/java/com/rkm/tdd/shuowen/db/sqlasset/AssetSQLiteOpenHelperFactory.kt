package com.rkm.tdd.shuowen.db.sqlasset

import androidx.sqlite.db.SupportSQLiteOpenHelper

class AssetSQLiteOpenHelperFactory : SupportSQLiteOpenHelper.Factory {

    override fun create(configuration: SupportSQLiteOpenHelper.Configuration): SupportSQLiteOpenHelper {
        return AssetSQLiteOpenHelper(
            configuration.context, configuration.name, configuration.callback
        )
    }
}
