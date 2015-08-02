package joao.programgenerator.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Joao on 03-04-2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "database";
    private static final int DATABASE_VERSION = 2;

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
    public static final String MUSIC_NAME = "music_name";
    public static final String PARTE_MUSICA_TABLE_NAME = "Parte_Musica";

    public static final String CREATE_TABLE_PARTE_MUSICA = "create table "+ PARTE_MUSICA_TABLE_NAME +
                                                           " ("+PARTE_MUSICA_ID+" integer primary key autoincrement, "+
                                                           PARTE_EUCARISTIA_ID+" integer, "+
                                                           MUSIC_NUMBER+" integer, " +
                                                           MUSIC_NAME+" text, " +
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
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Salmo');");             // 3
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Aleluia');");           // 4
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Ofertório');");         // 5
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Santo');");             // 6
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Pai-Nosso');");         // 7
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Paz');");               // 8
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Comunhão');");          // 9
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Acção de Graças');");   // 10
            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+PARTE_NAME+") VALUES ('Final');");             // 11


        }catch(SQLException e){
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

        if(oldVersion==1 && newVersion == 2){
            db.execSQL("ALTER TABLE "+ DataBaseHelper.PARTE_MUSICA_TABLE_NAME +
                       " ADD COLUMN " + DataBaseHelper.MUSIC_NAME +" text;");

            db.execSQL("CREATE TABLE temp AS SELECT * FROM "+DataBaseHelper.PARTE_MUSICA_TABLE_NAME+";");
            db.execSQL("DROP TABLE "+DataBaseHelper.PARTE_MUSICA_TABLE_NAME+";");

            // UPDATE IDS DAS PARTES DA EUCARISTIA

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 11 + " WHERE "+ PARTE_NAME + " = 'Final';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 11 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 10);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 10 + " WHERE "+ PARTE_NAME + " = 'Acção de Graças';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 10 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 9);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 9 + " WHERE "+ PARTE_NAME + " = 'Comunhão';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 9 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 8);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 8 + " WHERE "+ PARTE_NAME + " = 'Paz';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 8 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 7);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 7 + " WHERE "+ PARTE_NAME + " = 'Pai-Nosso';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 7 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 6);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 6 + " WHERE "+ PARTE_NAME + " = 'Santo';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 6 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 5);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 5 + " WHERE "+ PARTE_NAME + " = 'Ofertório';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 5 + " WHERE "+ PARTE_EUCARISTIA_ID + " = "+ 4);

            db.execSQL("UPDATE "+ PARTES_TABLE_NAME + " SET " + PARTE_ID + " = " + 4 + " WHERE "+ PARTE_NAME + " = 'Aleluia';");
            db.execSQL("UPDATE temp SET " + PARTE_EUCARISTIA_ID + " = " + 4 + " WHERE " + PARTE_EUCARISTIA_ID + " = " + 3);

            db.execSQL("CREATE TABLE "+DataBaseHelper.PARTE_MUSICA_TABLE_NAME+" AS SELECT * FROM temp;");
            db.execSQL("DROP TABLE temp;");

            db.execSQL("INSERT INTO "+PARTES_TABLE_NAME+" ("+ PARTE_ID +", "+ PARTE_NAME+") VALUES (3, 'Salmo');");
        }

    }


}
