package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer")
public class CaptionController {

    @Autowired
    private CaptionRepository captionRepository;

    // GET /videominer/captions - Listar todos los subtítulos
    @GetMapping("/captions")
    public List<Caption> getAllCaptions() {
        return captionRepository.findAll();
    }

    // GET /videominer/captions/{id} - Obtener un subtítulo por ID
    @GetMapping("/captions/{id}")
    public Caption getCaptionById(@PathVariable String id) {
        Optional<Caption> caption = captionRepository.findById(id);
        return caption.orElse(null);
    }

    // POST /videominer/captions - Crear un nuevo subtítulo
    @PostMapping("/captions")
    @ResponseStatus(HttpStatus.CREATED)
    public Caption createCaption(@RequestBody Caption caption) {
        return captionRepository.save(caption);
    }

    // PUT /videominer/captions/{id} - Actualizar un subtítulo existente
    @PutMapping("/captions/{id}")
    public Caption updateCaption(@PathVariable String id, @RequestBody Caption caption) {
        Optional<Caption> existingCaption = captionRepository.findById(id);
        if (existingCaption.isPresent()) {
            Caption updated = existingCaption.get();
            updated.setName(caption.getName());
            updated.setLanguage(caption.getLanguage());
            return captionRepository.save(updated);
        }
        return null;
    }

    // DELETE /videominer/captions/{id} - Eliminar un subtítulo
    @DeleteMapping("/captions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaption(@PathVariable String id) {
        captionRepository.deleteById(id);
    }
}