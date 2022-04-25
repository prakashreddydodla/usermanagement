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
private Long id;
private String name;
private String discription;
private Boolean read;
private Boolean write;
private String path;
private String parentImage;
@JsonIgnore
@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "parentPrivilages")
private List<Role> roleId;
private int domian;
/*private LocalDate createdDate;
private LocalDate lastModifyedDate;*/
//private Long createdBy;
//private Long modifiedBy;

}
