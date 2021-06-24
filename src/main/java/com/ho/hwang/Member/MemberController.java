package com.ho.hwang.Member;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final MemberService memberService;

    //모든 회원 조회
    @GetMapping
    public ResponseEntity<?> getAllMember(){
        List<MemberVo> memberList = memberService.findAll();
        if(memberList.size() >0){
            return new ResponseEntity<>(memberList, HttpStatus.OK);
        }else
            return new ResponseEntity<>("FAIL",HttpStatus.NO_CONTENT);
    }

    //멤버번호로 회원 조회
    @GetMapping("/{memberNo}")
    public ResponseEntity<?> getMember(@PathVariable("memberNo") int memberNo){
        Optional<MemberVo> member = memberService.findById(memberNo);
        //member가 null이 아닌지 체크
        if(member.isPresent()){
            return new ResponseEntity<>(member.get(), HttpStatus.OK);
        }else
            return new ResponseEntity<>("FAIL", HttpStatus.NO_CONTENT);
    }

    //멤버 등록
    @PostMapping
    public ResponseEntity<?> save(@RequestBody MemberVo memberVo){
        return new ResponseEntity<>(memberService.save(memberVo), HttpStatus.OK);
    }

    //멤버 번호로 정보 수정
    @PutMapping("/{memberNo}")
    public ResponseEntity<?> modifyMember(@PathVariable("memberNo") int memberNo, @RequestBody MemberVo memberVo){
        memberService.updateById(memberNo, memberVo);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //멤버번호로 멤버 삭제
    @DeleteMapping("/{memberNo}")
    public ResponseEntity<?> removeMember(@PathVariable("memberNo") int memberNo){
        memberService.deleteById(memberNo);
        //회원이 삭제되면 리턴할 데이터가 없기 때문에 NO_CONTENT 상태를 리턴
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }



}
