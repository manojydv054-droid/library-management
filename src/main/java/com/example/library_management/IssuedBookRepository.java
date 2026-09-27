package com.example.library_management;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IssuedBookRepository extends JpaRepository<IssuedBook, Integer> {

}