package com.sis.retro.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "feedbacks", uniqueConstraints = {@UniqueConstraint(columnNames = {"feedback_name"})})
public class FeedBack {
    @Column(name = "feedback_name")
    @Id
    private String name;
    @Column
    private String body;
    @Column
    private FeedBackType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "retro_name")
    private Retro retro;
}
