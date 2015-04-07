package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import joao.programgenerator.R;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private Button gerar, gerar_especial, listar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gerar = (Button) findViewById(R.id.gerar);
        gerar_especial = (Button) findViewById(R.id.gerar_especial);
        listar = (Button) findViewById(R.id.listar);

        gerar.setOnClickListener(this);
        gerar_especial.setOnClickListener(this);
        listar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.gerar:    Intent intent = new Intent(MainActivity.this, Programa.class);
                                startActivity(intent);

                                break;

            case R.id.gerar_especial:   final ArrayList mSelectedItems = new ArrayList();  // Where we track the selected items
                                        AlertDialog.Builder builder = new AlertDialog.Builder(this);
                                        // Set the dialog title
                                        builder.setTitle("Partes especiais")
                                               .setMultiChoiceItems(R.array.planets_array, null,
                                                       new DialogInterface.OnMultiChoiceClickListener() {
                                                           @Override
                                                           public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                                               if (isChecked)
                                                                   mSelectedItems.add(which);
                                                               else if (mSelectedItems.contains(which))
                                                                   mSelectedItems.remove(Integer.valueOf(which));
                                                           }
                                                       })
                                        // Set the action buttons
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                                Intent intent = new Intent(MainActivity.this, Programa.class);

                                                Bundle bundle = new Bundle();

                                                bundle.putCharSequenceArrayList("escolhidas", mSelectedItems);

                                                intent.putExtras(bundle);

                                                startActivity(intent);

                                            }
                                        })
                                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });

                                        builder.create();
                                        builder.show();

                                        break;

            case R.id.listar:   Intent listagem = new Intent(MainActivity.this, ListaMusicas.class);

                                startActivity(listagem);

                                break;
        }
    }

}
