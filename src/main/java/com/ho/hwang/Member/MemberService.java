package com.ho.hwang.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    //jpa에 findBy...함수가 이미 있기 때문에 메소드를 선언하지 않아도 사용가능

    //모든 회원을 조회하여 memberList에 저장
    public List<Member> findAll(){
        List<Member> memberList = new ArrayList<>();
        memberRepository.findAll().forEach(e -> memberList.add(e));
        return memberList;
    }

    //멤버번호로 해당 멤버 정보를 찾아서 리턴
    public Optional<Member> findById(int memberNo){
        Optional<Member> member = memberRepository.findById(memberNo);
        return member;
    }

    //멤버번호로 해당 멤버 삭제, deleteBy... 메소드도 선언하지 않아도 사용가능
    public void deleteById(int memberNo){
        memberRepository.deleteById(memberNo);
    }

    //입력 받은 멤버객체 정보를 저장 (save 메소드 )
    public Member save(Member Member){
        memberRepository.save(Member);
        return Member;
    }

    //멤버정보 수정
    public void updateById(int memberNo, Member Member){
        //Optional은 해당 객체에 데이터가 있는지 없는지 확인
        //장점 : 예상하지 못한 NullPointerException을 간단히 회피할 수 있다.
        //      복잡한 조건문없이 .isPresent() 메소드로 확인가능
        Optional<Member> member = memberRepository.findById(memberNo);

        if(member.isPresent()){
            member.get().setMemberNo(Member.getMemberNo());
            member.get().setId(Member.getId());
            member.get().setName(Member.getName());
            memberRepository.save(Member);
        }
    }



}
