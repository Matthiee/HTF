package be.hogent.hackthefuture.fragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.DatabaseHandler;
import be.hogent.hackthefuture.databank.Service;
import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.PhotoAdapter;
import be.hogent.hackthefuture.domein.Sample;
import be.hogent.hackthefuture.domein.SampleAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetAllSamples extends Fragment {

    DatabaseHandler db;
    Connectie con;
    SampleAdapter sampleAdapter;
    List<Sample> samples;


    public GetAllSamples() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_get_sample, container, false);

        samples = db.getSamplesAll();
        sampleAdapter = new SampleAdapter(getActivity(), samples);



        return v;
    }

}



