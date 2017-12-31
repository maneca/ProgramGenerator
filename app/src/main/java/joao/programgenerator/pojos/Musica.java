package joao.programgenerator.pojos;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Musicas",
        foreignKeys = {
            @ForeignKey(entity = ParteEucaristia.class,
                        parentColumns = "id",
                        childColumns = "parte_eucaristia_id")
        })
public class Musica {

    @PrimaryKey
    private int id;
    private int parte_eucaristia_id;
    private int music_number;
    private String music_name;

    public Musica(){

    }

    public Musica(int id, String name){
        this.music_number = id;
        this.music_name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParte_eucaristia_id() {
        return parte_eucaristia_id;
    }

    public void setParte_eucaristia_id(int parte_eucaristia_id) {
        this.parte_eucaristia_id = parte_eucaristia_id;
    }

    public int getMusic_number() {
        return music_number;
    }

    public void setMusic_number(int music_number) {
        this.music_number = music_number;
    }

    public String getMusic_name() {
        return music_name;
    }

    public void setMusic_name(String music_name) {
        this.music_name = music_name;
    }
}
