package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import joao.programgenerator.R;
import joao.programgenerator.custom.ExpandableListAdapter;
import joao.programgenerator.database.PartesMusicaHandler;
import joao.programgenerator.dependencyInjection.Injectable;
import joao.programgenerator.dependencyInjection.ViewModelFactory;
import joao.programgenerator.viewmodel.ListaMusicasViewModel;


public class ListaMusicasActivity extends AppCompatActivity implements Injectable, AdapterView.OnItemLongClickListener, ExpandableListView.OnChildClickListener {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView listview;
    List<String> listDataHeader = new ArrayList<>();
    HashMap<String, List<String>> listDataChild = new HashMap<>();
    private PartesMusicaHandler musica_handler;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musicas);

        listview = findViewById(R.id.expandableListView);

        // preparing list data


        ListaMusicasViewModel listaMusicasViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListaMusicasViewModel.class);
        listaMusicasViewModel.init();
        listaMusicasViewModel.getAllPartes().observe(this, partesNames -> {
            this.listDataHeader = partesNames;
            listDataChild.put(listDataHeader.get(0), new ArrayList<String>()); // Header, Child data
            listDataChild.put(listDataHeader.get(1), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(2), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(3), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(4), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(5), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(6), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(7), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(8), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(9), new ArrayList<String>());
            listDataChild.put(listDataHeader.get(10), new ArrayList<String>());

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
            listview.setAdapter(listAdapter);

            //listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(1).observe(this, musicas -> {

            this.listDataChild.put(listDataHeader.get(0), musicas);
            listAdapter.notifyDataSetChanged();

            Log.d("c", listDataChild.toString());
        });

        // setting list adapter
        //listview.setAdapter(listAdapter);

        listview.setOnItemLongClickListener(this);
        listview.setOnChildClickListener(this);

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
            case R.id.action_addmusic:  Intent intent = new Intent(ListaMusicasActivity.this, NovaMusicaActivity.class);
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

            Intent intent = new Intent(ListaMusicasActivity.this, NovaMusicaActivity.class);

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
