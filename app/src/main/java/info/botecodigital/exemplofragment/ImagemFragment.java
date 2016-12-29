package info.botecodigital.exemplofragment;

import android.app.Fragment;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by rodrigo on 28/12/16.
 */
public class ImagemFragment extends Fragment{

    private View rootView;

    private File imagem;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_imagem, null);

        ImageView imageView = (ImageView) rootView.findViewById( R.id.imagemView );

        if( this.imagem != null ){
            imageView.setImageBitmap(BitmapFactory.decodeFile( this.imagem.getAbsolutePath() ) );
        }

        return rootView;
    }


    public void setImage( File file ){
        this.imagem = file;
    }
}
