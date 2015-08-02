package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import joao.programgenerator.R;
import joao.programgenerator.database.PartesMusicaHandler;

public class NovaMusicaActivity extends ActionBarActivity implements View.OnClickListener {

    private EditText numero, nome;
    private CheckedTextView entrada , salmo, aleluia, acto_penitencial, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;
    private ArrayList<Integer> selected = new ArrayList<>(), original = new ArrayList<>();
    private String original_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_musica);

        Bundle b = getIntent().getExtras();


        // NUMERO DA MUSICA
        numero = (EditText) findViewById(R.id.numero_musica);
        nome = (EditText) findViewById(R.id.nome_musica);

        // CHECKBOXES
        entrada = (CheckedTextView) findViewById(R.id.entrada);
        salmo = (CheckedTextView) findViewById(R.id.salmo);
        aleluia = (CheckedTextView) findViewById(R.id.aleluia);
        acto_penitencial = (CheckedTextView) findViewById(R.id.acto_penitencial);
        ofertorio = (CheckedTextView) findViewById(R.id.ofertorio);
        santo = (CheckedTextView) findViewById(R.id.santo);
        pai_nosso = (CheckedTextView) findViewById(R.id.pai_nosso);
        paz = (CheckedTextView) findViewById(R.id.paz);
        comunhao = (CheckedTextView) findViewById(R.id.comunhao);
        accao_gracas = (CheckedTextView) findViewById(R.id.accao_gracas);
        final_ = (CheckedTextView) findViewById(R.id.final_);

        if(b.getInt("music_number")!=-1){
            setTitle(getString(R.string.editar_musica));

            numero.setText("" + b.getInt("music_number"));
        }
        else setTitle(getString(R.string.nova_musica));

        original_name = b.getString("music_name");
        nome.setText(b.getString("music_name"));

        if(b.getIntegerArrayList("music_parts")!=null){

            for(int parte: b.getIntegerArrayList("music_parts")){

                Log.d("parte", ""+parte);

                original.add(parte);

                switch(parte){

                    case 1: entrada.setChecked(true);
                        break;

                    case 2: acto_penitencial.setChecked(true);
                        break;

                    case 3: salmo.setChecked(true);
                        break;

                    case 4: aleluia.setChecked(true);
                        break;

                    case 5: ofertorio.setChecked(true);
                        break;

                    case 6: santo.setChecked(true);
                        break;

                    case 7: pai_nosso.setChecked(true);
                        break;

                    case 8: paz.setChecked(true);
                        break;

                    case 9: comunhao.setChecked(true);
                        break;

                    case 10:    accao_gracas.setChecked(true);
                        break;

                    case 11:    final_.setChecked(true);
                        break;
                }

            }

        }

        selected = (ArrayList<Integer>) original.clone();

        Button ok = (Button) findViewById(R.id.ok);
        Button cancel = (Button) findViewById(R.id.cancel);

        entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(entrada.isChecked()){
                    selected.remove((Integer) 1);
                }else selected.add(1);

                entrada.toggle();
            }
        });

        acto_penitencial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(acto_penitencial.isChecked()){
                    selected.remove((Integer) 2);
                }else selected.add(2);

                acto_penitencial.toggle();
            }
        });

        salmo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(salmo.isChecked()){
                    selected.remove((Integer) 3);
                }else selected.add(3);

                salmo.toggle();
            }
        });

        aleluia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aleluia.isChecked()){
                    selected.remove((Integer) 4);
                }else selected.add(4);

                aleluia.toggle();
            }
        });

        ofertorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ofertorio.isChecked()){
                    selected.remove((Integer) 5);
                }else selected.add(5);

                ofertorio.toggle();
            }
        });

        santo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(santo.isChecked()){
                    selected.remove((Integer) 6);
                }else selected.add(6);

                santo.toggle();
            }
        });

        pai_nosso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pai_nosso.isChecked()){
                    selected.remove((Integer) 7);
                }else selected.add(7);

                pai_nosso.toggle();
            }
        });

        paz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(paz.isChecked()){
                    selected.remove((Integer) 8);
                }else selected.add(8);

                paz.toggle();
            }
        });

        comunhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(comunhao.isChecked()){
                    selected.remove((Integer) 9);
                }else selected.add(9);

                comunhao.toggle();
            }
        });

        accao_gracas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(accao_gracas.isChecked()){
                    selected.remove((Integer) 10);
                }else selected.add(10);

                accao_gracas.toggle();
            }
        });

        final_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(final_.isChecked()){
                    selected.remove((Integer) 11);
                }else selected.add(11);

                final_.toggle();
            }
        });

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.ok:   saveOrEditMusic();

                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle(getString(R.string.operation_title));
                dialog.setMessage(getString(R.string.operation_message));
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        Intent intent = new Intent(NovaMusicaActivity.this, ListaMusicas.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialog.show();

                break;

            case R.id.cancel:   Intent intent = new Intent(NovaMusicaActivity.this, ListaMusicas.class);
                startActivity(intent);
                finish();

                break;
        }
    }

    private void saveOrEditMusic(){
        PartesMusicaHandler musica_handler = new PartesMusicaHandler(getApplicationContext());

        musica_handler.open();

        String musica = numero.getText().toString();
        String name = nome.getText().toString();

        if(musica.equals("") || name.equals("") ||
                (!entrada.isChecked() && !aleluia.isChecked() && !salmo.isChecked() && !acto_penitencial.isChecked() &&
                        !ofertorio.isChecked() && !santo.isChecked() && !pai_nosso.isChecked() &&
                        !paz.isChecked() && !comunhao.isChecked() && !accao_gracas.isChecked() && !final_.isChecked()))
            Toast.makeText(getApplicationContext(), getString(R.string.campos_obrigatorios), Toast.LENGTH_LONG).show();
        else{
            int number = Integer.parseInt(musica);

            if(getTitle().toString().contains("Editar")){

                ArrayList<Integer> to_add = (ArrayList<Integer>) selected.clone();
                to_add.removeAll(original);

                ArrayList<Integer> to_remove = (ArrayList<Integer>) original.clone();
                to_remove.removeAll(selected);


                for(int parte: to_remove) {
                    musica_handler.removeParteMusica(parte, number);
                }

                for(int parte: to_add){
                    musica_handler.insertParteMusica(parte, number, name);
                }


                if(!original_name.equals(name)){
                        musica_handler.updateNameMusica(number, name);
                }

            }else {



                if (entrada.isChecked())
                    musica_handler.insertParteMusica(1, number, name);

                if (acto_penitencial.isChecked())
                    musica_handler.insertParteMusica(2, number, name);

                if (salmo.isChecked())
                    musica_handler.insertParteMusica(3, number, name);

                if (aleluia.isChecked())
                    musica_handler.insertParteMusica(4, number, name);

                if (ofertorio.isChecked())
                    musica_handler.insertParteMusica(5, number, name);

                if (santo.isChecked())
                    musica_handler.insertParteMusica(6, number, name);

                if (pai_nosso.isChecked())
                    musica_handler.insertParteMusica(7, number, name);

                if (paz.isChecked())
                    musica_handler.insertParteMusica(8, number, name);

                if (comunhao.isChecked())
                    musica_handler.insertParteMusica(9, number, name);

                if (accao_gracas.isChecked())
                    musica_handler.insertParteMusica(10, number, name);

                if (final_.isChecked())
                    musica_handler.insertParteMusica(11, number, name);
            }
        }

        musica_handler.close();

    }
}
