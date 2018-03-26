package red.aoi.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;

public class StoryActivity extends AppCompatActivity {

    private Story story;
    private TextView storyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyText = findViewById(R.id.storyText);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        displayStory();
    }

    private void displayStory() {
        storyText.setText(story.getStory());
    }

    public void makeAgainButtonClicked(View view) {
        finish();
        jumpToMainActivity();
    }

    private void jumpToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
