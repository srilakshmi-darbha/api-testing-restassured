package com.thoughtworks.qabootcamp.utils;

public interface Routes {
    String PATH = "/api";

    String CREATE_USER = PATH + "/users";

    String UPDATE_USER = PATH + "/users/%s";

    String DELETE_USER = PATH + "/users/%s";
}
