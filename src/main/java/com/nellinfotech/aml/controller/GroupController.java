package com.nellinfotech.aml.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nellinfotech.aml.commonUtility.HeaderValidator;
import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.GroupUserMap;
import com.nellinfotech.aml.entities.UserHierarchy;
import com.nellinfotech.aml.model.GroupHierarchy;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.service.GroupService;

@CrossOrigin(origins = "*")
@RestController
public class GroupController {
    @Autowired
    private GroupService groupService;
    
    Logger logger = LoggerFactory.getLogger(GroupController.class);
    
    @PostMapping("/saveGroup")
    public GroupMst saveGroup(@RequestHeader HttpHeaders httpHeaders, @RequestBody GroupMst group) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.saveGroup(header, group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @PostMapping("/updateGroup")
    public GroupMst updateGroup(@RequestHeader HttpHeaders httpHeaders, @RequestBody GroupMst group) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.updateGroup(header, group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @PostMapping("/deleteGroup")
    public GroupMst deleteGroup(@RequestHeader HttpHeaders httpHeaders, @RequestBody GroupMst group) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.deleteGroup(header, group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @GetMapping("/getGroup")
    public List<GroupMst> getGroup(@RequestParam String bankCode) {
        try {
           
            return groupService.getGroup( bankCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @PostMapping("/saveGroupUser")
    public GroupMst saveGroupUser(@RequestHeader HttpHeaders httpHeaders, @RequestBody GroupUserMap group) {
        try {
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.saveGroupUser(header, group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @GetMapping("/getGroupHierychy")
    public List<GroupHierarchy> getGroupHierychy(@RequestParam String userId) {
        try {
            logger.info(" inside GroupController - getGroupHierychy-");
            return groupService.getGroupHierychy(userId);
        } catch (Exception e) {
            logger.error("GroupController  getGroupHierychy" + e);
        }
        return null;
    }
    
    @PostMapping("/saveGroupUserHierychy")
    public String saveGroupUserHierychy(@RequestHeader HttpHeaders httpHeaders,
            @RequestBody List<UserHierarchy> userHierarchy) {
        try {
            logger.info(" inside GroupController - saveGroupUserHierychy-");
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.saveGroupUserHierychy(header, userHierarchy);
        } catch (Exception e) {
            logger.error("GroupController  saveGroupUserHierychy" + e);
        }
        return null;
    }
    
    @GetMapping("/getGroupUserHierychy")
    public ResponseEntity<Map<String, Object>> getGroupUserHierychy(@RequestHeader HttpHeaders httpHeaders,@RequestParam String userId) {
        try {
            logger.info(" inside GroupController - updateGroupUser-" + userId);
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.getGroupUserHierychy(header,userId);
        } catch (Exception e) {
            logger.error("GroupController  updateGroupUser" + e);
        }
        return null;
    }
    
    @PostMapping("/updateGroupUser")
    public String updateGroupUser(@RequestHeader HttpHeaders httpHeaders,@RequestBody UserHierarchy userHierarchy) {
        try {
            logger.info(" inside GroupController - updateGroupUser-" + userHierarchy.getId());
            Header header = HeaderValidator.headerConversion(httpHeaders);
            return groupService.updateGroupUser(header,userHierarchy);
        } catch (Exception e) {
            logger.error("GroupController  updateGroupUser" + e);
        }
        return null;
    }
}
