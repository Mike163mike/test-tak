package com.parser.testparser.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.*;
import javax.persistence.*;

@Entity
@Getter
@Setter
public class Site {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "site", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WordsStatistic> statistics = new ArrayList<>();

}
