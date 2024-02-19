package lol.dongkim.testgame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.games.GamesSignInClient;
import com.google.android.gms.games.PlayGames;

public class SplashActivity extends AppCompatActivity {

    private GamesSignInClient gamesSignInClient;
    private Button signInButton;
    private Button guestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gamesSignInClient = PlayGames.getGamesSignInClient(this);

        signInButton = findViewById(R.id.sign_in_button);
        guestButton = findViewById(R.id.guest_button);

        signInButton.setOnClickListener(view -> startGoogleSignIn());
        guestButton.setOnClickListener(view -> startMainActivityAsGuest());

        checkAuthentication();
    }

    private void checkAuthentication() {
        gamesSignInClient.isAuthenticated().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().isAuthenticated()) {
                // Player is signed in. Get Player ID and proceed
                getPlayerIdAndStartMainActivity();
            } else {
                // Player not signed in. Show sign-in UI
                showSignInOptions();
            }
        });
    }

    private void getPlayerIdAndStartMainActivity() {
        PlayGames.getPlayersClient(this).getCurrentPlayer()
                .addOnCompleteListener(task -> {
                    String playerId = task.getResult().getPlayerId();
                    // PlayerID available, start the MainActivity
                    startMainActivity(playerId);
                });
    }

    private void startMainActivity(String playerId) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("player_id", playerId);
        startActivity(intent);
        finish();
    }
    private void startGoogleSignIn() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void startMainActivityAsGuest() {
        // Start MainActivity without a player ID
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void showSignInOptions() {
        // Show sign-in buttons and initiate sign-in on click
        // (You'd call gamesSignInClient.signIn() when sign-in buttons are clicked)
        signInButton.setVisibility(View.VISIBLE);
    }

}