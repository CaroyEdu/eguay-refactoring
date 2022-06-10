package com.test.eguay.service;

import com.test.eguay.dto.GroupDTO;
import com.test.eguay.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {
    public GroupRepository getGroupRepository() {
        return groupRepository;
    }

    @Autowired
    public void setGroupRepository(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    private GroupRepository groupRepository;


    public List<GroupDTO> getAll(){
        return this.groupRepository.findAll().stream().map(group -> group.toDtoLinked()).collect(Collectors.toList());
    }

    public void delete(long[] ids) {
        for (long id : ids){
            this.groupRepository.deleteByGroupid(id);
        }
    }
}
