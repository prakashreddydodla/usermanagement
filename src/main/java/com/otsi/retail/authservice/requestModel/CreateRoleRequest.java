package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.ParentPrivilages;

import lombok.Data;

@Data
public class CreateRoleRequest {
private String roleName;
private String description;
private long  clientDomianId;
private String createdBy;
private List<ParentPrivilageVo> parentPrivilages;
private List<SubPrivillagesvo> subPrivillages;
}


