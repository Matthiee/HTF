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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.hogent.hackthefuture.R;

/**
 * Created by Yannick on 8/12/2016.
 */

public class SampleAdapter extends ArrayAdapter<Sample> {

    private List<Sample> samples;

    public SampleAdapter(Context context, List<Sample> samples) {
        super(context, R.layout.fragment_get_sample__row, samples);

        this.samples = samples;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater.from(getContext()));
        View row = inflater.inflate(R.layout.fragment_get_sample__row, parent, false);

        Sample sam = samples.get(position);

        TextView txtNaam = (TextView)row.findViewById(R.id.getSample_Name);
        TextView txtValue = (TextView)row.findViewById(R.id.getSample_Value);
        TextView txtRemark = (TextView)row.findViewById(R.id.getSample_Remark);
        TextView txtDate = (TextView)row.findViewById(R.id.getSample_Date);
        TextView txtResearcher = (TextView)row.findViewById(R.id.getSample_Researcher);

        txtNaam.setText(sam.getName());
        txtValue.setText(sam.getValue());
        txtRemark.setText(sam.getRemark());
        txtDate.setText(sam.getDatetime());
        txtResearcher.setText(sam.getResearcher());

        return row;

    }
}
