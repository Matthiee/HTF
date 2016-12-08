package be.hogent.hackthefuture.databank;

import java.util.List;

import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.Researcher;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yannick on 8/12/2016.
 */

public interface Service {

    @POST("researcher")
    Call<Researcher> newResarcher(@Body Researcher researcher);

    @POST("researcher/startmission")
    Call<Researcher> startMission(@Body Researcher researcher);

    @POST("photo")
    Call<Photo> newPhoto(@Body Photo photo);

    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos/all")
    Call<List<Photo>> getPhotosAll();

}
