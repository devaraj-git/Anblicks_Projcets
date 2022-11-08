package com.example.junitrestapiapplication.Repository;

import com.example.junitrestapiapplication.Model.BookEntity;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;


public interface BookRepository extends JpaRepository<BookEntity,Long> {

}
