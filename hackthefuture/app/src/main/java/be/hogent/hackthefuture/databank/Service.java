package be.hogent.hackthefuture.databank;

import be.hogent.hackthefuture.domein.Researcher;
import be.hogent.hackthefuture.domein.Token;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Yannick on 8/12/2016.
 */

public interface Service {

    @POST("researcher")
    Call<Token> newResarcher(@Body Researcher researcher);

    @POST("researcher/startmission")
    Call<Token> startMission(@Body Researcher researcher);

}
