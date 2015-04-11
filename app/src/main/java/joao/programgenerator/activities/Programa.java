package joao.programgenerator.activities;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import joao.programgenerator.R;
import joao.programgenerator.database.PartesMusicaHandler;


public class Programa extends ActionBarActivity {

    private TextView entrada, aleluia, acto_penitencial, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;
    private TextView acto_penitencial_label, pai_nosso_label, accao_gracas_label;
    private TableRow row1, row2, row3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_programa);

        entrada = (TextView) findViewById(R.id.entrada);
        aleluia = (TextView) findViewById(R.id.aleluia);
        acto_penitencial = (TextView) findViewById(R.id.acto_penitencial);
        acto_penitencial_label = (TextView) findViewById(R.id.acto_penitencial_label);
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

        row1.setVisibility(View.GONE);
        acto_penitencial.setVisibility(View.GONE);
        acto_penitencial_label.setVisibility(View.GONE);
        row2.setVisibility(View.GONE);
        pai_nosso.setVisibility(View.GONE);
        pai_nosso_label.setVisibility(View.GONE);
        row3.setVisibility(View.GONE);
        accao_gracas.setVisibility(View.GONE);
        accao_gracas_label.setVisibility(View.GONE);

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            ArrayList seleccionados = bundle.getCharSequenceArrayList("escolhidas");


            if(seleccionados.contains(0)) {
                row1.setVisibility(View.VISIBLE);
                acto_penitencial_label.setVisibility(View.VISIBLE);
                acto_penitencial.setVisibility(View.VISIBLE);
            }

            if(seleccionados.contains(1)){
                row2.setVisibility(View.VISIBLE);
                pai_nosso_label.setVisibility(View.VISIBLE);
                pai_nosso.setVisibility(View.VISIBLE);
            }

            if(seleccionados.contains(2)) {
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
        ArrayList<Integer> programa = new ArrayList<Integer>();

        // Geração dos números random
        int prog_entrada = returnMusicNumber(1);
        programa.add(prog_entrada);

        int prog_aleluia = solveDuplicate(returnMusicNumber(3), programa, 3);
        programa.add(prog_aleluia);

        int prog_ofertorio = solveDuplicate(returnMusicNumber(4), programa, 4);
        programa.add(prog_ofertorio);

        int prog_santo = solveDuplicate(returnMusicNumber(5), programa, 5);
        programa.add(prog_santo);

        int prog_paz = solveDuplicate(returnMusicNumber(7), programa, 7);
        programa.add(prog_paz);

        int prog_comunhao = solveDuplicate(returnMusicNumber(8), programa, 8);
        programa.add(prog_comunhao);

        int prog_final = solveDuplicate(returnMusicNumber(10), programa, 10);
        programa.add(prog_final);

        if(prog_entrada == -1)
            entrada.setText("ND");
        else entrada.setText(""+prog_entrada);

        if(prog_aleluia == -1)
            aleluia.setText("ND");
        else aleluia.setText(""+prog_aleluia);

        if(acto_penitencial.getVisibility() == View.VISIBLE){
            int prog_acto = solveDuplicate(returnMusicNumber(2), programa, 2);
            programa.add(prog_acto);

            if( prog_acto == -1)
                acto_penitencial.setText("ND");
            else acto_penitencial.setText(""+prog_acto);
        }

        if(prog_ofertorio == -1)
            ofertorio.setText("ND");
        else ofertorio.setText(""+prog_ofertorio);

        if(prog_santo == -1)
            santo.setText("ND");
        else santo.setText(""+prog_santo);

        if(pai_nosso.getVisibility() == View.VISIBLE){
            int prog_pai_nosso = solveDuplicate(returnMusicNumber(6), programa, 6);
            programa.add(prog_pai_nosso);

            if(prog_pai_nosso == -1)
                pai_nosso.setText("ND");
            else pai_nosso.setText(""+prog_pai_nosso);
        }

        if(prog_paz == -1)
            paz.setText("ND");
        else paz.setText(""+prog_paz);

        if(prog_comunhao == -1)
            comunhao.setText("ND");
        else comunhao.setText(""+prog_comunhao);

        if(accao_gracas.getVisibility() == View.VISIBLE){
            int prog_accao_gracas = solveDuplicate(returnMusicNumber(9), programa, 9);
            programa.add(prog_accao_gracas);

            if(prog_accao_gracas == -1)
                accao_gracas.setText("ND");
            else accao_gracas.setText(""+prog_accao_gracas);
        }

        if(prog_final == -1)
            final_.setText("ND");
        else final_.setText(""+prog_final);
    }

    private int solveDuplicate(int number, ArrayList<Integer> programa, int pos){
        int new_number = -1;

        if(programa.indexOf(number) != -1 ){
            new_number = returnMusicNumber(pos);
        }else new_number = number;

        return new_number;
    }

    private int generateRandomNumber(int minimum, int maximum){

        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((maximum - minimum) + 1) + minimum;

        return randomNum;

    }

    private int returnMusicNumber(int parte){

        PartesMusicaHandler musica_handler = new PartesMusicaHandler(this);

        musica_handler.open();

        Cursor c = musica_handler.getMusicasForParte(parte);
        c.moveToFirst();

        ArrayList<Integer> lista_musicas = new ArrayList<Integer>();

        while(!c.isAfterLast()){

            lista_musicas.add(c.getInt(0));
            c.moveToNext();
        }

        musica_handler.close();

        int result = -1;

        if(lista_musicas.size()>0){
            int position = generateRandomNumber(0, lista_musicas.size()-1);
            result = lista_musicas.get(position);
        }

        return result;

    }
}
