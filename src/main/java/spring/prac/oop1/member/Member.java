package spring.prac.oop1.member;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private Long id;
    private String name;
    private Grade grade;

    @Builder
    public Member(Long id, String name, Grade grade) {
        this.id = id;
        this.name = name;
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("[Member] id = %d, name = %s, grade = %s",
                id, name, grade.name());
    }
}
