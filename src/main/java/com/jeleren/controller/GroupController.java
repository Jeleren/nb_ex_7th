package com.jeleren.controller;

import com.jeleren.service.IGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("group")
public class GroupController {

    @Autowired
    private IGroupService groupService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List getGroup () {
        return groupService.getGroup();
    }
}
