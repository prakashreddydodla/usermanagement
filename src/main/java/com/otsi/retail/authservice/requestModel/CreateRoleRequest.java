package com.otsi.retail.authservice.requestModel;

import lombok.Data;

@Data
public class CreateRoleRequest {
private String roleName;
private String description;
private int precedence;
}
