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


public class GetSample extends Fragment {

    DatabaseHandler db;
    Connectie con;


    public GetSample() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_post_sample, container, false);
        Bundle args = getArguments();
        db = new DatabaseHandler(getActivity());
        con = new Connectie();
        getActivity().setTitle("Samples");


        return view;
    }


    public void addPage() {


    }

}
