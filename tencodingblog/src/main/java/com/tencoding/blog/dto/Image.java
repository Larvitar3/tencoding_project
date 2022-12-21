package com.tencoding.blog.dto;


import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String storyText;
	private String originFileName;
	private String postImageUrl;
	
	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;
	
	@CreatedDate
	private Timestamp timestamp;
	
	
}
