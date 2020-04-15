package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
//기본생성자 자동추가
@NoArgsConstructor
//@Entity: 테이블과 링크될 클래스임을 나타낸다. 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍으로 테이블 이름을 매칭한다. ex)SalesManager.java -> sales_manager table
@Entity
//이 Posts 클래스는 실제 DB테이블과 매칭될 클래스이며 Entity클래스라고도 한다. DB데이터에 작업할 경우 실제 쿼리를 날리기보다는 이 Entity클래스의 수정을 통해 작업한다.
public class Posts extends BaseTimeEntity {
    //@Id: 해당 테이블의 Primary Key 필드를 나타낸다
    @Id
    //@GeneratedValue: PK의 생성 규칙을 나타낸다. 스프링 부트2.0에서는 GenerationType.IDENTITY옵션을 추가해야만 auto_increment가 된다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column: 테이블의 칼럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다. 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.
    //문자열의 경우 VARCHAR(255)가 기본이지만 사이즈를 500으로 늘리고 싶거나 타입을 TEXT로 변경하고 싶거나 등의 경우에 사용된다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder //이 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 빌드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
