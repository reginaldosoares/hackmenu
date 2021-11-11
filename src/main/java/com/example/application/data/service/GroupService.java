package com.example.application.data.service;

import com.example.application.data.entity.Group;
import com.example.application.repository.GroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public int count() {
        return (int) groupRepository.count();
    }

    public Group save(Group group) {
        return groupRepository.save(group);
    }

}
