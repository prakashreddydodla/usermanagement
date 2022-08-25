package com.otsi.retail.authservice.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ColorEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long colour_Id;
	
	private String colorCode;
	
    private String colorName;
    
	private String rgb;
	

}
