package com.otsi.retail.authservice.requestModel;

import java.util.List;

import com.otsi.retail.authservice.Entity.Privilages;

import lombok.Data;

@Data
public class CreateRoleRequest {
private String roleName;
private String description;
private int precedence;
private List<Privilages> privilages;
}
