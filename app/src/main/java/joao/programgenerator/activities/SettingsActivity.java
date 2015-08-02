package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import joao.programgenerator.R;

public class SettingsActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
    public static final String PREFS_NAME = "MyPrefsFile";
    private ListView settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        settings = (ListView) findViewById(R.id.listView);

        String[] options = {"Informacao que aparece no programa"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options);

        settings.setAdapter(adapter);

        settings.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final ArrayList mSelectedItems = new ArrayList();

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String nome_numero = sharedPreferences.getString("nome_numero", "Numero");
        int checked;

        if (nome_numero.equalsIgnoreCase("Numero"))
            checked = 0;
        else checked = 1;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.numero_ou_nome, checked,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String[] options = getResources().getStringArray(R.array.numero_ou_nome);

                        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("nome_numero", options[which]);
                        editor.commit();

                    }
                }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
                        String nome_numero = sharedPreferences.getString("nome_numero", "");

                        Log.d("index", nome_numero);
                    }
                }).setNegativeButton("Cancelar",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog,int id){

                    }
                }

        );

        builder.create();
        builder.show();
    }
}
