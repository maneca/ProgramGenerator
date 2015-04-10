package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import joao.programgenerator.R;
import joao.programgenerator.custom.ExpandableListAdapter;
import joao.programgenerator.database.PartesEucaristiaHandler;
import joao.programgenerator.database.PartesMusicaHandler;




public class ListaMusicas extends ActionBarActivity implements AdapterView.OnItemLongClickListener{

    private ExpandableListAdapter listAdapter;
    private ExpandableListView listview;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private PartesEucaristiaHandler partesHandler;
    private PartesMusicaHandler musica_handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musicas);

        listview = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        listview.setAdapter(listAdapter);

        listview.setOnItemLongClickListener(this);


    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        partesHandler = new PartesEucaristiaHandler(this);

        partesHandler.open();

        Cursor c = partesHandler.returnPartesEucaristia();

        c.moveToFirst();

        while(!c.isAfterLast()){
            listDataHeader.add(c.getString(1));

            c.moveToNext();
        }

        partesHandler.close();

        musica_handler = new PartesMusicaHandler(this);

        musica_handler.open();

        // Adding child data

        // ENTRADA
        List<String> entrada= new ArrayList<String>();

        Cursor cursor = musica_handler.getMusicasForParte(1);
        if(cursor.getCount() == 0)
            entrada.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                entrada.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // ALELUIA
        List<String> aleluia= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(2);
        if(cursor.getCount() == 0)
            aleluia.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                aleluia.add("" + cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // ACTO PENITENCIAL
        List<String> acto_penitencial= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(3);
        if(cursor.getCount() == 0)
            acto_penitencial.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                acto_penitencial.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // OFERTORIO
        List<String> ofertorio= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(4);
        if(cursor.getCount() == 0)
            ofertorio.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                ofertorio.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // SANTO
        List<String> santo = new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(5);
        if(cursor.getCount() == 0)
            santo.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                santo.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // PAI NOSSO
        List<String> pai_nosso= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(6);
        if(cursor.getCount() == 0)
            pai_nosso.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                pai_nosso.add("" + cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // PAZ
        List<String> paz= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(7);
        if(cursor.getCount() == 0)
            paz.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                paz.add("" + cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // COMUNHÃO
        List<String> comunhao= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(8);
        if(cursor.getCount() == 0)
            comunhao.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                comunhao.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // ACÇÃO DE GRAÇAS
        List<String> accao_gracas= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(9);
        if(cursor.getCount() == 0)
            accao_gracas.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                accao_gracas.add(""+cursor.getInt(0));
                cursor.moveToNext();
            }
        }

        // FINAL
        List<String> final_= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(10);
        if(cursor.getCount() == 0)
            final_.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                final_.add("" + cursor.getInt(0));
                cursor.moveToNext();
            }
        }


        musica_handler.close();

        listDataChild.put(listDataHeader.get(0), entrada); // Header, Child data
        listDataChild.put(listDataHeader.get(1), aleluia);
        listDataChild.put(listDataHeader.get(2), acto_penitencial);
        listDataChild.put(listDataHeader.get(3), ofertorio);
        listDataChild.put(listDataHeader.get(4), santo);
        listDataChild.put(listDataHeader.get(5), pai_nosso);
        listDataChild.put(listDataHeader.get(6), paz);
        listDataChild.put(listDataHeader.get(7), comunhao);
        listDataChild.put(listDataHeader.get(8), accao_gracas);
        listDataChild.put(listDataHeader.get(9), final_);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_musicas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_addmusic:  for(int i=0; i<10; i++){
                                            listview.collapseGroup(i);
                                        }

                                        return customAlertDialog();

            case R.id.action_help:      return helpDialog();

            default:	return super.onOptionsItemSelected(item);

        }
    }

    private boolean helpDialog(){

        AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setTitle(getString(R.string.help_title));
        dialog.setMessage(getString(R.string.help_message));
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // TODO Auto-generated method stub

            }
        });

        dialog.show();

        return true;


    }

    private boolean customAlertDialog(){


        //DIALOGO

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_alert_dialog);
        //dialog.setView(inflater.inflate(R.layout.custom_alert_dialog, null));

        final EditText numero;
        final CheckedTextView entrada , aleluia, acto_penitencial, ofertorio, santo, pai_nosso, paz, comunhao, accao_gracas, final_;

        dialog.setTitle(R.string.nova_musica);

        dialog.show();

        // NUMERO DA MUSICA
        numero = (EditText) dialog.findViewById(R.id.numero_musica);

        // CHECKBOXES
        entrada = (CheckedTextView) dialog.findViewById(R.id.entrada);
        aleluia = (CheckedTextView) dialog.findViewById(R.id.aleluia);
        acto_penitencial = (CheckedTextView) dialog.findViewById(R.id.acto_penitencial);
        ofertorio = (CheckedTextView) dialog.findViewById(R.id.ofertorio);
        santo = (CheckedTextView) dialog.findViewById(R.id.santo);
        pai_nosso = (CheckedTextView) dialog.findViewById(R.id.pai_nosso);
        paz = (CheckedTextView) dialog.findViewById(R.id.paz);
        comunhao = (CheckedTextView) dialog.findViewById(R.id.comunhao);
        accao_gracas = (CheckedTextView) dialog.findViewById(R.id.accao_gracas);
        final_ = (CheckedTextView) dialog.findViewById(R.id.final_);

        Button ok = (Button) dialog.findViewById(R.id.ok);
        Button cancel = (Button) dialog.findViewById(R.id.cancel);

        entrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                entrada.toggle();
            }
        });

        aleluia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aleluia.toggle();
            }
        });

        acto_penitencial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acto_penitencial.toggle();
            }
        });

        ofertorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ofertorio.toggle();
            }
        });

        santo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                santo.toggle();
            }
        });

        pai_nosso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pai_nosso.toggle();
            }
        });

        paz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paz.toggle();
            }
        });

        comunhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comunhao.toggle();
            }
        });

        accao_gracas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accao_gracas.toggle();
            }
        });

        final_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final_.toggle();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                musica_handler = new PartesMusicaHandler(getApplicationContext());

                musica_handler.open();

                String musica = numero.getText().toString();

                if(musica.equals("") ||
                        (!entrada.isChecked() && !aleluia.isChecked() && !acto_penitencial.isChecked() &&
                         !ofertorio.isChecked() && !santo.isChecked() && !pai_nosso.isChecked() &&
                         !paz.isChecked() && !comunhao.isChecked() && !accao_gracas.isChecked() && !final_.isChecked()))
                    Toast.makeText(getApplicationContext(), getString(R.string.campos_obrigatorios), Toast.LENGTH_LONG).show();
                else{
                    int number = Integer.parseInt(musica);
                    if(entrada.isChecked()) {
                        musica_handler.insertParteMusica(1, number);
                        listDataChild.get(listDataHeader.get(0)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(0)).add(musica);
                    }

                    if(aleluia.isChecked()) {
                        musica_handler.insertParteMusica(2, number);
                        listDataChild.get(listDataHeader.get(1)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(1)).add(musica);
                    }

                    if(acto_penitencial.isChecked()) {
                        musica_handler.insertParteMusica(3, number);
                        listDataChild.get(listDataHeader.get(2)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(2)).add(musica);
                    }

                    if(ofertorio.isChecked()) {
                        musica_handler.insertParteMusica(4, number);
                        listDataChild.get(listDataHeader.get(3)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(3)).add(musica);
                    }

                    if(santo.isChecked()) {
                        musica_handler.insertParteMusica(5, number);
                        listDataChild.get(listDataHeader.get(4)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(4)).add(musica);
                    }

                    if(pai_nosso.isChecked()) {
                        musica_handler.insertParteMusica(6, number);
                        listDataChild.get(listDataHeader.get(5)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(5)).add(musica);
                    }

                    if(paz.isChecked()) {
                        musica_handler.insertParteMusica(7, number);
                        listDataChild.get(listDataHeader.get(6)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(6)).add(musica);
                    }

                    if(comunhao.isChecked()) {
                        musica_handler.insertParteMusica(8, number);
                        listDataChild.get(listDataHeader.get(7)).remove(getString(R.string.musica_definida));
                        listDataChild.get(listDataHeader.get(7)).add(musica);
                    }

                    if(accao_gracas.isChecked()) {
                        musica_handler.insertParteMusica(9, number);
                        listDataChild.get(listDataHeader.get(8)).remove("Não tem nenhuma música definida para esta parte");
                        listDataChild.get(listDataHeader.get(8)).add(musica);
                    }

                    if(final_.isChecked()) {
                        musica_handler.insertParteMusica(10, number);
                        listDataChild.get(listDataHeader.get(9)).remove("Não tem nenhuma música definida para esta parte");
                        listDataChild.get(listDataHeader.get(9)).add(musica);
                    }
                }

                musica_handler.close();

                dialog.dismiss();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        return true;
    }

    public void removeOnClickHandler(View v){

        final View view = v;


    }


    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ExpandableListView listView = (ExpandableListView) parent;
        long pos = listView.getExpandableListPosition(position);

        // get type and correct positions
        int itemType = ExpandableListView.getPackedPositionType(pos);
        final int groupPos = ExpandableListView.getPackedPositionGroup(pos);
        final int childPos = ExpandableListView.getPackedPositionChild(pos);

        // if child is long-clicked
        if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle(getString(R.string.apagar_musica_titulo));
            dialog.setMessage(getString(R.string.apagar_musica_texto));
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sim), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    int musica = Integer.parseInt((String) listAdapter.getChild(groupPos, childPos));

                    PartesMusicaHandler musicaHandler = new PartesMusicaHandler(getApplicationContext());

                    musicaHandler.open();

                    boolean cenas = musicaHandler.removeParteMusica(groupPos+1, musica);
                    listview.collapseGroup(groupPos);

                    listDataChild.get(listDataHeader.get(groupPos)).remove(listAdapter.getChild(groupPos, childPos));

                    musicaHandler.close();

                }
            });

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.nao), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub

                }
            });

            dialog.show();


        }
        return false;
    }
}
