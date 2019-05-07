package com.dventus.ptt.servlet;

public enum StatusMessages {
    // error
    INITIALIZATION_ERROR,
    REQUEST_ERROR,
    SAVE_ERROR,
    MALFORMATED_REQUEST,
    LOGIN_ERROR,
    LOGIN_REQUIRED,


    // success
    SAVE_SUCCESS,
    LOGOUT_SUCCESS,
    VALID,

    NOT_FOUND,

    INTERNAL_SERVER_ERROR,
}
