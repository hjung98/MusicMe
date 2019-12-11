package com.musicme.musicme.services;

import com.musicme.musicme.entities.Likes;
import com.musicme.musicme.entities.LikesIdentity;
import com.musicme.musicme.exceptions.ResourceNotFoundException;
import com.musicme.musicme.repositories.LikesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Qualifier("userRepository")
    private LikesRepository likesRepository;

    @Autowired
    public LikesServiceImpl(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }

    @Override
    public List<Likes> listAll() {
        List<Likes> likes = new ArrayList<>();
        likesRepository.findAll().forEach(likes::add); //fun with Java 8
        return likes;
    }

    @Override
    public List<Likes> getByLikesIdentity(LikesIdentity likesIdentity) {
        List<Likes> likes = new ArrayList<>();
        likesRepository.findByLikeIdentity(likesIdentity).forEach(likes::add);
        return likes;
    }

    @Override
    public Likes saveOrUpdate(Likes likes) {
        likesRepository.save(likes);
        return likes;
    }

    @Override
    public void delete(LikesIdentity likesIdentity) {
        likesRepository.deleteById(likesIdentity);

    }

}
