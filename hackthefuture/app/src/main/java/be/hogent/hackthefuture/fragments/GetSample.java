package be.hogent.hackthefuture.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


public class GetSample extends Fragment implements Callback<List<Sample>> {

    DatabaseHandler db;
    Connectie con;
    SampleAdapter sampleAdapter;
    List<Sample> samples;

    public GetSample() {

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

        samples = new ArrayList<Sample>();
        sampleAdapter = new SampleAdapter(getActivity(), samples);

        Service service = Connectie.createService(Service.class, Connectie.token);
        service.getSamples().enqueue(this);

        return v;
    }

    @Override
    public void onResponse(Call<List<Sample>> call, Response<List<Sample>> response) {
        Log.i("Samples", "code: " + response.code());

        switch (response.code()) {
            case Connectie.OK:

                List<Sample> sam = response.body();
                samples.addAll(sam);
                sampleAdapter.notifyDataSetChanged();

                break;
            case Connectie.OFFLINE:
                Toast.makeText(this.getActivity(), "Sorry we are offline", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<List<Sample>> call, Throwable t) {
        Toast.makeText(this.getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }

    public void add() {
        getFragmentManager().beginTransaction().replace(R.id.main_content, new PostSample()).commit();
    }

}



