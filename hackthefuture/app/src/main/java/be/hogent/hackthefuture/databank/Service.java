package be.hogent.hackthefuture.databank;

import java.util.List;

import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.PostPhoto;
import be.hogent.hackthefuture.domein.Researcher;
import be.hogent.hackthefuture.domein.Sample;
import be.hogent.hackthefuture.domein.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Yannick on 8/12/2016.
 */

public interface Service {

    @POST("researcher")
    Call<Token> newResarcher(@Body Researcher researcher);

    @POST("photo")
    Call<PostPhoto> newPhoto(@Body PostPhoto photo);

    @POST("researcher/startmission")
    Call<Token> startMission(@Body Researcher researcher);

    @POST("photo")
    Call<Photo> newPhoto(@Body Photo photo);

    @GET("photos")
    Call<List<Photo>> getPhotos();

    @GET("photos/all")
    Call<List<Photo>> getPhotosAll();

    @POST("sample")
    Call<Sample> newSample(@Body Sample sam);

    @GET("samples")
    Call<List<Sample>> getSamples();

    @GET("samples/all")
    Call<List<Sample>> getSamplesAll();

}
