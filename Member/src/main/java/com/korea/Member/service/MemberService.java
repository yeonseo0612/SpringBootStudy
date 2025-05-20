package com.korea.Member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.korea.Member.model.MemberDTO;
import com.korea.Member.model.MemberEntity;
import com.korea.Member.persistance.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	
	public int createMem(MemberDTO dto) {
		
		MemberEntity entity;
		try {
			
			entity = new MemberEntity();
			
			entity.setName(dto.getName());
			entity.setEmail(dto.getEmail());
			entity.setPassword(dto.getPassword());
			
			memberRepository.save(entity);
		
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
		
		
		
		
	}

	public List<MemberDTO> getAllMember() {

		List<MemberEntity> entityList = memberRepository.findAll();
		List<MemberDTO> dtoList = new ArrayList<>();
		if(entityList != null) {
			for(MemberEntity entity : entityList) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setEmail(entity.getEmail());
				dto.setPassword(entity.getPassword());
				
				dtoList.add(dto);
			}
			
		}
		return dtoList;
	}

	public List<MemberDTO> getFindByEmailMember(String email) {
		
		Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);
		

		List<MemberDTO> dtoList = new ArrayList<>();
		if(memberEntity.isPresent()) {
			MemberDTO dto = new MemberDTO();
			
			dto.setEmail(memberEntity.get().getEmail());
			dto.setId(memberEntity.get().getId());
			dto.setName(memberEntity.get().getName());
			dto.setPassword(memberEntity.get().getPassword());
			
			
			dtoList.add(dto);
			
		}
		return dtoList;
	}

	public int deleteMember(int id) {
		
		Optional<MemberEntity> memberEntity = memberRepository.findById(id);
		
		if(memberEntity.isPresent()) {
			
			memberRepository.deleteById(id);
			
			return 1;
			
		}else {
			
			return 0;
		}

	}

	public int modifyPwd(String email, MemberDTO dto) {
		return 0;

	}

}
