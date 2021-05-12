package com.dddd.SLDocs.core.servImpls;

import com.dddd.SLDocs.core.entities.Group;
import com.dddd.SLDocs.core.repositories.GroupRepository;
import com.dddd.SLDocs.core.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private GroupRepository groupRepository;

    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> ListAll() {
        return groupRepository.findAll();
    }

    @Override
    public void save(Group group) {
        groupRepository.save(group);
    }

    public Group findByName(String name){
        return groupRepository.getGroupByName(name);
    }
}
