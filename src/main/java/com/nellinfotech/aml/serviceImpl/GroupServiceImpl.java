package com.nellinfotech.aml.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nellinfotech.aml.commonUtility.DateValidator;
import com.nellinfotech.aml.constant.ResponseStatus;
import com.nellinfotech.aml.entities.GroupMst;
import com.nellinfotech.aml.entities.GroupUserMap;
import com.nellinfotech.aml.entities.UserHierarchy;
import com.nellinfotech.aml.entities.UserMst;
import com.nellinfotech.aml.model.GroupHierarchy;
import com.nellinfotech.aml.model.Header;
import com.nellinfotech.aml.model.UserGroupHierarchy;
import com.nellinfotech.aml.repository.GroupRepository;
import com.nellinfotech.aml.repository.GroupUserRepository;
import com.nellinfotech.aml.repository.UserHierarchyRepoitory;
import com.nellinfotech.aml.repository.UserMstRepository;
import com.nellinfotech.aml.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    DateValidator dateValidator;
    @Autowired
    GroupUserRepository groupUserRepository;
    @Autowired
    UserHierarchyRepoitory hierarchyRepository;
    @Autowired
    UserMstRepository userMstRepository;
    
    @Override
    public GroupMst saveGroup(Header header, GroupMst group) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            group.setBankCode(header.getBankCode());
            group.setCreatedBy(header.getUserId());
            group.setCreatedDate(currentDate);
            return groupRepository.save(group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public GroupMst updateGroup(Header header, GroupMst group) {
        try {
            GroupMst existingGroup = groupRepository.findById(group.getId()).orElse(null);
            
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            existingGroup.setBankCode(header.getBankCode());
            existingGroup.setModifiedBy(header.getUserId());
            existingGroup.setModifiedDate(currentDate);
            existingGroup.setGroupName(group.getGroupName());
            existingGroup.setGroupDescription(group.getGroupDescription());
            return groupRepository.save(existingGroup);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    
    @Override
    public GroupMst deleteGroup(Header header, GroupMst group) {
        try {
            GroupMst existingGroup = groupRepository.findById(group.getId()).orElse(null);
            
//            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
//            existingGroup.setBankCode(header.getBankCode());
//            existingGroup.setModifiedBy(header.getUserId());
//            existingGroup.setModifiedDate(currentDate);
//            existingGroup.setGroupName(group.getGroupName());
//            existingGroup.setGroupDescription(group.getGroupDescription());
            existingGroup.setIsActive(2);
            return groupRepository.save(existingGroup);
            
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<GroupMst> getGroup(String bankCode) {
        try {
        	Integer isActive=1;
            return groupRepository.findAllByIsActiveAndBankCode(isActive,bankCode);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public GroupMst saveGroupUser(Header header, GroupUserMap group) {
        try {
            Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
            group.setBankCode(header.getBankCode());
            group.setCreatedBy(header.getUserId());
            group.setCreatedDate(currentDate);
            groupUserRepository.save(group);
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public List<GroupHierarchy> getGroupHierychy(String userId) {
        try {
            GroupMst group = new GroupMst();
            List<GroupMst> groupList = groupRepository.findByIsActive(group.getIsActive());
            List<GroupHierarchy> groupHierarchy = new ArrayList<GroupHierarchy>();
            List<GroupUserMap> groupuserMap = groupUserRepository.findByUserId(userId);
            for (GroupMst groupMst : groupList) {
                if (groupuserMap.size() > 0) {
                    int checkFlag = 0;
                    GroupHierarchy groupdata = new GroupHierarchy();
                    for (GroupUserMap groupUserMap2 : groupuserMap) {
                        
                        if (groupMst.getGroupName().contentEquals(groupUserMap2.getGroupName())) {
                            groupdata.setUserId(groupUserMap2.getUserId());
                            groupdata.setGroupName(groupMst.getGroupName());
                            groupdata.setGroupDescription(groupMst.getGroupDescription());
                            groupHierarchy.add(groupdata);
                            checkFlag = checkFlag + 1;
                        }
                    }
                    if (checkFlag != 1) {
                        groupdata.setGroupName(groupMst.getGroupName());
                        groupdata.setGroupDescription(groupMst.getGroupDescription());
                        groupdata.setUserId("0");
                        groupHierarchy.add(groupdata);
                    }
                } else {
                    GroupHierarchy groupdata = new GroupHierarchy();
                    groupdata.setGroupName(groupMst.getGroupName());
                    groupdata.setGroupDescription(groupMst.getGroupDescription());
                    groupdata.setUserId("0");
                    groupHierarchy.add(groupdata);
                }
            }
            
            return groupHierarchy;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String saveGroupUserHierychy(Header header, List<UserHierarchy> userHierarchy) {
        try {
            List<UserHierarchy> user = new ArrayList<UserHierarchy>();
            for (UserHierarchy userHierarchy2 : userHierarchy) {
                Date currentDate = dateValidator.getStrToDate(header.getCurrentDate());
                userHierarchy2.setBankCode(header.getBankCode());
                userHierarchy2.setCreatedBy(header.getUserId());
                userHierarchy2.setCreatedDate(currentDate);
                user.add(userHierarchy2);
            }
            hierarchyRepository.saveAll(user);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public ResponseEntity<Map<String, Object>> getGroupUserHierychy(Header header,String userId) {
        try {
        	 String bankCode=header.getBankCode();
        	 Integer IsActive=1;
            List<UserMst> user = userMstRepository.findByBankCodeAndIsActive(bankCode,IsActive);
            List<UserMst> userList = new ArrayList<UserMst>();
           
            List<UserHierarchy> userHirarcy = hierarchyRepository.findByUserIdAndBankCodeAndIsActive(userId,bankCode,IsActive);
            List<UserGroupHierarchy> userhirList = new ArrayList<UserGroupHierarchy>();
            
            for (UserMst userData : user) {
                if (!userData.getUserId().contentEquals(userId)) {
                    userList.add(userData);
                }
            }
            ///////////
            GroupMst group = new GroupMst();
            List<GroupMst> groupList = groupRepository.findByIsActiveAndBankCode(group.getIsActive(),bankCode);
            List<GroupHierarchy> groupHierarchy = new ArrayList<GroupHierarchy>();
            //  List<GroupUserMap> groupuserMap = groupUserRepository.findByUserId(userId);
            for (GroupMst groupMst : groupList) {
                if (userHirarcy.size() > 0) {
                    int checkFlag = 0;
                    GroupHierarchy groupdata = new GroupHierarchy();
                    for (UserHierarchy usergr : userHirarcy) {
                        
                        if (groupMst.getGroupName().contentEquals(usergr.getGroupName())) {
                            groupdata.setUserId(usergr.getUserId());
                            groupdata.setGroupName(groupMst.getGroupName());
                            groupdata.setGroupDescription(groupMst.getGroupDescription());
                            groupdata.setId(usergr.getId());
                            groupHierarchy.add(groupdata);
                            checkFlag = checkFlag + 1;
                        }
                    }
                    if (checkFlag != 1) {
                        groupdata.setGroupName(groupMst.getGroupName());
                        groupdata.setGroupDescription(groupMst.getGroupDescription());
                        groupdata.setUserId("0");
                        
                        groupHierarchy.add(groupdata);
                    }
                } else {
                    GroupHierarchy groupdata = new GroupHierarchy();
                    groupdata.setGroupName(groupMst.getGroupName());
                    groupdata.setGroupDescription(groupMst.getGroupDescription());
                    groupdata.setUserId("0");
                    groupHierarchy.add(groupdata);
                }
            }
            ///////////////
            for (UserMst usermst : userList) {
                
                if (userHirarcy.size() > 0) {
                    int checkFlag = 0;
                    UserGroupHierarchy userhir = new UserGroupHierarchy();
                    for (UserHierarchy userHierarchy : userHirarcy) {
                        
                        if (userHierarchy.getReportedUserId().contentEquals(usermst.getUserId())) {
                            
                            userhir.setUserId(usermst.getUserId());
                            userhir.setUserName(usermst.getFirstName() + usermst.getLastName());
                            userhir.setUserRole("1");
                            userhir.setId(userHierarchy.getId());
                            userhir.setUserRoleName(usermst.getBaseRoleCode());
                            userhirList.add(userhir);
                            checkFlag = checkFlag + 1;
                            
                        }
                    }
                    
                    if (checkFlag != 1) {
                        userhir.setUserId(usermst.getUserId());
                        userhir.setUserName(usermst.getFirstName() + usermst.getLastName());
                        userhir.setUserRoleName(usermst.getBaseRoleCode());
                        userhir.setUserRole("0");
                        userhirList.add(userhir);
                    }
                    
                } else {
                    UserGroupHierarchy userhir = new UserGroupHierarchy();
                    userhir.setUserId(usermst.getUserId());
                    userhir.setUserName(usermst.getFirstName() + usermst.getLastName());
                    userhir.setUserRoleName(usermst.getBaseRoleCode());
                    userhir.setUserRole("0");
                    userhirList.add(userhir);
                }
            }
            Map<String, Object> json = new HashMap<String, Object>();
            ResponseEntity<Map<String, Object>> result = null;
            json.put("group", groupHierarchy);
            json.put("user", userhirList);
            result = (new ResponseEntity<Map<String, Object>>(json, HttpStatus.OK));
            return result;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }
    
    @Override
    public String updateGroupUser(Header header,UserHierarchy userHierarchy) {
        try {
            hierarchyRepository.deleteByIdAndBankCode(userHierarchy.getId(),header.getBankCode());
            //            UserHierarchy reportedUser = hierarchyRepository.findById(userHierarchy.getId()).orElse(null);
            //            reportedUser.setIsActive(0);
            //            hierarchyRepository.save(reportedUser);
            return ResponseStatus.SUCCESS;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

	}
