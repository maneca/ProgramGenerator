package joao.programgenerator.database;


import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomDatabase;

import joao.programgenerator.database.dao.MusicaDao;
import joao.programgenerator.database.dao.PartesEucaristiaDao;
import joao.programgenerator.pojos.Musica;
import joao.programgenerator.pojos.ParteEucaristia;

@Database(entities = {ParteEucaristia.class, Musica.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase{


    public abstract PartesEucaristiaDao partesEucaristiaDao();

    public abstract MusicaDao musicaDao();

    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }


}
