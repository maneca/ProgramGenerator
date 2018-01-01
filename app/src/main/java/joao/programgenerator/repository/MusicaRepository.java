package joao.programgenerator.repository;


import java.util.concurrent.Executor;

import javax.inject.Inject;

import joao.programgenerator.database.dao.MusicaDao;
import joao.programgenerator.pojos.Musica;

public class MusicaRepository {

    private final MusicaDao musicaDao;
    private final Executor executor;

    @Inject
    MusicaRepository(MusicaDao musicaDao, Executor executor){

        this.musicaDao = musicaDao;
        this.executor = executor;
    }

    public void insertMusica(Musica m){

        executor.execute(() -> musicaDao.insertMusica(m));
    }
}
