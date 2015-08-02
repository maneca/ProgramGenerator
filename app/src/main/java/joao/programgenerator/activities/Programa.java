package joao.programgenerator.activities;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import joao.programgenerator.R;
import joao.programgenerator.custom.Musica;
import joao.programgenerator.database.PartesMusicaHandler;


public class Programa extends ActionBarActivity {
    public static final String PREFS_NAME = "MyPrefsFile";
    private TextView entrada, aleluia, acto_penitencial, salmo, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;
    private TextView acto_penitencial_label, pai_nosso_label, accao_gracas_label, salmo_label;
    private TableRow row1, row2, row3, row4;
    private String nome_numero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, 0);
        nome_numero = sharedPreferences.getString("nome_numero", "Numero");


        entrada = (TextView) findViewById(R.id.entrada);
        aleluia = (TextView) findViewById(R.id.aleluia);
        acto_penitencial = (TextView) findViewById(R.id.acto_penitencial);
        acto_penitencial_label = (TextView) findViewById(R.id.acto_penitencial_label);
        salmo = (TextView) findViewById(R.id.salmo);
        salmo_label = (TextView) findViewById(R.id.salmo_label);
        ofertorio = (TextView) findViewById(R.id.ofertorio);
        santo = (TextView) findViewById(R.id.santo);
        pai_nosso = (TextView) findViewById(R.id.pai_nosso);
        pai_nosso_label = (TextView) findViewById(R.id.pai_nosso_label);
        paz = (TextView) findViewById(R.id.paz);
        comunhao = (TextView) findViewById(R.id.comunhao);
        accao_gracas = (TextView) findViewById(R.id.accao_gracas);
        accao_gracas_label = (TextView) findViewById(R.id.accao_gracas_label);
        final_ = (TextView) findViewById(R.id.last);

        row1 = (TableRow) findViewById(R.id.row_acto);
        row2 = (TableRow) findViewById(R.id.row_pai_nosso);
        row3 = (TableRow) findViewById(R.id.row_accao_gracas);
        row4 = (TableRow) findViewById(R.id.row_salmo);


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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_programa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_refresh:   generatePrograma();

                                        return true;

            default:	return super.onOptionsItemSelected(item);

        }
    }

    private void generatePrograma(){
        ArrayList<Musica> programa = new ArrayList<Musica>();

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
            entrada.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            entrada.setText(prog_entrada.getNome());
            entrada.setTextSize(14);
        }else entrada.setText(""+prog_entrada.getNumero());

        if(prog_aleluia == null)
            aleluia.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            aleluia.setText(prog_aleluia.getNome());
            aleluia.setTextSize(14);
        }else aleluia.setText(""+prog_aleluia.getNumero());

        if(acto_penitencial.getVisibility() == View.VISIBLE){
            Musica prog_acto = solveDuplicate(returnMusic(2), programa, 2);
            programa.add(prog_acto);

            if( prog_acto == null)
                acto_penitencial.setText("ND");
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                acto_penitencial.setText(prog_acto.getNome());
                acto_penitencial.setTextSize(14);
            }else acto_penitencial.setText(""+prog_acto.getNumero());
        }

        if(salmo.getVisibility() == View.VISIBLE){
            Musica prog_salmo = solveDuplicate(returnMusic(3), programa, 3);
            programa.add(prog_salmo);

            if( prog_salmo == null)
                salmo.setText("ND");
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                salmo.setText(prog_salmo.getNome());
                salmo.setTextSize(14);
            }else salmo.setText(""+prog_salmo.getNumero());
        }

        if(prog_ofertorio == null)
            ofertorio.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            ofertorio.setText(prog_ofertorio.getNome());
            ofertorio.setTextSize(14);
        }else ofertorio.setText(""+prog_ofertorio.getNumero());

        if(prog_santo == null)
            santo.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            santo.setText(prog_santo.getNome());
            santo.setTextSize(14);
        }else santo.setText(""+prog_santo.getNumero());

        if(pai_nosso.getVisibility() == View.VISIBLE){
            Musica prog_pai_nosso = solveDuplicate(returnMusic(7), programa, 7);
            programa.add(prog_pai_nosso);

            if(prog_pai_nosso == null)
                pai_nosso.setText("ND");
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                pai_nosso.setText(prog_pai_nosso.getNome());
                pai_nosso.setTextSize(14);
            }else pai_nosso.setText(""+prog_pai_nosso.getNumero());
        }

        if(prog_paz == null)
            paz.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            paz.setText(prog_paz.getNome());
            paz.setTextSize(14);
        }else paz.setText(""+prog_paz.getNumero());

        if(prog_comunhao == null)
            comunhao.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            comunhao.setText(prog_comunhao.getNome());
            comunhao.setTextSize(14);
        }else comunhao.setText(""+prog_comunhao.getNumero());

        if(accao_gracas.getVisibility() == View.VISIBLE){
            Musica prog_accao_gracas = solveDuplicate(returnMusic(10), programa, 10);
            programa.add(prog_accao_gracas);

            if(prog_accao_gracas == null)
                accao_gracas.setText("ND");
            else if(nome_numero.equalsIgnoreCase("Nome")) {
                accao_gracas.setText(prog_accao_gracas.getNome());
                accao_gracas.setTextSize(14);
            }else accao_gracas.setText(""+prog_accao_gracas.getNumero());
        }

        if(prog_final == null)
            final_.setText("ND");
        else if(nome_numero.equalsIgnoreCase("Nome")) {
            final_.setText(prog_final.getNome());
            final_.setTextSize(14);
        }else final_.setText(""+prog_final.getNumero());
    }

    private Musica solveDuplicate(Musica musica, ArrayList<Musica> programa, int pos) {
        Musica new_musica = null;

        if(programa.indexOf(musica) != -1 ){
            new_musica = returnMusic(pos);
        }else new_musica = musica;

        return new_musica;
    }

    private int generateRandomNumber(int minimum, int maximum){

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((maximum - minimum) + 1) + minimum;

        return randomNum;

    }

    private Musica returnMusic(int parte){

        PartesMusicaHandler musica_handler = new PartesMusicaHandler(this);

        musica_handler.open();

        Cursor c = musica_handler.getMusicasForParte(parte);
        c.moveToFirst();

        ArrayList<Musica> lista_musicas = new ArrayList<Musica>();

        while(!c.isAfterLast()){

            Musica musica = new Musica(c.getInt(0), c.getString(1));
            lista_musicas.add(musica);
            c.moveToNext();
        }

        musica_handler.close();

        Musica result = null;

        if(lista_musicas.size()>0){
            int position = generateRandomNumber(0, lista_musicas.size()-1);
            result = lista_musicas.get(position);
        }

        return result;

    }
}
