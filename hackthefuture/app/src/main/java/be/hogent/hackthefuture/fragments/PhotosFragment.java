package be.hogent.hackthefuture.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import be.hogent.hackthefuture.R;
import be.hogent.hackthefuture.databank.Connectie;
import be.hogent.hackthefuture.databank.DatabaseHandler;
import be.hogent.hackthefuture.databank.Service;
import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.PhotoAdapter;
import be.hogent.hackthefuture.domein.PostPhoto;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class PhotosFragment extends Fragment implements Callback<List<Photo>>, View.OnClickListener{

    static final int REQUEST_IMAGE_CAPTURE = 1;

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
        Log.i("photos", t.getMessage());
        Log.e("photos", t.getMessage(), t);
        Toast.makeText(this.getActivity(), "Failure", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.fab){



        }
    }

    private void takePic(){

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] b = baos.toByteArray();

            String p = Base64.encodeToString(b, Base64.DEFAULT);

            Log.i("Photos", "base64: " + p);

            PostPhoto foto = new PostPhoto("test", p);

            Service service = Connectie.createService(Service.class, Connectie.token);
            service.newPhoto(foto).enqueue(new Callback<PostPhoto>() {
                @Override
                public void onResponse(Call<PostPhoto> call, Response<PostPhoto> response) {
                    Log.i("fragment", "code: "+response.code());
                    Log.i("fragment", "msg: " + response.message());

                    Toast.makeText(getActivity(), "Succes", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onFailure(Call<PostPhoto> call, Throwable t) {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_LONG).show();
                }
            });
        }


    }
}
