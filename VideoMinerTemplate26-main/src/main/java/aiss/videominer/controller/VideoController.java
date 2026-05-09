package aiss.videominer.controller;

import aiss.videominer.model.Video;
import aiss.videominer.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        Optional<Video> video = videoRepository.findById(id);
        return video.orElse(null);
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
        Optional<Video> existingVideo = videoRepository.findById(id);
        if (existingVideo.isPresent()) {
            Video updated = existingVideo.get();
            updated.setName(video.getName());
            updated.setDescription(video.getDescription());
            updated.setReleaseTime(video.getReleaseTime());
            updated.setAuthor(video.getAuthor());
            updated.setComments(video.getComments());
            updated.setCaptions(video.getCaptions());
            return videoRepository.save(updated);
        }
        return null;
    }

    // DELETE /videominer/videos/{id} - Eliminar un video
    @DeleteMapping("/videos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteVideo(@PathVariable String id) {
        videoRepository.deleteById(id);
    }
}