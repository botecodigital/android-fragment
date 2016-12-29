package info.botecodigital.exemplofragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.media.ThumbnailUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by rodrigo on 25/12/16.
 */
public class FileAdapter extends BaseAdapter {

    private final Context context;
    private final File directory;
    private final String[] extencoes;
    private List<File> arquivos;

    public FileAdapter(Context context , File directory , final String[] extencoes){
        this.context = context;
        this.directory = directory;
        this.extencoes = extencoes;

        this.arquivos = new ArrayList<File>();

        FileFilter ff = new FileFilter() {
            @Override
            public boolean accept(File file) {
                if( file.isDirectory()  ){
                    return true;
                }
                for( String ext : extencoes ){
                    if( file.getName().toLowerCase().endsWith( ext ) ){
                        return  true;
                    }
                }
                return false;
            }
        };
        for( File f : this.directory.listFiles( ff )){
            this.arquivos.add( f );
        }
        Log.i("===================>",this.arquivos.toString());
    }

    @Override
    public int getCount() {
        return this.arquivos.size();
    }

    @Override
    public Object getItem(int i) {
        return this.arquivos.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final File item = this.arquivos.get(i);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate( R.layout.item_file, viewGroup ,false);

        ImageView image = (ImageView) viewLayout.findViewById(R.id.item_file_image);
        TextView tvNome = (TextView) viewLayout.findViewById( R.id.item_file_nome);

        tvNome.setText( item.getName() );

        boolean flag = true;
        if( item.isDirectory() ){
            image.setImageResource( R.drawable.folder );
            flag = false;
        }

        if( item.getAbsolutePath().toLowerCase().endsWith(".png")  || item.getAbsolutePath().toLowerCase().endsWith(".jpg") || item.getAbsolutePath().toLowerCase().endsWith(".gif")  ){
            Bitmap thumbImage = ThumbnailUtils.extractThumbnail( BitmapFactory.decodeFile(item.getAbsolutePath() ), 70, 70);
            image.setImageBitmap( thumbImage );
            flag = false;
        }

        if( flag ){
            image.setImageResource( R.drawable.file );
        }


        return viewLayout;
    }
}
