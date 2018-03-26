package red.aoi.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gameStartButtonClicked(View view) {
        InputStream storyStream = getResources().openRawResource(R.raw.madlib1);
        Story story = Story.fromStream(storyStream);
        finish();
        jumpToWordFillingActivity(story);
    }

    private void jumpToWordFillingActivity(Story story) {
        Intent intent = new Intent(this, WordFillingActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }
}
