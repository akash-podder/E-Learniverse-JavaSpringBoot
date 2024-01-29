package com.akash.e_learniverse_spring_boot.domain.entity.many_to_many;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    //Many to Many te NEW ekta TABLE Create hobe
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Movie> movieListX = new ArrayList<>();
//    LAZY = fetch when needed
//    EAGER = fetch immediately

//    For example, you might have an entity called University and another entity called Student and a University might have many Students:
//    Now when you load a University from the database, JPA loads its id, name, and address fields for you. But you have two options for how students should be loaded:
//    Here are two options:
//    1. To load it together with the rest of the fields (i.e. eagerly), or
//    2. To load it on-demand (i.e. lazily) when you call the university's getStudents() method.
//    When a university has many students it is not efficient to load all of its students together with it, especially when they are not needed and in suchlike cases you can declare that you want students to be loaded when they are actually needed. This is called lazy loading.

}