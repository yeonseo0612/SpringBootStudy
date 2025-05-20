package com.korea.Member.persistance;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.korea.Member.model.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
	Optional<MemberEntity> findByEmail(String email);
	
}
