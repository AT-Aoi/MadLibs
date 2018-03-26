package red.aoi.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

public class WordFillingActivity extends AppCompatActivity {

    private Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_filling);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");
    }

    public void determineButtonClicked(View view) {
        finish();
        jumpToStoryActivity();
    }

    private void jumpToStoryActivity() {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }
}
