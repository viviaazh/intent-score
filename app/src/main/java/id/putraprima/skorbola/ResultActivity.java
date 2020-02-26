package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.HASIL_KEY;

public class ResultActivity extends AppCompatActivity {
    private TextView hasil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        hasil = findViewById(R.id.textView3);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            hasil.setText(extras.getString(HASIL_KEY));
        }
    }
}
