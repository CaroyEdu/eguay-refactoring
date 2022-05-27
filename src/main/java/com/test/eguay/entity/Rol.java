package com.test.eguay.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "rol", schema = "public", catalog = "da1knun38jg1va")
public class Rol {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "rolid", nullable = false)
    private Long rolid;
    @Basic
    @Column(name = "type", nullable = false)
    private Integer type;
    @Basic
    @Column(name = "name", nullable = false, length = -1)
    private String name;
    @OneToMany(mappedBy = "rolByRolid")
    private Collection<UserRol> usersrolsByRolid;

    public Long getRolid() {
        return rolid;
    }

    public void setRolid(Long rolid) {
        this.rolid = rolid;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rol rol = (Rol) o;

        if (rolid != null ? !rolid.equals(rol.rolid) : rol.rolid != null) return false;
        if (type != null ? !type.equals(rol.type) : rol.type != null) return false;
        if (name != null ? !name.equals(rol.name) : rol.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = rolid != null ? rolid.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<UserRol> getUsersrolsByRolid() {
        return usersrolsByRolid;
    }

    public void setUsersrolsByRolid(Collection<UserRol> usersrolsByRolid) {
        this.usersrolsByRolid = usersrolsByRolid;
    }
}
