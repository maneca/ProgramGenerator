package joao.programgenerator.dependencyInjection;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import joao.programgenerator.ProgramGeneratorApp;

@Singleton
@Component(modules = { DatabaseModule.class,
        AndroidInjectionModule.class,
        ActivityModule.class,
        ViewModelModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(ProgramGeneratorApp app);

}
