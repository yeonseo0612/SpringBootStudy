package com.example.board.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.board.model.BoardEntity;

public interface BoardRepository extends JpaRepository<BoardEntity, Long>{

}
