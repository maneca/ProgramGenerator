package joao.programgenerator.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import joao.programgenerator.R;
import joao.programgenerator.dependencyInjection.Injectable;
import joao.programgenerator.dependencyInjection.ViewModelFactory;
import joao.programgenerator.pojos.Musica;
import joao.programgenerator.viewmodel.ListaMusicasViewModel;


public class ProgramaActivity extends AppCompatActivity implements Injectable{
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView entrada, aleluia, acto_penitencial, salmo, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;
    private String nome_numero;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        nome_numero = sharedPreferences.getString("nome_numero", "Numero");


        entrada = findViewById(R.id.entrada);
        aleluia = findViewById(R.id.aleluia);
        acto_penitencial = findViewById(R.id.acto_penitencial);
        TextView acto_penitencial_label = findViewById(R.id.acto_penitencial_label);
        salmo = findViewById(R.id.salmo);
        TextView salmo_label = findViewById(R.id.salmo_label);
        ofertorio = findViewById(R.id.ofertorio);
        santo = findViewById(R.id.santo);
        pai_nosso = findViewById(R.id.pai_nosso);
        TextView pai_nosso_label = findViewById(R.id.pai_nosso_label);
        paz = findViewById(R.id.paz);
        comunhao = findViewById(R.id.comunhao);
        accao_gracas = findViewById(R.id.accao_gracas);
        TextView accao_gracas_label = findViewById(R.id.accao_gracas_label);
        final_ = findViewById(R.id.last);

        TableRow row1 = findViewById(R.id.row_acto);
        TableRow row2 = findViewById(R.id.row_pai_nosso);
        TableRow row3 = findViewById(R.id.row_accao_gracas);
        TableRow row4 = findViewById(R.id.row_salmo);


        row1.setVisibility(View.GONE);
        acto_penitencial.setVisibility(View.GONE);
        acto_penitencial_label.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        pai_nosso.setVisibility(View.GONE);
        pai_nosso_label.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        accao_gracas.setVisibility(View.GONE);
        accao_gracas_label.setVisibility(View.GONE);
        row4.setVisibility(View.GONE);
        salmo.setVisibility(View.GONE);
        salmo_label.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            ArrayList seleccionados = bundle.getCharSequenceArrayList("escolhidas");

            if(seleccionados.contains(0)) {
                row1.setVisibility(View.VISIBLE);
                acto_penitencial_label.setVisibility(View.VISIBLE);
                acto_penitencial.setVisibility(View.VISIBLE);
            }

            if(seleccionados.contains(1)) {
                row4.setVisibility(View.VISIBLE);
                salmo_label.setVisibility(View.VISIBLE);
                salmo.setVisibility(View.VISIBLE);
            }


            if(seleccionados.contains(2)){
                row2.setVisibility(View.VISIBLE);
                pai_nosso_label.setVisibility(View.VISIBLE);
                pai_nosso.setVisibility(View.VISIBLE);
            }

            if(seleccionados.contains(3)) {
                row3.setVisibility(View.VISIBLE);
                accao_gracas_label.setVisibility(View.VISIBLE);
                accao_gracas.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_programa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_refresh:
                generatePrograma();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    private void generatePrograma(){
        ArrayList<Musica> programa = new ArrayList<>();

        // Geração dos números random
        Musica prog_entrada = returnMusic(1);
        programa.add(prog_entrada);

        Musica prog_aleluia = solveDuplicate(returnMusic(4), programa, 4);
        programa.add(prog_aleluia);

        Musica prog_ofertorio = solveDuplicate(returnMusic(5), programa, 5);
        programa.add(prog_ofertorio);

        Musica prog_santo = solveDuplicate(returnMusic(6), programa, 6);
        programa.add(prog_santo);

        Musica prog_paz = solveDuplicate(returnMusic(8), programa, 8);
        programa.add(prog_paz);

        Musica prog_comunhao = solveDuplicate(returnMusic(9), programa, 9);
        programa.add(prog_comunhao);

        Musica prog_final = solveDuplicate(returnMusic(11), programa, 11);
        programa.add(prog_final);

        if(prog_entrada == null)
            entrada.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            entrada.setText(prog_entrada.getMusic_name());
            entrada.setTextSize(14);
        }else entrada.setText(String.valueOf(prog_entrada.getMusic_number()));

        if(prog_aleluia == null)
            aleluia.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            aleluia.setText(prog_aleluia.getMusic_name());
            aleluia.setTextSize(14);
        }else aleluia.setText(String.valueOf(prog_aleluia.getMusic_number()));

        if(acto_penitencial.getVisibility() == View.VISIBLE){
            Musica prog_acto = solveDuplicate(returnMusic(2), programa, 2);
            programa.add(prog_acto);

            if( prog_acto == null)
                acto_penitencial.setText(getResources().getString(R.string.nd));
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                acto_penitencial.setText(prog_acto.getMusic_name());
                acto_penitencial.setTextSize(14);
            }else acto_penitencial.setText(String.valueOf(prog_acto.getMusic_number()));
        }

        if(salmo.getVisibility() == View.VISIBLE){
            Musica prog_salmo = solveDuplicate(returnMusic(3), programa, 3);
            programa.add(prog_salmo);

            if( prog_salmo == null)
                salmo.setText(getResources().getString(R.string.nd));
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                salmo.setText(prog_salmo.getMusic_name());
                salmo.setTextSize(14);
            }else salmo.setText(String.valueOf(prog_salmo.getMusic_number()));
        }

        if(prog_ofertorio == null)
            ofertorio.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            ofertorio.setText(prog_ofertorio.getMusic_name());
            ofertorio.setTextSize(14);
        }else ofertorio.setText(String.valueOf(prog_ofertorio.getMusic_number()));

        if(prog_santo == null)
            santo.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            santo.setText(prog_santo.getMusic_name());
            santo.setTextSize(14);
        }else santo.setText(String.valueOf(prog_santo.getMusic_number()));

        if(pai_nosso.getVisibility() == View.VISIBLE){
            Musica prog_pai_nosso = solveDuplicate(returnMusic(7), programa, 7);
            programa.add(prog_pai_nosso);

            if(prog_pai_nosso == null)
                pai_nosso.setText(getResources().getString(R.string.nd));
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                pai_nosso.setText(prog_pai_nosso.getMusic_name());
                pai_nosso.setTextSize(14);
            }else pai_nosso.setText(String.valueOf(prog_pai_nosso.getMusic_number()));
        }

        if(prog_paz == null)
            paz.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            paz.setText(prog_paz.getMusic_name());
            paz.setTextSize(14);
        }else paz.setText(String.valueOf(prog_paz.getMusic_number()));

        if(prog_comunhao == null)
            comunhao.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            comunhao.setText(prog_comunhao.getMusic_name());
            comunhao.setTextSize(14);
        }else comunhao.setText(String.valueOf(prog_comunhao.getMusic_number()));

        if(accao_gracas.getVisibility() == View.VISIBLE){
            Musica prog_accao_gracas = solveDuplicate(returnMusic(10), programa, 10);
            programa.add(prog_accao_gracas);

            if(prog_accao_gracas == null)
                accao_gracas.setText(getResources().getString(R.string.nd));
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                accao_gracas.setText(prog_accao_gracas.getMusic_name());
                accao_gracas.setTextSize(14);
            }else accao_gracas.setText(String.valueOf(prog_accao_gracas.getMusic_number()));
        }

        if(prog_final == null)
            final_.setText(getResources().getString(R.string.nd));
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            final_.setText(prog_final.getMusic_name());
            final_.setTextSize(14);
        }else final_.setText(String.valueOf(prog_final.getMusic_number()));
    }

    private Musica solveDuplicate(Musica musica, ArrayList<Musica> programa, int pos) {
        Musica new_musica;

        if(programa.indexOf(musica) != -1 ){
            new_musica = returnMusic(pos);
        }else new_musica = musica;

        return new_musica;
    }

    private int generateRandomNumber(int maximum){

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        return rand.nextInt(maximum + 1);
    }

    private Musica returnMusic(int parte){


        ListaMusicasViewModel listaMusicasViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListaMusicasViewModel.class);
        ArrayList<Musica> lista_musicas = listaMusicasViewModel.getListOfMusicas(parte);

        Musica result = null;

        if(lista_musicas.size()>0){
            int position = generateRandomNumber(lista_musicas.size()-1);
            result = lista_musicas.get(position);
        }

        return result;

    }
}
