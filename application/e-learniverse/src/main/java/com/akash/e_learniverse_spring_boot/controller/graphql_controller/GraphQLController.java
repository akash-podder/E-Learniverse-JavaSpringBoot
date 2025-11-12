package com.akash.e_learniverse_spring_boot.controller.graphql_controller;

import com.akash.e_learniverse_spring_boot.domain.dto.graphql_dto.Author;
import com.akash.e_learniverse_spring_boot.domain.dto.graphql_dto.Book;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

@Controller
public class GraphQLController {
    //  http://localhost:9000/graphiql.html --> don't forget ".html" extension in Browsr

    // In your Spring for GraphQL application prepared earlier, add a new file "src/main/resources/graphql/schema.graphqls"

    @QueryMapping
    public Book bookById(@Argument String id) {
        return Book.getById(id);
    }

    @SchemaMapping
    public Author author(Book book) {
        return Author.getById(book.getAuthorId());
    }
}