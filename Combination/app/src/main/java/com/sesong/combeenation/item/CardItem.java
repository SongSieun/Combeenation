package com.sesong.combeenation.item;

public class CardItem {
    private String title;
    private String contents;
    private int image;

    public CardItem(String title, String contents, int image){
        this.title = title;
        this.contents = contents;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
