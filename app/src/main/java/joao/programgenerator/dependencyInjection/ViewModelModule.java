package joao.programgenerator.dependencyInjection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import joao.programgenerator.viewmodel.ListaMusicasViewModel;
import joao.programgenerator.viewmodel.MusicaViewModel;
import joao.programgenerator.viewmodel.ProgramaViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListaMusicasViewModel.class)
    abstract ViewModel bindListaMusicasViewModel(ListaMusicasViewModel listaMusicasViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MusicaViewModel.class)
    abstract ViewModel bindMusicaViewModel(MusicaViewModel musicaViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProgramaViewModel.class)
    abstract ViewModel bindProgramaViewModel(ProgramaViewModel programaViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
