package com.neoimperum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "puan")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Puan extends BaseEntity {

	@Column(name = "user_puan")
	private Integer userPuan;
	
	
}
