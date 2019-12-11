package com.musicme.musicme.repositories;

import com.musicme.musicme.entities.Likes;
import com.musicme.musicme.entities.LikesIdentity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface LikesRepository extends JpaRepository<Likes, LikesIdentity> {

    List<Likes> findByLikeIdentity(LikesIdentity likesIdentity);

    void deleteByLikesIdentity(LikesIdentity likesIdentity);

}
