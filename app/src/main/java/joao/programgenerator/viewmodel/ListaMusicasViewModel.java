package joao.programgenerator.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import joao.programgenerator.pojos.Musica;
import joao.programgenerator.repository.ListaMusicasRepository;

public class ListaMusicasViewModel extends ViewModel{

    private ListaMusicasRepository repo;
    private LiveData<List<String>> partes;
    private LiveData<List<Musica>> raw_musicas;
    private LiveData<List<String>> musicas;

    @Inject
    ListaMusicasViewModel(ListaMusicasRepository repository){

        repo = repository;
    }

    public void init(){

        partes = repo.getAllPartes();
        raw_musicas = repo.getAllMusicas();
        musicas = Transformations.map(raw_musicas, input -> {

            ArrayList<String> result = new ArrayList<>();

            for(Musica m: input){
                result.add(m.getMusic_number() + "-" + m.getMusic_name());
            }

            return result;
        });
    }

    public LiveData<List<String>> getAllPartes(){

        return partes;
    }

    public LiveData<List<String>> getMusicasForParte(int parte){


        raw_musicas = repo.getMusicasForParte(parte);

        musicas = Transformations.map(raw_musicas, input -> {

            ArrayList<String> result = new ArrayList<>();

            for(Musica m: input){
                result.add(m.getMusic_number() + " - " + m.getMusic_name());
            }

            return result;
        });

        return musicas;
    }

}
