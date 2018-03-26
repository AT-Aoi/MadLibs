package red.aoi.madlibs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class WordFillingActivity extends AppCompatActivity {

    private Story story;
    private EditText input;
    private TextView label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_filling);

        input = findViewById(R.id.fillingWordInput);
        label = findViewById(R.id.remainingWordCountHint);

        Intent intent = getIntent();
        story = (Story) intent.getSerializableExtra("story");

        displayCurrentFillingWord();
    }

    /**
     * 把当前要填写的单词的提示信息写在输入文本框内
     * 显示当前是第几个单词了
     */
    private void displayCurrentFillingWord() {
        setFillingWordInputHint();
        displayRemainingWordCount();
    }

    private void setFillingWordInputHint() {
        input.setHint(story.getCurrentHint());
    }

    private void displayRemainingWordCount() {
        label.setText(String.format(getResources().getString(R.string.remainingWordCount), story.getRemainingCount()));
    }

    public void determineButtonClicked(View view) {
        if (isInputEmpty()) {
            Toast.makeText(this, "输入为空", Toast.LENGTH_SHORT).show();
            return;
        }

        fillWord();
        resetWordInputText();

        if (finishedFilling()) {
            finish();
            jumpToStoryActivity();
        } else {
            displayCurrentFillingWord();
        }
    }

    private boolean finishedFilling() {
        return story.isFilledUp();
    }

    private void resetWordInputText() {
        input.setText("");
    }

    private void fillWord() {
       story.fillWord(input.getText().toString());
    }

    private void jumpToStoryActivity() {
        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra("story", story);
        startActivity(intent);
    }

    private boolean isInputEmpty() {
        return input.getText().toString().isEmpty();
    }
}
