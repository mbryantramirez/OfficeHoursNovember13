package nyc.aisleone.officehoursnov13.utils;
import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Retrofit2Client {
    private static Retrofit2Client instance = null;
    private Retrofit retrofit;
    private OkHttpClient client;

    private PuppyService puppyService;

    public Retrofit2Client() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient.Builder();
        okhttpBuilder.addInterceptor(loggingInterceptor);


        client = okhttpBuilder.build();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(client)
                .build();

        puppyService = retrofit.create(PuppyService.class);
    }

    public static Retrofit2Client getInstance() {
        if (instance == null) {
            instance = new Retrofit2Client();
        }
        return instance;
    }

    public PuppyService getPuppyService() {
        return puppyService;
    }

}
