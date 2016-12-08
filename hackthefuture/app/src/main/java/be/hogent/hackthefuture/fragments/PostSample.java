package be.hogent.hackthefuture.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.DatabaseHandler;
import be.hogent.hackthefuture.domein.Sample;


public class PostSample extends Fragment {

    private String name;
    private String value;
    private String remark;
    private String date;

    private TextView txtName, txtRemark, txtValue;
    DatabaseHandler db;
    Calendar c;
    Connectie con;


    public PostSample() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_sample, container, false);
        Bundle args = getArguments();
        db = new DatabaseHandler(getActivity());
        c = Calendar.getInstance();
        con = new Connectie();
        getActivity().setTitle("New Sample");

        txtName = (TextView) view.findViewById(R.id.input_Name);
        txtRemark = (TextView) view.findViewById(R.id.input_Remark);
        txtValue = (TextView) view.findViewById(R.id.input_Value);

        return view;
    }


    public void addSample() {
        SimpleDateFormat iso8601Format = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        Date d = c.getTime();
        String dat = iso8601Format.format(c.getTime());

        Sample sam = new Sample();
        sam.setName(txtName.getText().toString());
        sam.setValue(txtName.getText().toString());
        sam.setRemark(txtRemark.getText().toString());
        sam.setDatetime(dat);
        sam.setResearcher(con.getName());
        db.addSample(sam);
        
    }

}
