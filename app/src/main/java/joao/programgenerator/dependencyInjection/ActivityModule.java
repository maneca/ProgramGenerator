package joao.programgenerator.dependencyInjection;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import joao.programgenerator.activities.ListaMusicasActivity;
import joao.programgenerator.activities.MainActivity;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract ListaMusicasActivity contributeListaMusicasActivity();

}
