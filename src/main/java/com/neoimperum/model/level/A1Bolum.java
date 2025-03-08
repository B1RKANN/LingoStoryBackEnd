package com.neoimperum.model.level;

import com.neoimperum.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "a1_bolum")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1Bolum extends BaseEntity{
	
	@Column(name = "image_url")
	private String imageUrl;
	
	@Column(name = "color_code")
	private String colorCode;
	
	@Column
	private String title;
	
}
