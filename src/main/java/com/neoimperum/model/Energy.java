package com.neoimperum.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "energy")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Energy extends BaseEntity {
	
	@Column(name = "user_energy")
	private Byte userEnergy;
	
}
