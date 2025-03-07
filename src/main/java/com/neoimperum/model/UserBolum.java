package com.neoimperum.model;

import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.BolumType;
import com.neoimperum.enums.CompletionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_bolum")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserBolum extends BaseEntity{
	
	@Column(name = "bolum_type")
	@Enumerated(EnumType.STRING)
	private BolumType bolumType;
	
	
	@Column(name = "completion_status")
	@Enumerated(EnumType.ORDINAL)
	private CompletionStatus completionStatus;
	
	@Column(name = "bolum_puan_status")
	@Enumerated(EnumType.ORDINAL)
	private BolumPuanStatus bolumPuanStatus;
}
