package com.minhpt.assignment_ph32719;

import java.io.Serializable;

public class NhanVienModel implements Serializable {
    private String maNV;
    private String hoten;
    private String pb;

    public NhanVienModel(String maNV, String hoten, String pb) {
        this.maNV = maNV;
        this.hoten = hoten;
        this.pb = pb;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getPb() {
        return pb;
    }

    public void setPb(String pb) {
        this.pb = pb;
    }
}
