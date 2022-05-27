//package com.test.eguay.service;
//
//import com.test.eguay.dto.GroupDTO;
//import com.test.eguay.dto.UserDTO;
//import com.test.eguay.entity.Group;
//import com.test.eguay.entity.User;
//import com.test.eguay.repository.GroupRepository;
//import com.test.eguay.repository.UserRepository;
//import org.springframework.stereotype.Service;
//
//import java.util.*;
//
//@Service
//public class GroupService {
//    GroupRepository groupRepository;
//    UserRepository userRepository;
//    UserService userService;
//
//    // Query
//
//    public List<GroupDTO> getAllGroups() {
//        return GroupService.this.toDTO(getAllGroupsDAO());
//    }
//
//    public GroupDTO getGroup(long groupId) {
//        return this.groupsFacade.find(groupId).toDTO();
//    }
//
//    // Extra functionalities
//
//    public static List<GroupDTO> toDTO(List<Group> groups){
//        List<GroupDTO> dtos = new ArrayList<>(groups.size());
//
//        for(Group group : groups){
//            dtos.add(group.toDTO());
//        }
//
//        return dtos;
//    }
//
//    // Logic
//
//    public void createNewGroupFromSelectedGroups(List<Long> groupsIds) {
//        Group newGroup = new Group();
//        List<Group> selectedGroups = getGroupsDAO(groupsIds);
//
//        newGroup.setName(concatGroupNames(selectedGroups));
//        addAllUsersInGroups(newGroup, selectedGroups);
//
//        if(!newGroup.getUsersList().isEmpty())
//            groupsFacade.create(newGroup);
//    }
//
//    public void createNewGroup(String name, List<Integer> userIds){
//        Group newGroup = new Group();
//        List<User> users = usersFacade.findAll(userIds);
//
//        newGroup.setName(name);
//        newGroup.setUsersList(users);
//        groupsFacade.create(newGroup);
//
//        addGroupToUserList(users, newGroup);
//    }
//
//    public void newGroupFromSelectedUsers(Integer originalGroupId, List<Integer> userIds, String formName) {
//        Group newGroup = new Group();
//
//        String originalGroupName = getGroupName(originalGroupId);
//        setGroupName(newGroup, formName, originalGroupName);
//
//        List<User> users = usersFacade.findAll(userIds);
//        newGroup.setUsersList(users);
//        groupsFacade.create(newGroup);
//
//        addGroupToUserList(users, newGroup);
//    }
//
//    public void removeGroups(List<Long> groupIds){
//        List<Group> selectedGroups = getGroupsDAO(groupIds);
//
//        for(Group group : selectedGroups){
//            groupsFacade.remove(group);
//        }
//    }
//
//    public Map<UserDTO, Boolean> GetUsersInGroupMap(GroupDTO groupDTO) {
//        HashMap<UserDTO, Boolean> map = new HashMap<>();
//
//        Group group = toDTO(groupDTO);
//        List<UserDTO> allUsers = userService.getAllUsersDTO();
//        List<UserDTO> usersInGroup = UserService.toDTO(group.getUsersList());
//
//        for(UserDTO user : allUsers){
//            map.put(user, usersInGroup.contains(user));
//        }
//
//        return map;
//    }
//
//    public void updateGroup(Long groupId, String name, List<Integer> userIds) {
//        Group group = getGroupDAO(groupId);
//        List<User> users = userService.getUsersByIds(userIds);
//
//        group.setName(name);
//        group.setUsersList(users);
//
//        groupsFacade.edit(group);
//    }
//
//    // private
//
//    private Group toDTO(GroupDTO group) {
//        return getGroupDAO(group.getId());
//    }
//
//    private List<Group> getAllGroupsDAO() {
//        return groupsFacade.findAll();
//    }
//
//    private Group getGroupDAO(long groupId) {
//        return this.groupsFacade.find(groupId);
//    }
//
//    private void addAllUsersInGroups(Group group, List<Group> groups){
//        if(groups!= null && !groups.isEmpty()){
//            createUserListIfDontExist(group);
//            for(Group groupToAdd : groups){
//                addAll(group, groupToAdd.getUsersList());
//            }
//        }
//    }
//
//    private void addAll(Group group, List<User> users){
//        if(users != null && !users.isEmpty()){
//            createUserListIfDontExist(group);
//            List<User> userList = group.getUsersList();
//            userList.addAll(users);
//            group.setUsersList(userList);
//        }
//    }
//
//    private String concatGroupNames(List<Group> groups){
//        StringJoiner sj = new StringJoiner("-");
//
//        for(Group group : groups){
//            sj.add(group.getName());
//        }
//
//        return sj.toString();
//    }
//
//    private void createUserListIfDontExist(Group group){
//        if(group.getUsersList() == null)
//            group.setUsersList(new LinkedList<>());
//    }
//
//    private String getGroupName(Integer groupId){
//        return getGroupDAO(groupId).getName();
//    }
//
//    private void addGroupToUserList(List<User> users, Group newGroup) {
//        for(User user : users){
//            userService.addToGroup(user, newGroup);
//            usersFacade.edit(user);
//        }
//    }
//
//    private void setGroupName(Group newGroup, String formName, String originalGroupName) {
//        if(formName.equals(originalGroupName)){
//            newGroup.setName(originalGroupName + "2");
//        }else{
//            newGroup.setName(formName);
//        }
//    }
//
//    private List<Group> getGroupsDAO(List<Long> groupsIds) {
//        return groupsFacade.findAll(groupsIds);
//    }
//}
