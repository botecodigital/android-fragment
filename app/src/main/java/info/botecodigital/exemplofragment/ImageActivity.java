package info.botecodigital.exemplofragment;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.File;

public class ImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        ImagemFragment fragment = new ImagemFragment();
        if( getIntent().hasExtra("image") ){
            fragment.setImage((File) getIntent().getSerializableExtra("image"));
        }

        getFragmentManager().beginTransaction()
                .add( R.id.fragment_imagem , fragment )
                .commit();
    }
}
