package com.test.eguay.entity;

import javax.persistence.*;

@Entity
@Table(name = "usersrol", schema = "public", catalog = "da1knun38jg1va")
public class UserRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Basic
    @Column(name = "userid", nullable = true)
    private Long userid;
    @Basic
    @Column(name = "rolid", nullable = true)
    private Long rolid;
    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private User usersByUserid;
    @ManyToOne
    @JoinColumn(name = "rolid", referencedColumnName = "rolid", insertable = false, updatable = false)
    private Rol rolByRolid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getRolid() {
        return rolid;
    }

    public void setRolid(Long rolid) {
        this.rolid = rolid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRol userRol = (UserRol) o;

        if (userid != null ? !userid.equals(userRol.userid) : userRol.userid != null) return false;
        if (rolid != null ? !rolid.equals(userRol.rolid) : userRol.rolid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userid != null ? userid.hashCode() : 0;
        result = 31 * result + (rolid != null ? rolid.hashCode() : 0);
        return result;
    }

    public User getUsersByUserid() {
        return usersByUserid;
    }

    public void setUsersByUserid(User usersByUserid) {
        this.usersByUserid = usersByUserid;
    }

    public Rol getRolByRolid() {
        return rolByRolid;
    }

    public void setRolByRolid(Rol rolByRolid) {
        this.rolByRolid = rolByRolid;
    }
}
