package com.neoimperum.model.level;

import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.LevelType;
import com.neoimperum.model.BaseEntity;
import com.neoimperum.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "a1_user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1User extends BaseEntity {
	
	@ManyToOne
    private User user;
	
	@Column(name = "level_type")
	@Enumerated(EnumType.STRING)
	private LevelType levelType;
	
	@Column(name = "completion_status")
    @Enumerated(EnumType.ORDINAL)
    private CompletionStatus completionStatus;
}
