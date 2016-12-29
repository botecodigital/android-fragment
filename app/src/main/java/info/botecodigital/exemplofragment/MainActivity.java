package info.botecodigital.exemplofragment;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListaArquivosFragment fragment = new ListaArquivosFragment();

        getFragmentManager().beginTransaction()
                .replace(R.id.fragment_lista, fragment)
                .commit();

        if ( getResources().getConfiguration().orientation != Configuration.ORIENTATION_PORTRAIT) {

            ImagemFragment fragment2 = new ImagemFragment();
            getFragmentManager().beginTransaction()
                    .replace(R.id.fragment_imagem, fragment2)
                    .commit();
        }
    }
}
