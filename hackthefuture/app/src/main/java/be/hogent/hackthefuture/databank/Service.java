package be.hogent.hackthefuture.databank;

import java.util.List;

import be.hogent.hackthefuture.domein.Photo;
import be.hogent.hackthefuture.domein.PostPhoto;
import be.hogent.hackthefuture.domein.Researcher;
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

    @GET("photos/all")
    Call<List<Photo>> getAllPhotos();

}
