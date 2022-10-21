package com.otsi.retail.authservice.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentPrivilege extends BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

private String name;

private String description;

private Boolean read;

private Boolean write;

private String path;


private Boolean isActive;

@JsonIgnore
@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "parentPrivileges")
private List<Role> roleId;

private Long domain;
@Enumerated(EnumType.STRING)

private PrevilegeType previlegeType;

private Long planId;

private String iconName;



@OneToMany(mappedBy = "parentPrivilegeId", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private List<SubPrivilege> subPrivileges;


}
