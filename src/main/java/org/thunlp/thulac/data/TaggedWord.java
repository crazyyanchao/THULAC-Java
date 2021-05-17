package org.thunlp.thulac.data;

/**
 * A class which represent a tagged word, that is, a word with a tag.
 */
public class TaggedWord {

    public String word;
    public String tag;
    public String description;

    public TaggedWord() {
        this.word = "";
    }

    public TaggedWord(String word, String tag) {
        this.word = word;
        this.tag = tag;
        this.description = WordNature.MAP.get(tag);
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
