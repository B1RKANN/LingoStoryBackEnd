package com.neoimperum.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.neoimperum.enums.LevelStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity implements UserDetails {
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@OneToOne
	private Energy energy;
	
	@OneToOne
	private Puan puan;
	
	@Column(name = "current_level")
	@Enumerated(EnumType.STRING)
	private LevelStatus currentLevel;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of();
	}


	
}
