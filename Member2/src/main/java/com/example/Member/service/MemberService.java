package com.example.Member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.Member.model.MemberDTO;
import com.example.Member.model.MemberEntity;
import com.example.Member.persistance.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;

	public int createMember(MemberDTO member) {
		
		MemberEntity memEntity = new MemberEntity();
		
		memEntity.setAge(member.getAge());
		memEntity.setEmail(member.getEmail());
		memEntity.setId(member.getId());
		memEntity.setName(member.getName());
		
			try {
				memberRepository.save(memEntity);
				
				return 1;
				
			} catch (Exception e) {
				// TODO: handle exception
				return -1;
				
			}
			
	}

	public List<MemberDTO> getMemberList() {

		List<MemberEntity> member = memberRepository.findAll();
		
		List<MemberDTO> dtoList = new ArrayList<>();
		
		for(MemberEntity memberList : member) {
			MemberDTO dto = new MemberDTO();
			
			dto.setAge(memberList.getAge());
			dto.setName(memberList.getName());
			dto.setId(memberList.getId());
			dto.setEmail(memberList.getEmail());
			
			dtoList.add(dto);
		}
		
		return dtoList;
	}

	public int deleteMemeber(Long id) {
	
		 Optional<MemberEntity> optionalEntity = memberRepository.findById(id);
		 
		  if (optionalEntity.isEmpty()) {
		        return 0;
		    }else {
		    	
		    	try {
		    		memberRepository.deleteById(id);
					return 1;
				} catch (Exception e) {
					// TODO: handle exception
					return 0;
				}
						    	
		    }

	}

	public int updateMember(Long id, MemberDTO member) {
		
		Optional<MemberEntity> optionalEntity = memberRepository.findById(id);
		
		if(!optionalEntity.isPresent()) {
			return 0;
		}else {
			try {
				  MemberEntity IdMember = optionalEntity.get();
				  
					  IdMember.setAge(member.getAge());
					  IdMember.setEmail(member.getEmail());
					  IdMember.setName(member.getName());
					  
					  memberRepository.save(IdMember);
					  
					  return 1;
				  
				  
			} catch (Exception e) {
					return -1;
			}
		}
		
	}


	
	
	

}
