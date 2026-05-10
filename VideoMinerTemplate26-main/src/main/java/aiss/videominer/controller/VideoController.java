package aiss.videominer.controller;

import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videominer")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    @GetMapping("/videos")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @GetMapping("/videos/{id}")
    public Video getVideoById(@PathVariable String id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video", id));
    }
}