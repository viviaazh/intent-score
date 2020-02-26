package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.AWAYIMAGE_KEY;
import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMEIMAGE_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;

public class MatchActivity extends AppCompatActivity {

    private Uri uriHome;
    private Uri uriAway;
    private ImageView imageView;
    private ImageView imageView2;
    private TextView homeTeam;
    private TextView awayTeam;
    private TextView scoreHome;
    private TextView scoreAway;

    int scorehome =0;
    int scoreaway =0;

    public static final String HASIL_KEY = "hasil";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        homeTeam = findViewById(R.id.txt_home);
        imageView = findViewById(R.id.home_logo);
        awayTeam = findViewById(R.id.txt_away);
        imageView2 = findViewById(R.id.away_logo);
        scoreHome = findViewById(R.id.score_home);
        scoreAway = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            homeTeam.setText(extras.getString(HOMETEAM_KEY));
            awayTeam.setText(extras.getString(AWAYTEAM_KEY));
            uriHome = Uri.parse(extras.getString(HOMEIMAGE_KEY));
            uriAway = Uri.parse(extras.getString(AWAYIMAGE_KEY));
            Bitmap bitmapHome = null;
            Bitmap bitmapAway = null;
            try{
                bitmapHome = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriHome);
                bitmapAway = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriAway);
            }catch (IOException e){
                e.printStackTrace();
            }
            imageView.setImageBitmap(bitmapHome);
            imageView2.setImageBitmap(bitmapAway);
        }

        //TODO
        //1.Menampilkan detail match sesuai data dari main activity
        //2.Tombol add score menambahkan satu angka dari angka 0, setiap kali di tekan
        //3.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang ke ResultActivity, jika seri di kirim text "Draw"
    }

    public void handleAddHome(View view) {
        scorehome++;
        scoreHome.setText(String.valueOf(scorehome));
    }

    public void handleAddAway(View view) {
        scoreaway++;
        scoreAway.setText(String.valueOf(scoreaway));
    }
    public void handleCek(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        String hasil = null;
        if(scorehome > scoreaway){
            hasil = homeTeam.getText().toString() + " Menang";
        }
        else if(scoreaway > scorehome){
            hasil = awayTeam.getText().toString() + " Menang";
        }
        else{
            hasil = homeTeam.getText().toString() + " Draw dengan " + awayTeam.getText();
        }
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);
    }

}
