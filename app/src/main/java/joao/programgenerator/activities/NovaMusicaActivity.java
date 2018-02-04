package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import joao.programgenerator.R;
import joao.programgenerator.dependencyInjection.Injectable;
import joao.programgenerator.dependencyInjection.ViewModelFactory;
import joao.programgenerator.viewmodel.MusicaViewModel;

public class NovaMusicaActivity extends AppCompatActivity implements Injectable, View.OnClickListener {

    private EditText numero, nome;
    private CheckedTextView entrada , salmo, gloria, aleluia, acto_penitencial, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;
    private ArrayList<Integer> selected = new ArrayList<>(), original = new ArrayList<>();
    private String original_name;
    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nova_musica);

        Bundle b = getIntent().getExtras();

        // NUMERO DA MUSICA
        numero = findViewById(R.id.numero_musica);
        nome = findViewById(R.id.nome_musica);

        // CHECKBOXES
        entrada = findViewById(R.id.entrada);
        salmo = findViewById(R.id.salmo);
        gloria = findViewById(R.id.gloria);
        aleluia = findViewById(R.id.aleluia);
        acto_penitencial = findViewById(R.id.acto_penitencial);
        ofertorio = findViewById(R.id.ofertorio);
        santo = findViewById(R.id.santo);
        pai_nosso = findViewById(R.id.pai_nosso);
        paz = findViewById(R.id.paz);
        comunhao = findViewById(R.id.comunhao);
        accao_gracas = findViewById(R.id.accao_gracas);
        final_ = findViewById(R.id.final_);

        if(b.getInt("music_number")!=-1){
            setTitle(getString(R.string.editar_musica));
            original_name = b.getString("music_name");
            numero.setText(String.valueOf(b.getInt("music_number")));
        }
        else setTitle(getString(R.string.nova_musica));

        nome.setText(b.getString("music_name"));

        if(b.getIntegerArrayList("music_parts")!=null){

            for(int parte: b.getIntegerArrayList("music_parts")){
                original.add(parte);

                switch(parte){

                    case 1:
                        entrada.setChecked(true);
                        break;

                    case 2:
                        acto_penitencial.setChecked(true);
                        break;

                    case 3:
                        gloria.setChecked(true);
                        break;

                    case 4:
                        salmo.setChecked(true);
                        break;

                    case 5:
                        aleluia.setChecked(true);
                        break;

                    case 6:
                        ofertorio.setChecked(true);
                        break;

                    case 7:
                        santo.setChecked(true);
                        break;

                    case 8:
                        pai_nosso.setChecked(true);
                        break;

                    case 9:
                        paz.setChecked(true);
                        break;

                    case 10:
                        comunhao.setChecked(true);
                        break;

                    case 11:
                        accao_gracas.setChecked(true);
                        break;

                    case 12:
                        final_.setChecked(true);
                        break;
                }

            }

        }

        selected = (ArrayList<Integer>) original.clone();

        Button ok = findViewById(R.id.ok);
        Button cancel = findViewById(R.id.cancel);

        entrada.setOnClickListener(v -> {
            if(entrada.isChecked()){
                selected.remove((Integer) 1);
            }else selected.add(1);

            entrada.toggle();
        });

        acto_penitencial.setOnClickListener(v -> {
            if(acto_penitencial.isChecked()){
                selected.remove((Integer) 2);
            }else selected.add(2);

            acto_penitencial.toggle();
        });

        gloria.setOnClickListener(v -> {
            if(gloria.isChecked()){
                selected.remove((Integer) 3);
            }else selected.add(3);

            gloria.toggle();
        });

        salmo.setOnClickListener(v -> {
            if(salmo.isChecked()){
                selected.remove((Integer) 4);
            }else selected.add(4);

            salmo.toggle();
        });

        aleluia.setOnClickListener(v -> {
            if(aleluia.isChecked()){
                selected.remove((Integer) 5);
            }else selected.add(5);

            aleluia.toggle();
        });

        ofertorio.setOnClickListener(v -> {
            if(ofertorio.isChecked()){
                selected.remove((Integer) 6);
            }else selected.add(6);

            ofertorio.toggle();
        });

        santo.setOnClickListener(v -> {
            if(santo.isChecked()){
                selected.remove((Integer) 7);
            }else selected.add(7);

            santo.toggle();
        });

        pai_nosso.setOnClickListener(v -> {
            if(pai_nosso.isChecked()){
                selected.remove((Integer) 8);
            }else selected.add(8);

            pai_nosso.toggle();
        });

        paz.setOnClickListener(v -> {
            if(paz.isChecked()){
                selected.remove((Integer) 9);
            }else selected.add(9);

            paz.toggle();
        });

        comunhao.setOnClickListener(v -> {
            if(comunhao.isChecked()){
                selected.remove((Integer) 10);
            }else selected.add(10);

            comunhao.toggle();
        });

        accao_gracas.setOnClickListener(v -> {
            if(accao_gracas.isChecked()){
                selected.remove((Integer) 11);
            }else selected.add(11);

            accao_gracas.toggle();
        });

        final_.setOnClickListener(v -> {
            if(final_.isChecked()){
                selected.remove((Integer) 12);
            }else selected.add(12);

            final_.toggle();
        });

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.ok:
                saveOrEditMusic();

                break;

            case R.id.cancel:
                Intent intent = new Intent(NovaMusicaActivity.this, ListaMusicasActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }

    private void saveOrEditMusic(){
        MusicaViewModel musicaViewModel = ViewModelProviders.of(this, viewModelFactory).get(MusicaViewModel.class);

        String musica = numero.getText().toString();
        String name = nome.getText().toString();

        if(musica.equals("") || name.equals("") ||
                (!entrada.isChecked() && !aleluia.isChecked() && !gloria.isChecked() && !salmo.isChecked() && !acto_penitencial.isChecked() &&
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
                    musicaViewModel.removeParteFromMusica(parte, number);
                }

                for(int parte: to_add){
                    musicaViewModel.insertMusica(parte, number, name);
                }


                if(!original_name.equals(name)){
                        musicaViewModel.updateNameMusica(name, number);
                }

            }else {

                if (entrada.isChecked())
                    musicaViewModel.insertMusica(1, number, name);

                if (acto_penitencial.isChecked())
                    musicaViewModel.insertMusica(2, number, name);

                if(gloria.isChecked())
                    musicaViewModel.insertMusica(3, number, name);

                if (salmo.isChecked())
                    musicaViewModel.insertMusica(4, number, name);

                if (aleluia.isChecked())
                    musicaViewModel.insertMusica(5, number, name);

                if (ofertorio.isChecked())
                    musicaViewModel.insertMusica(6, number, name);

                if (santo.isChecked())
                    musicaViewModel.insertMusica(7, number, name);

                if (pai_nosso.isChecked())
                    musicaViewModel.insertMusica(8, number, name);

                if (paz.isChecked())
                    musicaViewModel.insertMusica(9, number, name);

                if (comunhao.isChecked())
                    musicaViewModel.insertMusica(10, number, name);

                if (accao_gracas.isChecked())
                    musicaViewModel.insertMusica(11, number, name);

                if (final_.isChecked())
                    musicaViewModel.insertMusica(12, number, name);
            }

            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle(getString(R.string.operation_title));
            dialog.setMessage(getString(R.string.operation_message));
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), (dialog1, which) -> finish());

            dialog.show();
        }

    }
}
