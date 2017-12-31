package joao.programgenerator.dependencyInjection;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import joao.programgenerator.viewmodel.ListaMusicasViewModel;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListaMusicasViewModel.class)
    abstract ViewModel bindListaMusicasViewModel(ListaMusicasViewModel listaMusicasViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);

}
