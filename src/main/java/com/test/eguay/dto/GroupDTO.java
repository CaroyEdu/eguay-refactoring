package com.test.eguay.dto;

import java.util.List;
import java.util.Objects;

public class GroupDTO {
    // DB
    private Long id;

    // Conceptual
    private String name;

    // Relationships
    private List<UserDTO> users;

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

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
}
