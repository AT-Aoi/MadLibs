package red.aoi.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gameStartButtonClicked(View view) {
        Story story = Story.fromTemplate();
        finish();
        jumpToWordFillingActivity(story);
    }

    private void jumpToWordFillingActivity(Story story) {
        Intent intent = new Intent(this, WordFillingActivity.class);
        intent.putExtra("Story", story);
        startActivity(intent);
    }
}
