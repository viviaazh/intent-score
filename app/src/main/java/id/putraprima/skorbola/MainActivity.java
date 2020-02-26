package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getCanonicalName();
    private Uri imageUriHome = null;
    private Uri imageUriAway = null;

    public static final String HOMETEAM_KEY = "homeTeam";
    public static final String AWAYTEAM_KEY = "awayTeam";
    public static final String HOMEIMAGE_KEY = "homeimage";
    public static final String AWAYIMAGE_KEY = "awayimage";

    private EditText hometeamInput;
    private EditText awayteamInput;
    private ImageView homeImage;
    private ImageView awayImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hometeamInput = findViewById(R.id.home_team);
        homeImage = findViewById(R.id.home_logo);
        awayteamInput = findViewById(R.id.away_team);
        awayImage = findViewById(R.id.away_logo);


        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if (resultCode == RESULT_CANCELED) {
                return;
            }
            if (requestCode == 1) {
                if (data != null) {
                    try {
                        imageUriHome = data.getData();
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriHome);
                        homeImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                        Log.e(TAG, e.getMessage());
                    }
                }
            }
        if (requestCode == 2) {
            if (data != null) {
                try {
                    imageUriAway = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUriAway);
                    awayImage.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't Load Image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }

    }


    public void handleNext(View view) {
        String hometeam = hometeamInput.getText().toString();
        String awayteam = awayteamInput.getText().toString();

        if(hometeamInput.length()==0){
            hometeamInput.setError("Home team dilarang kosong");
        }
        else if(awayteamInput.length()==0){
            awayteamInput.setError("Away team dilarang kosong");
        }
        else{
            Intent intent = new Intent(this, MatchActivity.class);
            intent.putExtra(HOMETEAM_KEY, hometeam);
            intent.putExtra(AWAYTEAM_KEY, awayteam);
            intent.putExtra(HOMEIMAGE_KEY, imageUriHome.toString());
            intent.putExtra(AWAYIMAGE_KEY, imageUriAway.toString());
            startActivity(intent);
        }
    }

    public void handleHomeImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleAwayImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }


}
