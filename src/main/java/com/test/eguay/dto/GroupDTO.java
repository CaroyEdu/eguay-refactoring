package com.test.eguay.dto;

import java.util.Objects;

public class GroupDTO {
    // DB
    private Long id;

    // Conceptual
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
