package aiss.videominer.controller;

import aiss.videominer.model.Caption;
import aiss.videominer.repository.CaptionRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return captionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caption", id));
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
        Caption existingCaption = captionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caption", id));

        existingCaption.setName(caption.getName());
        existingCaption.setLanguage(caption.getLanguage());

        return captionRepository.save(existingCaption);
    }

    // DELETE /videominer/captions/{id} - Eliminar un subtítulo
    @DeleteMapping("/captions/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCaption(@PathVariable String id) {
        if (!captionRepository.existsById(id)) {
            throw new ResourceNotFoundException("Caption", id);
        }
        captionRepository.deleteById(id);
    }
}