package com.rkm.tdd.shuowen.di

import androidx.room.Room
import com.rkm.tdd.shuowen.AppLifecycleCallback
import com.rkm.tdd.shuowen.ShuowenApp
import com.rkm.tdd.shuowen.db.WordDataDb
import com.rkm.tdd.shuowen.db.dao.BookImageDao
import com.rkm.tdd.shuowen.db.dao.WordDao
import com.rkm.tdd.shuowen.db.sqlasset.AssetSQLiteOpenHelperFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {

    @Singleton
    @Provides
    @JvmStatic
    fun provideAppLifecycleCallback(): AppLifecycleCallback = AppLifecycleCallbackImpl()

    @Singleton
    @Provides
    @JvmStatic
    fun provideOfficerDataDb(app: ShuowenApp): WordDataDb =
        Room.databaseBuilder(app, WordDataDb::class.java, "shuowen.db")
            .openHelperFactory(AssetSQLiteOpenHelperFactory())
            .build()

    @Singleton
    @Provides
    @JvmStatic
    fun provideOfficerDao(db: WordDataDb): WordDao = db.wordDao()

    @Singleton
    @Provides
    @JvmStatic
    fun provideBookImageDao(db: WordDataDb): BookImageDao = db.bookImageDao()
}
