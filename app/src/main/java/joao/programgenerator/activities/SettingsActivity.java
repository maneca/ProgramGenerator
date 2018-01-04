package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import joao.programgenerator.R;

public class SettingsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
    public static final String PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ListView settings = findViewById(R.id.listView);

        String[] options = {"Informacao que aparece no programa"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, options);

        settings.setAdapter(adapter);

        settings.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        String nome_numero = sharedPreferences.getString("nome_numero", "Numero");
        int checked;

        if (nome_numero.equalsIgnoreCase("Numero"))
            checked = 0;
        else checked = 1;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setSingleChoiceItems(R.array.numero_ou_nome, checked, (dialog, which) -> {
                    String[] options = getResources().getStringArray(R.array.numero_ou_nome);

                    SharedPreferences sharedPreferences1 = getSharedPreferences(PREFS_NAME, 0);
                    SharedPreferences.Editor editor = sharedPreferences1.edit();
                    editor.putString("nome_numero", options[which]);
                    editor.apply();

                }).setPositiveButton("OK", (dialog, id1) -> {

                    SharedPreferences sharedPreferences12 = getSharedPreferences(PREFS_NAME, 0);
                    String nome_numero1 = sharedPreferences12.getString("nome_numero", "");

                    Log.d("index", nome_numero1);
                }).setNegativeButton("Cancelar", (dialog, id12) -> {

                }

        );

        builder.create();
        builder.show();
    }
}
