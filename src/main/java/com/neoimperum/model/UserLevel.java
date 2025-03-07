package com.neoimperum.model;

import java.util.ArrayList;
import java.util.List;

import com.neoimperum.enums.LevelStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_level")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLevel extends BaseEntity{
	
	@Column(name = "level_status")
	@Enumerated(EnumType.STRING)
	private LevelStatus levelStatus;
	
	@OneToMany
	private List<UserBolum> userBolum = new ArrayList<>();
	
	
}
