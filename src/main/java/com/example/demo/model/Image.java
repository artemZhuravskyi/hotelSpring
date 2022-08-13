package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "images")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @JsonIgnore
    @OneToOne
    private Room room;

    private String bedroom;
    private String balcony;
    private String toilet;

    @Override
    public String toString() {
        return bedroom.substring(0, bedroom.length()-4);
    }

}
