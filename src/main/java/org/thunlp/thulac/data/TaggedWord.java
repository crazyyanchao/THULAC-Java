package org.thunlp.thulac.data;

/**
 * A class which represent a tagged word, that is, a word with a tag.
 */
public class TaggedWord {

    // 词
    public String word;
    // 词性
    public String tag;
    // 词性标签的描述
    public String description;

    public TaggedWord() {
        this.word = "";
    }

    public TaggedWord(String word, String tag) {
        this.word = word != null ? word.replace("\n", "") : null;
        this.tag = tag;
        this.description = WordNature.MAP.getOrDefault(tag,null);
    }

    public TaggedWord(String word) {
        this.word = word != null ? word.replace("\n", "") : null;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
