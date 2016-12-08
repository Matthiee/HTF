package be.hogent.hackthefuture.domein;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.hogent.hackthefuture.R;

/**
 * Created by Matthias on 8/12/2016.
 */

public class PhotoAdapter extends ArrayAdapter<Photo> {

    private List<Photo> photos;

    public PhotoAdapter(Context context, List<Photo> photos) {
        super(context, R.layout.photo_row, photos);

        this.photos = photos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(getContext()));
        View row = inflater.inflate(R.layout.photo_row, parent, false);

        Photo p = photos.get(position);

        ImageView img = (ImageView)row.findViewById(R.id.photo_row_img);
        TextView txtNaam = (TextView)row.findViewById(R.id.txtNaam);
        TextView txtAuthor = (TextView)row.findViewById(R.id.txtAuthor);
        TextView txtDesc = (TextView)row.findViewById(R.id.txtDescription);

        txtNaam.setText(p.getName());
        txtAuthor.setText(p.getResearcher());
        txtDesc.setText(p.getRemark());

        byte[] decodedString = Base64.decode(p.getValue(), Base64.URL_SAFE);
        Bitmap decodedImg = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        img.setImageBitmap(decodedImg);

        return row;

    }
}
