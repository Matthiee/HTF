package be.hogent.hackthefuture.databank;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yannick on 8/12/2016.
 */

public class Connectie {

    public static String token = "";

    public final static int CONTENT_CREATED = 201;
    public final static int BAD_REQ = 400;
    public final static int UNAUTHORIZED = 401;
    public final static int OFFLINE = 503;
    public final static int ALREADY_ON_MISSION = 409;

    public static final String API_BASE_URL = "https://htf-mars.azurewebsites.net/api/";

    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null);
    }

    public static <S> S createService(Class<S> serviceClass, final String authToken) {
        if (authToken != null) {
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", authToken)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

}
