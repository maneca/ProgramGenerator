package joao.programgenerator.repository;


import android.arch.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import joao.programgenerator.database.dao.MusicaDao;
import joao.programgenerator.database.dao.PartesEucaristiaDao;
import joao.programgenerator.pojos.Musica;

public class ListaMusicasRepository {

    private final PartesEucaristiaDao partesEucaristiaDao;
    private final MusicaDao musicaDao;
    private final Executor executor;

    @Inject
    ListaMusicasRepository(PartesEucaristiaDao partesEucaristiaDao, MusicaDao musicaDao, Executor executor){

        this.partesEucaristiaDao = partesEucaristiaDao;
        this.musicaDao = musicaDao;
        this.executor = executor;
    }

    public LiveData<List<String>> getAllPartes(){

        return partesEucaristiaDao.getAllNomePartes();
    }

    public LiveData<List<Musica>> getMusicasForParte(int parte){

        return musicaDao.getMusicasFromParte(parte);
    }

    public List<Musica> getListOfMusicas(int parte){
        return musicaDao.getListOfMusicas(parte);
    }

    public List<Integer> getPartesFromMusica(int number){

        return musicaDao.getPartesFromMusica(number);
    }

    public void deleteMusica(Musica m){

        executor.execute(() -> musicaDao.deleteMusica(m));
    }


}
