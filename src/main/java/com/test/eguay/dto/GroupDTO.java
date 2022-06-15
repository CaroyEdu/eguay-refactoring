// Autores: Pedro Antonio Benito Rojano

package com.test.eguay.dto;

import java.util.List;
import java.util.Objects;

public class GroupDTO {
    // DB
    private Long id;

    // Conceptual
    private String name;

    // Relationships
    private List<Integer> userIds;

    public GroupDTO() {}

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

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
