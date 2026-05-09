package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videominer")
public class ChannelController {

    @Autowired
    private ChannelRepository channelRepository;

    // GET /videominer/channels - Listar todos los canales
    @GetMapping("/channels")
    public List<Channel> getAllChannels() {
        return channelRepository.findAll();
    }

    // GET /videominer/channels/{id} - Obtener un canal por ID
    @GetMapping("/channels/{id}")
    public Channel getChannelById(@PathVariable String id) {
        Optional<Channel> channel = channelRepository.findById(id);
        return channel.orElse(null);
    }

    // POST /videominer/channels - Crear un nuevo canal
    @PostMapping("/channels")
    @ResponseStatus(HttpStatus.CREATED)
    public Channel createChannel(@RequestBody Channel channel) {
        return channelRepository.save(channel);
    }

    // PUT /videominer/channels/{id} - Actualizar un canal existente
    @PutMapping("/channels/{id}")
    public Channel updateChannel(@PathVariable String id, @RequestBody Channel channel) {
        Optional<Channel> existingChannel = channelRepository.findById(id);
        if (existingChannel.isPresent()) {
            Channel updated = existingChannel.get();
            updated.setName(channel.getName());
            updated.setDescription(channel.getDescription());
            updated.setCreatedTime(channel.getCreatedTime());
            updated.setVideos(channel.getVideos());
            return channelRepository.save(updated);
        }
        return null;
    }

    // DELETE /videominer/channels/{id} - Eliminar un canal
    @DeleteMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChannel(@PathVariable String id) {
        channelRepository.deleteById(id);
    }
}