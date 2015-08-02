package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
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




public class ListaMusicas extends ActionBarActivity implements AdapterView.OnItemLongClickListener, ExpandableListView.OnChildClickListener {

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
        listview.setOnChildClickListener(this);


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

                entrada.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // ACTO PENITENCIAL
        List<String> acto_penitencial= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(2);
        if(cursor.getCount() == 0)
            acto_penitencial.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                acto_penitencial.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // ALELUIA
        List<String> salmo = new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(3);
        if(cursor.getCount() == 0)
            salmo.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                salmo.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // ALELUIA
        List<String> aleluia = new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(4);
        if(cursor.getCount() == 0)
            aleluia.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                aleluia.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // OFERTORIO
        List<String> ofertorio= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(5);
        if(cursor.getCount() == 0)
            ofertorio.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                ofertorio.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // SANTO
        List<String> santo = new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(6);
        if(cursor.getCount() == 0)
            santo.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                santo.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // PAI NOSSO
        List<String> pai_nosso= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(7);
        if(cursor.getCount() == 0)
            pai_nosso.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                pai_nosso.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // PAZ
        List<String> paz= new ArrayList<>();

        cursor = musica_handler.getMusicasForParte(8);
        if(cursor.getCount() == 0)
            paz.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                paz.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // COMUNHÃO
        List<String> comunhao= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(9);
        if(cursor.getCount() == 0)
            comunhao.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                comunhao.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // ACÇÃO DE GRAÇAS
        List<String> accao_gracas= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(10);
        if(cursor.getCount() == 0)
            accao_gracas.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                accao_gracas.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }

        // FINAL
        List<String> final_= new ArrayList<String>();

        cursor = musica_handler.getMusicasForParte(11);
        if(cursor.getCount() == 0)
            final_.add(getString(R.string.musica_definida));
        else{
            cursor.moveToFirst();

            while(!cursor.isAfterLast()){

                final_.add(cursor.getInt(0)+"-"+cursor.getString(1));
                cursor.moveToNext();
            }
        }


        musica_handler.close();

        listDataChild.put(listDataHeader.get(0), entrada); // Header, Child data
        listDataChild.put(listDataHeader.get(1), acto_penitencial);
        listDataChild.put(listDataHeader.get(2), salmo);
        listDataChild.put(listDataHeader.get(3), aleluia);
        listDataChild.put(listDataHeader.get(4), ofertorio);
        listDataChild.put(listDataHeader.get(5), santo);
        listDataChild.put(listDataHeader.get(6), pai_nosso);
        listDataChild.put(listDataHeader.get(7), paz);
        listDataChild.put(listDataHeader.get(8), comunhao);
        listDataChild.put(listDataHeader.get(9), accao_gracas);
        listDataChild.put(listDataHeader.get(10), final_);
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
            case R.id.action_addmusic:  Intent intent = new Intent(ListaMusicas.this, NovaMusicaActivity.class);
                                        intent.putExtra("music_number", -1);

                                        startActivity(intent);
                                        finish();

                                        return true;

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

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ExpandableListView listView = (ExpandableListView) parent;
        long pos = listView.getExpandableListPosition(position);

        // get type and correct positions
        int itemType = ExpandableListView.getPackedPositionType(pos);
        final int groupPos = ExpandableListView.getPackedPositionGroup(pos);
        final int childPos = ExpandableListView.getPackedPositionChild(pos);

        String music_name = (String) listAdapter.getChild(groupPos, childPos);

        // if child is long-clicked
        if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD &&
                !music_name.equalsIgnoreCase(getString(R.string.musica_definida))) {
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle(getString(R.string.apagar_musica_titulo));
            dialog.setMessage(getString(R.string.apagar_musica_texto));
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sim), new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // TODO Auto-generated method stub
                    String[] musica_parte = ((String) listAdapter.getChild(groupPos, childPos)).split("-");

                    int musica = Integer.parseInt(musica_parte[0]);

                    PartesMusicaHandler musicaHandler = new PartesMusicaHandler(getApplicationContext());

                    musicaHandler.open();

                    musicaHandler.removeParteMusica(groupPos+1, musica);
                    listview.collapseGroup(groupPos);

                    listDataChild.get(listDataHeader.get(groupPos)).remove(listAdapter.getChild(groupPos, childPos));

                    musicaHandler.close();

                    if(listDataChild.get(listDataHeader.get(groupPos)).size()==0)
                        listDataChild.get(listDataHeader.get(groupPos)).add(getString(R.string.musica_definida));

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

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPos, int childPos, long id) {


            String music = (String) listAdapter.getChild(groupPos, childPos);

            String[] music_number_name = music.split("-");


            int number = Integer.parseInt(music_number_name[0]);

            Intent intent = new Intent(ListaMusicas.this, NovaMusicaActivity.class);

            PartesMusicaHandler partesMusicaHandler = new PartesMusicaHandler(this);
            partesMusicaHandler.open();

            Cursor c = partesMusicaHandler.getPartesForMusica(number);
            c.moveToFirst();

            ArrayList<Integer> partes = new ArrayList<Integer>();

            while(!c.isAfterLast()){

                partes.add(c.getInt(0));
                c.moveToNext();
            }

            partesMusicaHandler.close();

            intent.putExtra("music_number", number);
            if(music_number_name[1].equalsIgnoreCase("null"))
                intent.putExtra("music_name", "");
            else intent.putExtra("music_name", music_number_name[1]);
            intent.putExtra("music_parts", partes);

        startActivity(intent);
            finish();


        return false;
    }
}
