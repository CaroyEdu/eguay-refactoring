package com.test.eguay.entity;

import javax.persistence.*;

@Entity
public class Category {
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "categoryid", nullable = false)
    private Long categoryid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (name != null ? !name.equals(category.name) : category.name != null) return false;
        if (categoryid != null ? !categoryid.equals(category.categoryid) : category.categoryid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (categoryid != null ? categoryid.hashCode() : 0);
        return result;
    }
}
