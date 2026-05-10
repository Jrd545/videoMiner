package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videominer")
public class CaptionController {

    @Autowired
    private CaptionRepository captionRepository;

    @GetMapping("/captions")
    public List<Caption> getAllCaptions() {
        return captionRepository.findAll();
    }

    @GetMapping("/captions/{id}")
    public Caption getCaptionById(@PathVariable String id) {
        return captionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caption", id));
    }
}