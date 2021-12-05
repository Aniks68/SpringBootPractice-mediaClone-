package com.example.mediaclone.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name= "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    @CreatedDate
    @Column(name = "dateCreated", nullable = false, updatable = false)
    private LocalDateTime dateCreated = LocalDateTime.now();

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserDetails user;
    private String editNotice;
//
//    public int compareTo(Post otherPost) {
//
//        if(getId() < otherPost.getId()){
//            return -1;
//        }
//        else if(getId() > otherPost.getId()){
//            return 1;
//        }
//        return 0;
//    }
}
