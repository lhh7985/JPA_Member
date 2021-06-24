package com.ho.hwang.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Integer> {

    public List<MemberVo> findById(String id);

    public List<MemberVo> findByName(String name);

    public List<MemberVo> findByNameLike(String keyword);
}
