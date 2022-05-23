package com.prgrms.jpapairboard.user.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @NotNull
    String name;

    @NotNull
    @Column(updatable = false)
    String email;

    Integer age;

    @Enumerated
    Hobby hobby;

    protected User() {
    }

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.hobby = builder.hobby;
    }

    public static Builder builder() {
        return new Builder();
    }


    public static class Builder {
        private String name;
        private String email;
        private Integer age;
        private Hobby hobby;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder hobby(Hobby hobby) {
            this.hobby = hobby;
            return this;
        }

        public User build() {
            return new User(this);
        }

    }
}
