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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.otsi.retail.authservice.utils.PrevilegeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPrivilege extends BaseEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String description;
	
	private String childPath;
	
	private String childImage;
	
	private Long domain;
	
	private Long parentPrivilegeId;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "subPrivileges")
	private List<Role> roleId;
	@Enumerated(EnumType.STRING)
	private PrevilegeType previlegeType;

	
}

