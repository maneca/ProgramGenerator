package joao.programgenerator.viewmodel;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import joao.programgenerator.pojos.Musica;
import joao.programgenerator.repository.ListaMusicasRepository;

public class ListaMusicasViewModel extends ViewModel{

    private ListaMusicasRepository repo;
    private LiveData<List<String>> partes;

    @Inject
    ListaMusicasViewModel(ListaMusicasRepository repository){

        repo = repository;
    }

    public void init(){

        partes = repo.getAllPartes();
    }

    public LiveData<List<String>> getAllPartes(){

        return partes;
    }

    public LiveData<List<Musica>> getMusicasForParte(int parte){

        return repo.getMusicasForParte(parte);
    }

    public ArrayList<Musica> getListOfMusicas(int parte){

        return (ArrayList<Musica>) repo.getListOfMusicas(parte);
    }

    public ArrayList<Integer> getPartesfromMusica(int music_number){

        return (ArrayList<Integer>) repo.getPartesFromMusica(music_number);
    }

    public void deleteMusica(Musica m){

        repo.deleteMusica(m);
    }

}
