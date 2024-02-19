package lol.dongkim.testgame;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity  {

    private ImageView eggImageView;
    private TextView clickCountTextView;
    private int clickCount = 0;
    private int eggState = 0; // Track the current image state

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Assuming your layout is 'activity_main.xml'

        eggImageView = findViewById(R.id.eggImageView);
        clickCountTextView = findViewById(R.id.clickCountTextView);

        eggImageView.setOnClickListener(view -> {
            clickCount++;
            updateEggImage();
            clickCountTextView.setText("Clicks: " + clickCount);
        });
    }

    private void updateEggImage() {
        eggState = clickCount / 10; // Image changes every 10 clicks

        if (eggState > 10) { // Make sure eggState doesn't exceed the image count
            eggState = 10;
        }

        int resId = getResources().getIdentifier("egg_" + eggState, "drawable", getPackageName());
        eggImageView.setImageResource(resId);
    }
}