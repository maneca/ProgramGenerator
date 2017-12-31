package joao.programgenerator.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import joao.programgenerator.pojos.Musica;

@Dao
public interface MusicaDao {

    @Query("SELECT * FROM Musicas")
    LiveData<List<Musica>> getAllMusicas();

    @Insert
    void insertMusica(Musica musica);

    @Update
    void updateMusica(Musica musica);

    @Delete
    void deleteMusica(Musica musica);

    @Query("SELECT * FROM Musicas WHERE parte_eucaristia_id = :parte")
    LiveData<List<Musica>> getMusicasFromParte(int parte);
}
