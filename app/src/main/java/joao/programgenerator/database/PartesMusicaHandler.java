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

    public long insertParteMusica(int parte_id, int music_number, String music_name){

        ContentValues content = new ContentValues();
        content.put(DataBaseHelper.PARTE_EUCARISTIA_ID,parte_id);
        content.put(DataBaseHelper.MUSIC_NUMBER, music_number);
        content.put(DataBaseHelper.MUSIC_NAME, music_name);

        return db.insertOrThrow(DataBaseHelper.PARTE_MUSICA_TABLE_NAME, null, content);

    }

    public Cursor getMusicasForParte(int parte_id){

        return db.rawQuery("SELECT " + DataBaseHelper.MUSIC_NUMBER + ", " + DataBaseHelper.MUSIC_NAME +
                " FROM " + DataBaseHelper.PARTE_MUSICA_TABLE_NAME +
                " WHERE " + DataBaseHelper.PARTE_EUCARISTIA_ID + " = " + parte_id +
                " ORDER BY " + DataBaseHelper.MUSIC_NUMBER + " ASC", null);

    }

    public Cursor getPartesForMusica(int musica_id){

        return db.query(DataBaseHelper.PARTE_MUSICA_TABLE_NAME,
                new String[]{DataBaseHelper.PARTE_EUCARISTIA_ID},
                DataBaseHelper.MUSIC_NUMBER + " =?",
                new String[]{"" + musica_id},
                null, null, null);
    }

    public boolean updateNameMusica(int music_number, String music_name){

        Cursor c = db.rawQuery("UPDATE "+ DataBaseHelper.PARTE_MUSICA_TABLE_NAME+
                           " SET "+DataBaseHelper.MUSIC_NAME+" = '"+music_name+
                           "' WHERE "+DataBaseHelper.MUSIC_NUMBER +" = "+music_number, null);

        return c.getCount()>0;
    }

    public boolean removeParteMusica(int parte_id, int music_number){

        return db.delete(DataBaseHelper.PARTE_MUSICA_TABLE_NAME,
                         DataBaseHelper.PARTE_EUCARISTIA_ID + " =? AND "+ DataBaseHelper.MUSIC_NUMBER + " =?",
                         new String[]{""+parte_id, ""+music_number}) > 0;

    }

}
