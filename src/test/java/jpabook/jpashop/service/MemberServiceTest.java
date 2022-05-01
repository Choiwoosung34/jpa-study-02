package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepositoryOld;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    protected MemberService memberService;

    @Autowired
    protected MemberRepositoryOld memberRepository;

    @Test
    void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kim");

        memberService.join(member);

        assertEquals(member, memberRepository.findOne(member.getId()));
    }

    @Test
    void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim1");

        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

}