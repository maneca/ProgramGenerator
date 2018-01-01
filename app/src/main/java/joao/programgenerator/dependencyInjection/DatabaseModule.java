package joao.programgenerator.dependencyInjection;


import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.ContentValues;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import joao.programgenerator.database.AppDatabase;
import joao.programgenerator.database.dao.MusicaDao;
import joao.programgenerator.database.dao.PartesEucaristiaDao;

@Module
public class DatabaseModule {

    @Singleton
    @Provides
    AppDatabase provideDb(Application app) {
        return Room.databaseBuilder(app, AppDatabase.class,"programgenerator.db")
                .fallbackToDestructiveMigration()
                .addCallback(new RoomDatabase.Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);

                        ContentValues contentValues = new ContentValues();
                        contentValues.put("name", "Entrada");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Acto Penitencial");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Salmo");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Aleluia");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Ofertório");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Santo");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Pai-nosso");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Paz");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Comunhão");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Acção de Graças");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                        contentValues.put("name", "Final");
                        db.insert("PartesEucaristia", OnConflictStrategy.IGNORE, contentValues);
                    }

                    @Override
                    public void onOpen(@NonNull SupportSQLiteDatabase db) {
                        super.onOpen(db);
                    }
                })
                .build();
    }

    @Singleton @Provides
    MusicaDao provideMusicaDao(AppDatabase db) {
        return db.musicaDao();
    }

    @Singleton @Provides
    PartesEucaristiaDao providePartesEucaristiaDao(AppDatabase db) {
        return db.partesEucaristiaDao();
    }

    @Provides
    @Singleton
    Executor provideExecutor(){

        return Executors.newSingleThreadExecutor();
    }



}
