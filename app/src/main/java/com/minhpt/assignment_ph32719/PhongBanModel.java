package com.minhpt.assignment_ph32719;

public class PhongBanModel {
    private int img;
    private String name;

    public PhongBanModel(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
