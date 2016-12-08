package be.hogent.hackthefuture.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.DatabaseHandler;
import be.hogent.hackthefuture.databank.Service;
import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.PhotoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosFragment extends Fragment implements Callback<List<Photo>> {

    PhotoAdapter photoAdapter;
    List<Photo> photos;
    DatabaseHandler db;
    Connectie con;


    public PhotosFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_photos, container, false);
        db = new DatabaseHandler(getActivity());
        con = new Connectie();
        photos = new ArrayList<Photo>();
        photoAdapter = new PhotoAdapter(getActivity(), photos);

        Service service = Connectie.createService(Service.class, Connectie.token);
        service.getPhotos().enqueue(this);

        return v;
    }

    @Override
    public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
        Log.i("Photos", "code: " + response.code());

        switch (response.code()){
            case Connectie.OK:

                List<Photo> ps = response.body();
                photos.addAll(ps);
                photoAdapter.notifyDataSetChanged();

                break;
            case Connectie.OFFLINE:
                Toast.makeText(this.getActivity(), "Sorry we are offline", Toast.LENGTH_LONG).show();
                List<Photo> pOff = db.getPhotos(con.getName());
                photos.addAll(pOff);
                photoAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onFailure(Call<List<Photo>> call, Throwable t) {
        Toast.makeText(this.getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }
}
