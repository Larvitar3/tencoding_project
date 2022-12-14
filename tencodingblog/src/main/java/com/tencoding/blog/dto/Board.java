package com.tencoding.blog.dto;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = false, length = 100)
	private String title;
	
	@Lob // 대용량 데이터 선언
	private String content;
	
	@ColumnDefault("0") // ' 홑따옴표가 없기때문에 int로 인식한다.
	private int count;
	
	@ManyToOne(fetch = FetchType.EAGER) // RDBMS 만들기 (FK)
	@JoinColumn(name = "userId") // 컬럼명 직접 지정
	private User user;
	
	// 연관관계의 주인이 아님. * select 할 때 가지고 와야하는 데이터
	@OneToMany(mappedBy = "board", fetch = FetchType.EAGER)
	// mappedBy : 해당 연관 관계의 주인과 연결
	private List<Reply> reply;
	// 댓글 가져오기. 여러개 일 수도 있기 때문에 List 형식
	// 오브젝트를 다룰 때 가지고 올 수 있고 요청해야함 (mappedMy)
	
	@CreationTimestamp
	private Timestamp createDate;

}
