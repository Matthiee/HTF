package be.hogent.hackthefuture.databank;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yannick on 8/12/2016.
 */

public class Connectie {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://htf-mars.azurewebsites.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    Service service = retrofit.create(Service.class);
}
