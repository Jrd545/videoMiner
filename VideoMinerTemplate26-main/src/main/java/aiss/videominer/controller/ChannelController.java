package aiss.videominer.controller;

import aiss.videominer.model.Channel;
import aiss.videominer.repository.ChannelRepository;
import aiss.videominer.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", id));
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
        Channel existingChannel = channelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Channel", id));

        existingChannel.setName(channel.getName());
        existingChannel.setDescription(channel.getDescription());
        existingChannel.setCreatedTime(channel.getCreatedTime());
        existingChannel.setVideos(channel.getVideos());

        return channelRepository.save(existingChannel);
    }

    // DELETE /videominer/channels/{id} - Eliminar un canal
    @DeleteMapping("/channels/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteChannel(@PathVariable String id) {
        if (!channelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Channel", id);
        }
        channelRepository.deleteById(id);
    }
}