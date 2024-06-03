package com.example.project;

public class TripeTypes {
    private int Mainimage ;
    private int logoImage ;
    private String type ;

    public TripeTypes(int mainimage, int logoImage, String type) {
        Mainimage = mainimage;
        this.logoImage = logoImage;
        this.type = type;
    }

    public int getMainimage() {
        return Mainimage;
    }

    public int getLogoImage() {
        return logoImage;
    }

    public String getType() {
        return type;
    }

    public void setMainimage(int mainimage) {
        Mainimage = mainimage;
    }

    public void setLogoImage(int logoImage) {
        this.logoImage = logoImage;
    }

    public void setType(String type) {
        this.type = type;
    }
}
