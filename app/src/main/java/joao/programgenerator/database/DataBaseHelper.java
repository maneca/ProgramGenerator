package joao.programgenerator.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Joao on 03-04-2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "database";
    private static final int DATABASE_VERSION = 1;

    // TABLE PARTES_EUCARISTIA
    public static final String PARTE_ID = "_id";
    public static final String PARTE_NAME = "name";
    public static final String PARTES_TABLE_NAME = "Partes_Eucaristia";

    public static final String CREATE_TABLE_PARTES = "create table "+ PARTES_TABLE_NAME +
                                                     " ("+PARTE_ID+" integer primary key autoincrement, "+
                                                     PARTE_NAME+" text not null);";

    // TABLE PARTE_MUSICA

    public static final String PARTE_MUSICA_ID = "_id";
    public static final String PARTE_EUCARISTIA_ID = "parte_id";
    public static final String MUSIC_NUMBER = "music_number";
    public static final String PARTE_MUSICA_TABLE_NAME = "Parte_Musica";

    public static final String CREATE_TABLE_PARTE_MUSICA = "create table "+ PARTE_MUSICA_TABLE_NAME +
                                                           " ("+PARTE_MUSICA_ID+" integer primary key autoincrement, "+
                                                           PARTE_EUCARISTIA_ID+" integer,"+
                                                           MUSIC_NUMBER+" integer," +
                                                           "FOREIGN KEY("+ PARTE_EUCARISTIA_ID +") REFERENCES "+ PARTES_TABLE_NAME +" ("+PARTE_ID+") );";

    public DataBaseHelper(Context context) {

        super(context, DATA_BASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        try{
            db.execSQL(CREATE_TABLE_PARTES);
            db.execSQL(CREATE_TABLE_PARTE_MUSICA);

            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Entrada');");           // 1
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Acto Penitencial');");  // 2
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Aleluia');");           // 3
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Ofertório');");         // 4
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Santo');");             // 5
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Pai-Nosso');");         // 6
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Paz');");               // 7
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Comunhão');");          // 8
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Acção de Graças');");   // 9
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Final');");             // 10


        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        db.execSQL("DROP TABLE IF EXISTS "+ PARTE_MUSICA_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+ PARTES_TABLE_NAME);

        onCreate(db);

    }


}
