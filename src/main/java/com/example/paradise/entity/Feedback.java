package com.example.paradise.entity;


import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="feedback")
public class Feedback {
    @Id
    @SequenceGenerator(name = "hms_book_seq_gen", sequenceName = "hms_book_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "hms_book_seq_gen", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "fullname")
    private String fullname;

    @Column(name = "email")
    private String email;

    @Column(name = "subject")
    private String subject;

    @Column(name = "message")
    private String message;

}
