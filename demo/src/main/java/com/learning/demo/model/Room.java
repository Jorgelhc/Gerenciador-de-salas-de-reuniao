package com.learning.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="MEETING_ROOM")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (name="name")
    private String name;

    @Column (name="date")
    private String date;

    @Column (name="startHour")
    private String startHour;

    @Column (name="endHour")
    private String endHour;

}
