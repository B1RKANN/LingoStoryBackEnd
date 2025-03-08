package com.neoimperum.model.step;

import com.neoimperum.enums.BolumPuanStatus;
import com.neoimperum.enums.CompletionStatus;
import com.neoimperum.enums.StepType;
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
@Table(name = "a1_bolum4_step")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class A1Bolum4Step extends BaseEntity {
    
    @ManyToOne
    private User user;
    
    @Column(name = "step_type")
    @Enumerated(EnumType.STRING)
    private StepType stepType;
    
    @Column(name = "completion_status")
    @Enumerated(EnumType.ORDINAL)
    private CompletionStatus completionStatus;
    
    @Column(name = "bolumpuan_status")
    @Enumerated(EnumType.STRING)
    private BolumPuanStatus bolumPuanStatus;
}
