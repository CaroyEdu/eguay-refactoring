package com.test.eguay.service;

import org.springframework.stereotype.Service;

@Service
public class GroupService {
    @EJB GroupsFacade groupsFacade;
    @EJB UsersFacade usersFacade;

    @EJB UserService userService;

    // Query

    public List<GroupDTO> getAllGroups() {
        return GroupService.this.toDTO(getAllGroupsDAO());
    }

    public GroupDTO getGroup(long groupId) {
        return this.groupsFacade.find(groupId).toDTO();
    }

    // Extra functionalities

    public static List<GroupDTO> toDTO(List<Groups> groups){
        List<GroupDTO> dtos = new ArrayList<>(groups.size());

        for(Groups group : groups){
            dtos.add(group.toDTO());
        }

        return dtos;
    }

    // Logic

    public void createNewGroupFromSelectedGroups(List<Long> groupsIds) {
        Groups newGroup = new Groups();
        List<Groups> selectedGroups = getGroupsDAO(groupsIds);

        newGroup.setName(concatGroupNames(selectedGroups));
        addAllUsersInGroups(newGroup, selectedGroups);

        if(!newGroup.getUsersList().isEmpty())
            groupsFacade.create(newGroup);
    }

    public void createNewGroup(String name, List<Integer> userIds){
        Groups newGroup = new Groups();
        List<Users> users = usersFacade.findAll(userIds);

        newGroup.setName(name);
        newGroup.setUsersList(users);
        groupsFacade.create(newGroup);

        addGroupToUserList(users, newGroup);
    }

    public void newGroupFromSelectedUsers(Integer originalGroupId, List<Integer> userIds, String formName) {
        Groups newGroup = new Groups();

        String originalGroupName = getGroupName(originalGroupId);
        setGroupName(newGroup, formName, originalGroupName);

        List<Users> users = usersFacade.findAll(userIds);
        newGroup.setUsersList(users);
        groupsFacade.create(newGroup);

        addGroupToUserList(users, newGroup);
    }

    public void removeGroups(List<Long> groupIds){
        List<Groups> selectedGroups = getGroupsDAO(groupIds);

        for(Groups group : selectedGroups){
            groupsFacade.remove(group);
        }
    }

    public Map<UserDTO, Boolean> GetUsersInGroupMap(GroupDTO groupDTO) {
        HashMap<UserDTO, Boolean> map = new HashMap<>();

        Groups group = toDTO(groupDTO);
        List<UserDTO> allUsers = userService.getAllUsersDTO();
        List<UserDTO> usersInGroup = UserService.toDTO(group.getUsersList());

        for(UserDTO user : allUsers){
            map.put(user, usersInGroup.contains(user));
        }

        return map;
    }

    public void updateGroup(Long groupId, String name, List<Integer> userIds) {
        Groups group = getGroupDAO(groupId);
        List<Users> users = userService.getUsersByIds(userIds);

        group.setName(name);
        group.setUsersList(users);

        groupsFacade.edit(group);
    }

    // private

    private Groups toDTO(GroupDTO group) {
        return getGroupDAO(group.getId());
    }

    private List<Groups> getAllGroupsDAO() {
        return groupsFacade.findAll();
    }

    private Groups getGroupDAO(long groupId) {
        return this.groupsFacade.find(groupId);
    }

    private void addAllUsersInGroups(Groups group, List<Groups> groups){
        if(groups!= null && !groups.isEmpty()){
            createUserListIfDontExist(group);
            for(Groups groupToAdd : groups){
                addAll(group, groupToAdd.getUsersList());
            }
        }
    }

    private void addAll(Groups group, List<Users> users){
        if(users != null && !users.isEmpty()){
            createUserListIfDontExist(group);
            List<Users> userList = group.getUsersList();
            userList.addAll(users);
            group.setUsersList(userList);
        }
    }

    private String concatGroupNames(List<Groups> groups){
        StringJoiner sj = new StringJoiner("-");

        for(Groups group : groups){
            sj.add(group.getName());
        }

        return sj.toString();
    }

    private void createUserListIfDontExist(Groups group){
        if(group.getUsersList() == null)
            group.setUsersList(new LinkedList<>());
    }

    private String getGroupName(Integer groupId){
        return getGroupDAO(groupId).getName();
    }

    private void addGroupToUserList(List<Users> users, Groups newGroup) {
        for(Users user : users){
            userService.addToGroup(user, newGroup);
            usersFacade.edit(user);
        }
    }

    private void setGroupName(Groups newGroup, String formName, String originalGroupName) {
        if(formName.equals(originalGroupName)){
            newGroup.setName(originalGroupName + "2");
        }else{
            newGroup.setName(formName);
        }
    }

    private List<Groups> getGroupsDAO(List<Long> groupsIds) {
        return groupsFacade.findAll(groupsIds);
    }
}
