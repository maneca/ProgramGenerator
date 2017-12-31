package joao.programgenerator.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import joao.programgenerator.pojos.ParteEucaristia;

@Dao
public interface PartesEucaristiaDao {

    @Query("SELECT name FROM PartesEucaristia")
    LiveData<List<String>> getAllNomePartes();

    @Insert
    void insertParte(ParteEucaristia parteEucaristia);
}
