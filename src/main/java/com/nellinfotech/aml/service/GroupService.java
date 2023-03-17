package com.nellinfotech.aml.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.GroupUserMap;
import com.nellinfotech.aml.entities.UserHierarchy;
import com.nellinfotech.aml.model.GroupHierarchy;
import com.nellinfotech.aml.model.Header;

public interface GroupService {
    
GroupMst saveGroup(Header header, GroupMst group);
    
    GroupMst updateGroup(Header header, GroupMst group);
    
    GroupMst deleteGroup(Header header, GroupMst group);
    
    List<GroupMst> getGroup(String bankCode);
    
    GroupMst saveGroupUser(Header header, GroupUserMap group);
    
    List<GroupHierarchy> getGroupHierychy(String userId);
    
    String saveGroupUserHierychy(Header header, List<UserHierarchy> userHierarchy);
    
    ResponseEntity<Map<String, Object>> getGroupUserHierychy(Header header,String userId);
    
    String updateGroupUser(Header header,UserHierarchy userHierarchy);
    
    
}
