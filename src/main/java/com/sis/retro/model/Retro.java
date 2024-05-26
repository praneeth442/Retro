package com.sis.retro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "retros", uniqueConstraints = {@UniqueConstraint(columnNames = {"retro_name"})})
public class Retro {

    @Column(name="retro_name")
    @Id
    private String name;
    @Column
    private String summary;
    @Column
    private LocalDate date;
    @Column
    private List<String> participants;
    @OneToMany(fetch= FetchType.LAZY,cascade= CascadeType.ALL,mappedBy="retro")
    private List<FeedBack> feedBacks;
}
