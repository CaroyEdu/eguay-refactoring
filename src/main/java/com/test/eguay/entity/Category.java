package com.test.eguay.entity;

import com.test.eguay.dto.CategoryDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "category", schema = "public", catalog = "da1knun38jg1va")
public class Category {
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "categoryid", nullable = false)
    private long categoryid;
    @OneToMany(mappedBy = "categoryByCategoryid")
    private Collection<AuctionCategory> auctioncategoriesByCategoryid;
    @OneToMany(mappedBy = "categoryByCategoryid")
    private Collection<UserCategory> userscategoriesByCategoryid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(long categoryid) {
        this.categoryid = categoryid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Category category = (Category) o;

        if (categoryid != category.categoryid) return false;
        if (name != null ? !name.equals(category.name) : category.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (categoryid ^ (categoryid >>> 32));
        return result;
    }

    public Collection<AuctionCategory> getAuctioncategoriesByCategoryid() {
        return auctioncategoriesByCategoryid;
    }

    public void setAuctioncategoriesByCategoryid(Collection<AuctionCategory> auctioncategoriesByCategoryid) {
        this.auctioncategoriesByCategoryid = auctioncategoriesByCategoryid;
    }

    public Collection<UserCategory> getUserscategoriesByCategoryid() {
        return userscategoriesByCategoryid;
    }

    public void setUserscategoriesByCategoryid(Collection<UserCategory> userscategoriesByCategoryid) {
        this.userscategoriesByCategoryid = userscategoriesByCategoryid;
    }

    public CategoryDTO toDTO(){
        CategoryDTO dto = new CategoryDTO();

        dto.setId(categoryid);
        dto.setName(name);

        return dto;
    }

    public static List<CategoryDTO> toDTO(List<Category> categories){
        List<CategoryDTO> dtos = new ArrayList<>(categories.size());

        for(Category category : categories){
            dtos.add(category.toDTO());
        }

        return dtos;
    }
}
