package com.otsi.retail.authservice.Entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity	
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParentPrivilages extends BaseEntity {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id;
private String name;
private String discription;
private boolean read;
private boolean write;
private String path;
private String parentImage;
@JsonIgnore
@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "parentPrivilages")
private List<Role> roleId;
private int domian;
private LocalDate createdDate;
private LocalDate lastModifyedDate;
private long createdBy;
private String modifiedBy;

}
