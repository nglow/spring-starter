package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // Test 순서는 보장되지 않음
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);


        Member result = repository.findById(member.getId()).isEmpty()
                ? null
                : repository.findById(member.getId()).get(); // Optional 값을 get()으로 바로 꺼내는것은 좋은 방법이 아님
        assertThat(member).isEqualTo(result); // Static import
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").isEmpty()
                ? null
                : repository.findByName("spring1").get();

        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
