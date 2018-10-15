package com.demo.contacts.rest.mediatypes;

import lombok.*;
import org.springframework.hateoas.Identifiable;
import org.springframework.hateoas.core.Relation;

@Relation
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Contact implements Identifiable<Integer> {
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String email;
    private String profession;

    public Integer getId() {
        return this.id;
    }
}
