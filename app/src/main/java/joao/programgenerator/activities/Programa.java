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

        // Geração dos números random
        int prog_entrada = returnMusicNumber(1);
        int prog_aleluia = returnMusicNumber(2);
        int prog_ofertorio = returnMusicNumber(4);
        int prog_santo = returnMusicNumber(5);
        int prog_paz = returnMusicNumber(7);
        int prog_comunhao = returnMusicNumber(8);
        int prog_final = returnMusicNumber(10);

        if(prog_entrada == -1)
            entrada.setText("ND");
        else entrada.setText(""+prog_entrada);

        if(prog_aleluia == -1)
            aleluia.setText("ND");
        else aleluia.setText(""+prog_aleluia);

        if(acto_penitencial.getVisibility() == View.VISIBLE){
            if(returnMusicNumber(3) == -1)
                acto_penitencial.setText("ND");
            else acto_penitencial.setText(""+returnMusicNumber(3));
        }

        if(prog_ofertorio == -1)
            ofertorio.setText("ND");
        else ofertorio.setText(""+prog_ofertorio);

        if(prog_santo == -1)
            santo.setText("ND");
        else santo.setText(""+prog_santo);

        if(pai_nosso.getVisibility() == View.VISIBLE){
            if(returnMusicNumber(6) == -1)
                pai_nosso.setText("ND");
            else pai_nosso.setText(""+returnMusicNumber(6));
        }

        if(prog_paz == -1)
            paz.setText("ND");
        else paz.setText(""+prog_paz);

        if(prog_comunhao == -1)
            comunhao.setText("ND");
        else comunhao.setText(""+prog_comunhao);

        if(accao_gracas.getVisibility() == View.VISIBLE){
            if(returnMusicNumber(9) == -1)
                accao_gracas.setText("ND");
            else accao_gracas.setText(""+returnMusicNumber(9));
        }

        if(prog_final == -1)
            final_.setText("ND");
        else final_.setText(""+prog_final);
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
