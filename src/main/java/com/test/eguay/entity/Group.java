// Autores: Pedro Antonio Benito Rojano

package com.test.eguay.entity;

import com.test.eguay.dto.GroupDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
@Table(name = "groups", schema = "public", catalog = "da1knun38jg1va")
public class Group {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "groupid", nullable = false)
    private Long groupid;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @OneToMany(mappedBy = "groupsByGroupid")
    private Collection<GroupMail> groupsmailsByGroupid;
    @OneToMany(mappedBy = "groupsByGroupid")
    private Collection<UserGroups> usersgroupsByGroupid;

    public Long getId() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
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

        Group group = (Group) o;

        if (groupid != null ? !groupid.equals(group.groupid) : group.groupid != null) return false;
        return name != null ? name.equals(group.name) : group.name == null;
    }

    @Override
    public int hashCode() {
        int result = groupid != null ? groupid.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public Collection<GroupMail> getGroupsmailsByGroupid() {
        return groupsmailsByGroupid;
    }

    public void setGroupsmailsByGroupid(Collection<GroupMail> groupsmailsByGroupid) {
        this.groupsmailsByGroupid = groupsmailsByGroupid;
    }

    public Collection<UserGroups> getUsersgroupsByGroupid() {
        return usersgroupsByGroupid;
    }

    public void setUsersgroupsByGroupid(Collection<UserGroups> usersgroupsByGroupid) {
        this.usersgroupsByGroupid = usersgroupsByGroupid;
    }

    public GroupDTO toDtoLinked() {
        GroupDTO dto = this.toDto();
        dto.setUserIds(this.usersgroupsByGroupid.stream().map(userGroups -> userGroups.getUsersByUserid().getUserid()).collect(Collectors.toList()));
        return dto;
    }

    public GroupDTO toDto() {
        GroupDTO dto = new GroupDTO();

        dto.setId(this.getId());
        dto.setName(this.getName());

        return dto;
    }
}
