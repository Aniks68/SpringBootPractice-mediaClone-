package com.example.mediaclone.Repository;

import com.example.mediaclone.Models.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDetails, Long> {
    Optional<UserDetails> findByEmailAndPassword(String email, String password);

    Optional<UserDetails>  findFirstByEmail(String email);
}
