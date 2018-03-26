package red.aoi.madlibs;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Aoi on 2018-03-26.
 * 代表一个故事，故事中有许多空位，可以填词
 */
class Story implements Serializable {

    private Pattern fillingWordPattern = Pattern.compile("<[^>]+>");

    /**
     * 根据内置的模板文件创建一个 Story 实例
     * @return
     * @param storyStream
     */
    public static Story fromStream(InputStream storyStream) {
        Story story = new Story();
        StringBuilder storyContents = new StringBuilder();
        Scanner scanner = new Scanner(storyStream);
        while (scanner.hasNextLine()) {
            storyContents.append(scanner.nextLine());
        }
        story.setContents(storyContents.toString());
        return story;
    }

    /**
     * 只读，只能被设置第一次
     */
    private String contents;
    private String getContents() {
        return contents;
    }
    private void setContents(String contents) {
        if (this.contents != null) return;

        this.contents = contents;
        initFilledWords();
    }

    private List<String> filledWords;

    private int fillingWordCount;
    public int getFillingWordCount() {
        return fillingWordCount;
    }

    private List<String> hints;

    /**
     * 获取当前要填的词的提示信息
     * @return
     */
    public String getCurrentHint() {
        return hints.get(getCurrentIndex());
    }

    /**
     * 获取当前要填的词是是第几个要填的词
     * @return
     */
    public int getCurrentIndex() {
        return filledWords.size();
    }

    public int getRemainingCount() {
        return fillingWordCount - getCurrentIndex();
    }

    public boolean isFilledUp() {
        return getRemainingCount() == 0;
    }

    /**
     * 向故事中填充一个新单词
     * @param word
     * @return 故事还有空位可以填充新单词，填充成功就返回 true
     */
    public boolean fillWord(String word) {
        if (fillingWordCount <= filledWords.size()) return false;

        filledWords.add(word);

        return true;
    }

    /**
     * 返回填完单词的故事内容
     * @return
     */
    public String getStory() {
        StringBuffer story = new StringBuffer();
        Matcher matcher = fillingWordPattern.matcher(getContents());
        for (String word : filledWords) {
            if (!matcher.find()) break;
            matcher.appendReplacement(story, word);
        }
        matcher.appendTail(story);
        return story.toString();
    }

    private void initFilledWords() {
        calculateFillingWordCount();
        filledWords = new ArrayList<>(fillingWordCount);
    }

    private void calculateFillingWordCount() {
        fillingWordCount = 0;
        hints = new ArrayList<>();
        Matcher matcher = fillingWordPattern.matcher(getContents());
        while (matcher.find()) {
            String hint = matcher.group();
            hint = hint.substring(1, hint.length() - 1);
            hints.add(hint);
            ++fillingWordCount;
        }
    }
}
