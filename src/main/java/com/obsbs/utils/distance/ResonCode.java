package com.obsbs.utils.distance;

public enum ResonCode {
    Kind(1),
    Pflegefall(2);

    private int code;

    ResonCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
