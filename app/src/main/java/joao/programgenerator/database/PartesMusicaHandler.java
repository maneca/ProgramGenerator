package joao.programgenerator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Joao on 03-04-2015.
 */
public class PartesMusicaHandler {

    DataBaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase db;

    public PartesMusicaHandler(Context ctx){

        this.ctx = ctx;

        dbhelper = new DataBaseHelper(ctx);
    }


    public PartesMusicaHandler open(){

        db = dbhelper.getWritableDatabase();

        return this;
    }

    public void close(){

        dbhelper.close();
    }

    public long insertParteMusica(int parte_id, int music_number){

        ContentValues content = new ContentValues();
        content.put(DataBaseHelper.PARTE_EUCARISTIA_ID,parte_id);
        content.put(DataBaseHelper.MUSIC_NUMBER, music_number);

        return db.insertOrThrow(DataBaseHelper.PARTE_MUSICA_TABLE_NAME, null, content);

    }

    public Cursor getMusicasForParte(int parte_id){

        return db.query(DataBaseHelper.PARTE_MUSICA_TABLE_NAME,
                    new String[]{DataBaseHelper.MUSIC_NUMBER},
                    DataBaseHelper.PARTE_EUCARISTIA_ID + " =?",
                    new String[]{""+parte_id},
                    null, null, null);

    }

    public boolean removeParteMusica(int parte_id, int music_number){

        return db.delete(DataBaseHelper.PARTE_MUSICA_TABLE_NAME,
                         DataBaseHelper.PARTE_EUCARISTIA_ID + " =? AND "+ DataBaseHelper.MUSIC_NUMBER + " =?",
                new String[]{""+parte_id, ""+music_number}) > 0;

    }

}
