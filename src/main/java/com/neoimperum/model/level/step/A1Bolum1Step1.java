package com.neoimperum.model.level.step;

import com.neoimperum.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "a1_bolum1_step1")
public class A1Bolum1Step1 extends BaseEntity {
    
    
    @Column(name = "image_url")
    private String imageUrl;
    
    @Column
    private String title;
    
    @Column
    private String question;
    
    @Column
    private String question2;
    
    @Column
    private String question3;
    
    @Column
    private String correct;
}
