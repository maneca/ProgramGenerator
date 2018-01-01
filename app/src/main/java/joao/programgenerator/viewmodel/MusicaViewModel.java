package joao.programgenerator.viewmodel;

import android.arch.lifecycle.ViewModel;

import javax.inject.Inject;

import joao.programgenerator.pojos.Musica;
import joao.programgenerator.repository.MusicaRepository;

public class MusicaViewModel extends ViewModel{

    private MusicaRepository repository;

    @Inject
    public MusicaViewModel(MusicaRepository repository){

        this.repository = repository;
    }

    public void insertMusica(int parte_musica, int number, String name){

        Musica m = new Musica();

        m.setMusic_name(name);
        m.setMusic_number(number);
        m.setParte_eucaristia_id(parte_musica);

        repository.insertMusica(m);
    }
}
