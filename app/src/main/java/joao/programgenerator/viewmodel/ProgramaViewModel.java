package joao.programgenerator.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.inject.Inject;

public class ProgramaViewModel extends ViewModel{

    @Inject
    ProgramaViewModel(){

    }

    /* Checks if external storage is available for read and write */
    private boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();

        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private String getStorageDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "programgenerator");

        return file.getAbsolutePath();
    }

    public boolean exportProgram(String filename, String entrada, String aleluia, String gloria, String acto_penitencial, String salmo, String ofertorio, String santo,
                                 String pai_nosso, String paz, String comunhao, String accao_gracas, String final_){

        boolean endOK=false;

        Calendar calendar = Calendar.getInstance();
        String output = "**************************************\n"+
                        "**       PROGRAMA - "+calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH) + 1)+"/"+calendar.get(Calendar.YEAR)+"       **\n"+
                        "**************************************\n";

        output = output.concat(entrada);
        if(!acto_penitencial.isEmpty())
            output = output.concat(acto_penitencial);
        if(!gloria.isEmpty())
            output = output.concat(gloria);
        if(!salmo.isEmpty())
            output = output.concat(salmo);

        output = output.concat(aleluia);
        output = output.concat(ofertorio);
        output = output.concat(santo);
        if(!pai_nosso.isEmpty())
            output = output.concat(pai_nosso);

        output = output.concat(paz);
        output = output.concat(comunhao);

        if(!accao_gracas.isEmpty())
            output = output.concat(accao_gracas);

        output = output.concat(final_);

        FileOutputStream outputStream;

        try {

            if(isExternalStorageWritable()){

                String fullPath = getStorageDir();

                File file = new File(fullPath, filename+".txt");
                if(file.exists())
                    file.delete();
                file.createNewFile();
                outputStream = new FileOutputStream(file);
                outputStream.write(output.getBytes());
                outputStream.flush();
                outputStream.close();

                endOK = true;
            }
        } catch (Exception e) {
            endOK = false;
            e.printStackTrace();
        }

        return endOK;
    }
}
