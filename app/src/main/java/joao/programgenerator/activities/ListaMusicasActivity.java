package joao.programgenerator.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import joao.programgenerator.dependencyInjection.Injectable;
import joao.programgenerator.dependencyInjection.ViewModelFactory;
import joao.programgenerator.pojos.Musica;
import joao.programgenerator.viewmodel.ListaMusicasViewModel;


public class ListaMusicasActivity extends AppCompatActivity implements Injectable, AdapterView.OnItemLongClickListener, ExpandableListView.OnChildClickListener {

    private ExpandableListAdapter listAdapter;
    private ExpandableListView listview;
    private List<String> listDataHeader = new ArrayList<>();
    private HashMap<String, List<Musica>> listDataChild = new HashMap<>();
    private ListaMusicasViewModel listaMusicasViewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musicas);

        listview = findViewById(R.id.expandableListView);

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        listview.setAdapter(listAdapter);

        // preparing list data
        listaMusicasViewModel = ViewModelProviders.of(this, viewModelFactory).get(ListaMusicasViewModel.class);
        listaMusicasViewModel.init();
        listaMusicasViewModel.getAllPartes().observe(this, partesNames -> {
            this.listDataHeader = partesNames;

            listAdapter.setExpandableListTitle(this.listDataHeader);
        });

        listaMusicasViewModel.getMusicasForParte(1).observe(this, musicas -> {
            listDataChild.put("Entrada", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(2).observe(this, musicas -> {
            listDataChild.put("Acto Penitencial", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(3).observe(this, musicas -> {
            listDataChild.put("Salmo", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(4).observe(this, musicas -> {
            listDataChild.put("Aleluia", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(5).observe(this, musicas -> {
            listDataChild.put("Ofertório", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(6).observe(this, musicas -> {
            listDataChild.put("Santo", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(7).observe(this, musicas -> {
            listDataChild.put("Pai-nosso", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(8).observe(this, musicas -> {
            listDataChild.put("Paz", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(9).observe(this, musicas -> {
            listDataChild.put("Comunhão", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(10).observe(this, musicas -> {
            listDataChild.put("Acção de Graças", musicas);
            listAdapter.notifyDataSetChanged();
        });

        listaMusicasViewModel.getMusicasForParte(11).observe(this, musicas -> {
            listDataChild.put("Final", musicas);
            listAdapter.notifyDataSetChanged();
        });


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
        switch (item.getItemId()) {
            case R.id.action_addmusic:
                Intent intent = new Intent(ListaMusicasActivity.this, NovaMusicaActivity.class);
                intent.putExtra("music_number", -1);

                startActivity(intent);
                finish();

                return true;

            case R.id.action_help:

                AlertDialog dialog = new AlertDialog.Builder(this).create();
                dialog.setTitle(getString(R.string.help_title));
                dialog.setMessage(getString(R.string.help_message));
                dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.ok), (dialog1, which) -> {

                });

                dialog.show();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        ExpandableListView listView = (ExpandableListView) parent;
        long pos = listView.getExpandableListPosition(position);

        // get type and correct positions
        int itemType = ExpandableListView.getPackedPositionType(pos);
        final int groupPos = ExpandableListView.getPackedPositionGroup(pos);
        final int childPos = ExpandableListView.getPackedPositionChild(pos);

        Musica musica = (Musica) listAdapter.getChild(groupPos, childPos);

        // if child is long-clicked
        if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD ) {
            AlertDialog dialog = new AlertDialog.Builder(this).create();
            dialog.setTitle(getString(R.string.apagar_musica_titulo));
            dialog.setMessage(getString(R.string.apagar_musica_texto));
            dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.sim), (dialog12, which) -> {

                listaMusicasViewModel.deleteMusica(musica);
                listview.collapseGroup(groupPos);

                listDataChild.get(listDataHeader.get(groupPos)).remove(listAdapter.getChild(groupPos, childPos));

            });

            dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.nao), (dialog1, which) -> {

            });

            dialog.show();
        }
        return false;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPos, int childPos, long id) {


        Musica music = (Musica) listAdapter.getChild(groupPos, childPos);

        Intent intent = new Intent(ListaMusicasActivity.this, NovaMusicaActivity.class);
        intent.putExtra("music_number", music.getMusic_number());
        intent.putExtra("music_name", music.getMusic_name());

        intent.putExtra("music_parts", listaMusicasViewModel.getPartesfromMusica(music.getMusic_number()));

        startActivity(intent);

        return false;
    }
}
