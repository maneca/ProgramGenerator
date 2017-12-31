package joao.programgenerator.pojos;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "PartesEucaristia")
public class ParteEucaristia {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public ParteEucaristia(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
