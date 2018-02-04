package joao.programgenerator;

import android.content.Intent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowApplication;

import joao.programgenerator.activities.MainActivity;
import joao.programgenerator.activities.ProgramaActivity;

import static junit.framework.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class GenerateProgramUnitTest {

    @Test
    public void generateProgram(){

        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);
        mainActivity.findViewById(R.id.gerar).performClick();

        Intent expectedIntent = new Intent(mainActivity, ProgramaActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());


    }
}
