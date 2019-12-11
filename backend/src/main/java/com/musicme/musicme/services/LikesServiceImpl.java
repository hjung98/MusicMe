package com.musicme.musicme.services;

import com.musicme.musicme.entities.Likes;
import com.musicme.musicme.entities.LikesIdentity;
import com.musicme.musicme.entities.Video;
import com.musicme.musicme.entities.User;
import com.musicme.musicme.repositories.LikesRepository;
import com.musicme.musicme.repositories.VideoRepository;
import com.musicme.musicme.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;
import java.util.ArrayList;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Qualifier("likesRepository")
    private LikesRepository likesRepository;

    @Qualifier("videoRepository")
    VideoRepository videoRepository;

    @Qualifier("userRepository")
    UserRepository userRepository;

    @Autowired
    public LikesServiceImpl(LikesRepository likesRepository, VideoRepository videoRepository) {
        this.likesRepository = likesRepository;
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Likes> listAll() {
        List<Likes> likes = new ArrayList<>();
        likesRepository.findAll().forEach(likes::add);
        return likes;
    }

    @Override
    public List<Likes> getByVideo(String pathToVideo) {
        List<Likes> likes = new ArrayList<>();

        // Creating LikesId instance
        LikesIdentity onlyVideo = new LikesIdentity();

        // Setting only videos portion of composite key to obtain full list for said video
        Video video = this.videoRepository.findByPathToVideo(pathToVideo);
        onlyVideo.setVideo(video);

        // Creating template with Example.class and then grabbing all likes that match these credentials
        Likes example = new Likes();
        example.setLikesIdentity(onlyVideo);
        this.likesRepository.findAll(Example.of(example)).forEach(likes::add);
        return likes;
    }
    // @Override
    // public List<Likes> getByVideo(String pathToVideo) {
    //     List<Likes> likes = new ArrayList<>();
    //     // Creating LikesId instance
    //     LikesIdentity onlyVideo = new LikesIdentity();

    //     // Setting only videos portion of composite key to obtain full list for said video
    //     Video video = this.videoRepository.findByPathToVideo(pathToVideo);
    //     onlyVideo.setVideo(video);

    //     // Creating template with Example.class and then grabbing all likes that match these credentials
    //     // Likes example = new Likes();
    //     // example.setLikesIdentity(onlyVideo);

    //     this.likesRepository.findByLikesIdentity(Example.of(onlyVideo)).forEach(likes::add);
        
    //     return likes;
    // }

    @Override
    public List<Likes> getByUser(Long id) {
        List<Likes> likes = new ArrayList<>();

        // Creating LikesId instance
        LikesIdentity onlyUser = new LikesIdentity();

        // Setting only videos portion of composite key to obtain full list for said video
        User user = this.userRepository.findById(id).get();
        onlyUser.setUser(user);

        // Creating template with Example.class and then grabbing all likes that match these credentials
        Likes example = new Likes();
        example.setLikesIdentity(onlyUser);
        this.likesRepository.findAll(Example.of(example)).forEach(likes::add);
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
