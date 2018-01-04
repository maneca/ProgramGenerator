package joao.programgenerator.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import joao.programgenerator.pojos.Musica;

@Dao
public interface MusicaDao {

    @Query("SELECT * FROM Musicas")
    LiveData<List<Musica>> getAllMusicas();

    @Insert
    void insertMusica(Musica musica);

    @Query("UPDATE Musicas SET music_name = :name WHERE music_number = :number")
    void updateNameMusica(String name, int number);

    @Delete
    void deleteMusica(Musica m);

    @Query("DELETE FROM Musicas WHERE parte_eucaristia_id = :parte AND music_number = :number")
    void removeParteFromMusica(int parte, int number);

    @Query("SELECT parte_eucaristia_id FROM Musicas WHERE music_number = :number")
    List<Integer> getPartesFromMusica(int number);

    @Query("SELECT * FROM Musicas WHERE parte_eucaristia_id = :parte")
    LiveData<List<Musica>> getMusicasFromParte(int parte);

    @Query("SELECT * FROM Musicas WHERE parte_eucaristia_id = :parte")
    List<Musica> getListOfMusicas(int parte);


}
