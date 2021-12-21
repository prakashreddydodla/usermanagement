package com.otsi.retail.authservice.Entity;

import java.util.List;

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
public class ColourFamilies {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int fId;
	private String colourFamily;

}
