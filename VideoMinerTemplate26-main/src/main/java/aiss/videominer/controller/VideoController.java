package aiss.videominer.controller;

import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videominer")
public class VideoController {

    @Autowired
    private VideoRepository videoRepository;

    // GET /videominer/videos - Listar todos los videos
    @GetMapping("/videos")
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    // GET /videominer/videos/{id} - Obtener un video por ID
    @GetMapping("/videos/{id}")
    public Video getVideoById(@PathVariable String id) {
        return videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video", id));
    }

    // POST /videominer/videos - Crear un nuevo video
    @PostMapping("/videos")
    @ResponseStatus(HttpStatus.CREATED)
    public Video createVideo(@RequestBody Video video) {
        return videoRepository.save(video);
    }

    // PUT /videominer/videos/{id} - Actualizar un video existente
    @PutMapping("/videos/{id}")
    public Video updateVideo(@PathVariable String id, @RequestBody Video video) {
        Video existingVideo = videoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Video", id));

        existingVideo.setName(video.getName());
        existingVideo.setDescription(video.getDescription());
        existingVideo.setReleaseTime(video.getReleaseTime());
        existingVideo.setAuthor(video.getAuthor());
        existingVideo.setComments(video.getComments());
        existingVideo.setCaptions(video.getCaptions());

        return videoRepository.save(existingVideo);
    }

    // DELETE /videominer/videos/{id} - Eliminar un video
    @DeleteMapping("/videos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo(@PathVariable String id) {
        if (!videoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Video", id);
        }
        videoRepository.deleteById(id);
    }
}