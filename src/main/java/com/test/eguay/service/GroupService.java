package com.test.eguay.service;

import com.test.eguay.dto.GroupDTO;
import com.test.eguay.entity.Group;
import com.test.eguay.entity.UserGroups;
import com.test.eguay.repository.GroupRepository;
import com.test.eguay.repository.UserGroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserGroupsRepository userGroupsRepository;

    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public UserGroupsRepository getUserGroupsRepository() {
        return userGroupsRepository;
    }

    @Autowired
    public void setUserGroupsRepository(UserGroupsRepository userGroupsRepository) {
        this.userGroupsRepository = userGroupsRepository;
    }

    public List<GroupDTO> getAll(){
        return this.groupRepository.findAll().stream().map(group -> group.toDtoLinked()).collect(Collectors.toList());
    }

    public void delete(long[] ids) {
        for (long id : ids){
            this.userGroupsRepository.deleteAllByGroupid(id);
            this.groupRepository.deleteByGroupid(id);
        }
    }

    public void save(GroupDTO groupDTO, int[] userIds) {
        Group group = new Group();
        group.setGroupid(groupDTO.getId());
        group.setName(groupDTO.getName());
        this.groupRepository.save(group);

        UserGroups relationship;
        for (int id : userIds){
            relationship = new UserGroups();
            relationship.setGroupid(group.getId());
            relationship.setUserid((long) id);
            this.userGroupsRepository.save(relationship);
        }
    }

    public GroupDTO get(long id) {
        return this.groupRepository.getById(id).toDtoLinked();
    }
}
