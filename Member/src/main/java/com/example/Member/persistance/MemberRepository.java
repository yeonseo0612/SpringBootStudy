package com.example.Member.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Member.model.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

}
