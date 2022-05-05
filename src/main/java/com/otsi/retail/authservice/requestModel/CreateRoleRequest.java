package com.otsi.retail.authservice.requestModel;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {
private Long roleId; //this feild is required for when updating role
private String roleName;
private String description;
private Long  clientDomianId;
private Long createdBy;
private List<ParentPrivilageVo> parentPrivilages;
private List<SubPrivillagesvo> subPrivillages;
}


