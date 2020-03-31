package com.ontravel.model;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Data
@EqualsAndHashCode(of = {"id"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class City {

    @Column(name = "attractions")
    @Type(type = "text")
    String attractions;

    @Id
    String city;
}

