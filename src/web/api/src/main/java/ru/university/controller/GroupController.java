package ru.university.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.university.entity.Group;
import ru.university.service.GroupService;

import java.util.List;

@RestController
@RequestMapping(value = "group")
public class GroupController {


    @Autowired GroupService groupService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Group getById(@PathVariable("id") Long id){
        return groupService.findById(id);
    }

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public List<Group> all(){
        return groupService.findByTeacher();
    }

    @RequestMapping(value = "groups", method = RequestMethod.GET)
    public List<Group> findGroupsByCurrentTeacher(){
        return  groupService.getGroupFromCurrentTeacher();
    }
}
