package com.martian.robots;

public enum Orientation {
    N, S, E, W;

    public static Orientation clockwiseRotate(Orientation orient) {
        switch(orient) {
            case N: return E;
            case S: return W;
            case E: return S;
            case W: return N;
        }
        return null;
    }

    public static Orientation antiClockwiseRotate(Orientation orient) {
        switch(orient) {
            case N: return W;
            case S: return E;
            case E: return N;
            case W: return S;
        }
        return null;
    }
}
