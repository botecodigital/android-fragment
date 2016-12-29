package info.botecodigital.exemplofragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.File;

/**
 * Created by rodrigo on 28/12/16.
 */
public class ListaArquivosFragment extends Fragment {

    private View rootView;

    private File dir;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_lista, null);


        if( this.dir == null){
            this.dir = Environment.getExternalStorageDirectory();
        }

        ListView listView = (ListView) rootView.findViewById( R.id.listView);

        final FileAdapter adapter = new FileAdapter( getActivity() , this.dir , new String[]{ ".png",".jpg",".gif" });

        listView.setAdapter( adapter );

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                File item = (File) adapter.getItem(i);

                if ( item.isDirectory() ) {
                    ListaArquivosFragment fragment = new ListaArquivosFragment();
                    fragment.setDir(item);

                    getFragmentManager().beginTransaction()
                            .replace(R.id.fragment_lista, fragment)
                            .addToBackStack(null)
                            .commit();
                }

                if( getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    if (item.isFile() && (item.getAbsolutePath().toLowerCase().endsWith(".png") || item.getAbsolutePath().toLowerCase().endsWith(".jpg"))) {
                        Intent intent = new Intent(getActivity(), ImageActivity.class);
                        intent.putExtra("image", item);
                        startActivity(intent);
                    }
                }else{

                    ImagemFragment fragment = new ImagemFragment();
                    fragment.setImage( item );

                    getFragmentManager().beginTransaction()
                            .replace( R.id.fragment_imagem , fragment )
                            .commit();
                }
            }
        });

        return rootView;
    }


    public void setDir( File dir ){
        this.dir = dir;
    }
}
