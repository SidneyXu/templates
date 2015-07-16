package com.bookislife.provence;

public class AppException extends Exception {

    public static final int NO_EXCEPTION = 0;
    public static final int UNKOWN_EXCEPTION = -1;

    public static final int USER_NOT_EXIST = 10000;
    public static final int USERNAME_OR_PASSWORD_MISMATCH = 10001;

    private int errorCode;

    public AppException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }


}
