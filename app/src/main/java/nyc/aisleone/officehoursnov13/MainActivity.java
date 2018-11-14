package nyc.aisleone.officehoursnov13;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nyc.aisleone.officehoursnov13.models.PuppyItem;
import nyc.aisleone.officehoursnov13.utils.PuppyService;
import nyc.aisleone.officehoursnov13.utils.Retrofit2Client;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static PuppyService newsService = Retrofit2Client.getInstance().getPuppyService();
    private Button toastButton;
    private Button loadImageButton;
    private ImageView puppyImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instantiate all the views
        init();
    }

    private void init() {

        puppyImage = findViewById(R.id.iv_puppy_image);

        /* Set OnClickListener on button since we are implementing the OnClickListener interface in our main activity
            the input will be this activities OnClickListener
         */
        toastButton = findViewById(R.id.toast_button);
        toastButton.setOnClickListener(this);
        loadImageButton = findViewById(R.id.load_image_button);
        loadImageButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //Switch looks at the specific id of each button and run specific actions
        switch (v.getId()) {
            case R.id.toast_button:
                Toast.makeText(this, "Button Press", Toast.LENGTH_LONG).show();
                break;
            case R.id.load_image_button:
                loadImage();
                break;
            default:
                break;
        }
    }

    private void loadImage() {
        // Ignore for now
        Call<PuppyItem> puppyItemCall = newsService.getPuppyImage();

        puppyItemCall.enqueue(new Callback<PuppyItem>() {
            @Override
            public void onResponse(Call<PuppyItem> call, Response<PuppyItem> response) {
                if (response.body() != null) {
                    String url = response.body().getMessage();
                    Log.d("RETRO", "onSuccess: " + url);
                    Picasso.get()
                            .load(url)
                            .fit()
                            .centerCrop()
                            .into(puppyImage);
                }

            }

            @Override
            public void onFailure(Call<PuppyItem> call, Throwable t) {
                Log.d("RETRO", "onFailure: " + t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
