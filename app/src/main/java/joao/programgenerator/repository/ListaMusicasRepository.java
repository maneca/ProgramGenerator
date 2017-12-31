package joao.programgenerator.repository;


import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import joao.programgenerator.database.dao.MusicaDao;
import joao.programgenerator.database.dao.PartesEucaristiaDao;
import joao.programgenerator.pojos.Musica;

public class ListaMusicasRepository {

    private final PartesEucaristiaDao partesEucaristiaDao;
    private final MusicaDao musicaDao;

    @Inject
    ListaMusicasRepository(PartesEucaristiaDao partesEucaristiaDao, MusicaDao musicaDao){

        this.partesEucaristiaDao = partesEucaristiaDao;
        this.musicaDao = musicaDao;
    }

    public LiveData<List<String>> getAllPartes(){

        return partesEucaristiaDao.getAllNomePartes();
    }

    public LiveData<List<Musica>> getMusicasForParte(int parte){

        return musicaDao.getMusicasFromParte(parte);
    }

    public LiveData<List<Musica>> getAllMusicas(){

        return musicaDao.getAllMusicas();
    }


}
