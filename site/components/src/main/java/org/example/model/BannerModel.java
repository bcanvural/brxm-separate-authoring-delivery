package org.example.model;

import javax.validation.constraints.NotNull;

public class BannerModel {
    @NotNull
    private String title;

    @NotNull
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
