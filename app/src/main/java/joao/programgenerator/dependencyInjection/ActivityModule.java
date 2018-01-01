package joao.programgenerator.dependencyInjection;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import joao.programgenerator.activities.ListaMusicasActivity;
import joao.programgenerator.activities.MainActivity;
import joao.programgenerator.activities.NovaMusicaActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract ListaMusicasActivity contributeListaMusicasActivity();

    @ContributesAndroidInjector
    abstract NovaMusicaActivity contributeNovaMusicaActivity();


}
