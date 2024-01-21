package com.akash.e_learniverse_spring_boot.mapper;

public interface CustomObjectMapper<A, B> {
    B mapTo(A a);
    A mapFrom(B b);
}
