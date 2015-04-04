package joao.programgenerator.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Joao on 03-04-2015.
 */
public class PartesEucaristiaHandler {

    DataBaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase db;

    public PartesEucaristiaHandler(Context ctx){

        this.ctx = ctx;

        dbhelper = new DataBaseHelper(ctx);
    }


    public PartesEucaristiaHandler open(){

        db = dbhelper.getWritableDatabase();

        return this;
    }

    public void close(){

        dbhelper.close();
    }

    public Cursor returnPartesEucaristia(){

        return db.query(DataBaseHelper.PARTES_TABLE_NAME,
                        new String[]{DataBaseHelper.PARTE_ID, DataBaseHelper.PARTE_NAME},
                        null, null, null, null, null);
    }

}
