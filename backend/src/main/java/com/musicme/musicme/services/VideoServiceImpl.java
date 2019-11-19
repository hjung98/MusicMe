package com.musicme.musicme.services;

import com.musicme.musicme.entities.Video;
import com.musicme.musicme.entities.VideoIdentity;
import com.musicme.musicme.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Qualifier("videoRepository")
    private VideoRepository videoRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public List<Video> listAll() {
        List<Video> videos = new ArrayList<Video>();
        videoRepository.findAll().forEach(videos::add);
        return videos;
    }

    @Override
    public List<Video> getByUser(String userEmail) {
        List<Video> videos = new ArrayList<Video>();
        videoRepository.findByVideoIdentityUserEmail(userEmail).forEach(videos::add);
        return videos;
    }

    @Override
    public Video saveOrUpdate(Video video) {
        videoRepository.save(video);
        return video;
    }

    @Override
    public void delete(VideoIdentity videoId) {
        videoRepository.deleteByVideoIdentity(videoId);
    }

}