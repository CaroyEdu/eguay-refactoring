package com.test.eguay.service;

import com.test.eguay.dto.GroupDTO;
import com.test.eguay.entity.Group;
import com.test.eguay.entity.User;
import com.test.eguay.entity.UserGroups;
import com.test.eguay.repository.GroupRepository;
import com.test.eguay.repository.UserGroupsRepository;
import com.test.eguay.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    private GroupRepository groupRepository;
    private UserGroupsRepository userGroupsRepository;
    private UserRepository userRepository;

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

    public UserRepository getUserRepository() {
        return userRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<GroupDTO> getAll(){
        return this.groupRepository.findAll().stream().map(group -> group.toDto()).collect(Collectors.toList());
    }

    public List<GroupDTO> getAllLinked(){
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
        if(group.getName() == null || group.getName().isEmpty())
            group.setName(String.join("-", this.userRepository.findAllByUseridIn(userIds).stream().map(user -> user.getUsername()).collect(Collectors.toList())));

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

    public void join(long[] ids) {
        List<User> users = this.userRepository.findAllByUsersGroup_Group_IdIn(ids);
        Group group = new Group();
        group.setName(String.join("-", this.groupRepository.findAllByGroupidIn(ids).stream().map(group1 -> group1.getName()).collect(Collectors.toList())));
        UserGroups relationship;

        this.groupRepository.save(group);
        for (User user : users){
            relationship = new UserGroups();
            relationship.setGroupid(group.getId());
            relationship.setUserid((long) user.getUserid());
            this.userGroupsRepository.save(relationship);
        }
    }
}
