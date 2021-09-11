package com.learning.demo.controller;

import com.learning.demo.exceptions.ResourceNotFoundException;
import com.learning.demo.model.Room;
import com.learning.demo.reposiory.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1")
public class RoomController {

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public List<Room> getAllRooms(){
        return roomRepository.findAll();
    }
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoomById(@PathVariable long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Room "+ id + " not found"));
        return ResponseEntity.ok().body(room);

    }

    @PostMapping("/rooms")
    public Room createRoom (@Validated @RequestBody Room room){
        return roomRepository.save(room);
    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @Validated @RequestBody Room roomDetails) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Room not found for this id: " + id));
        roomDetails.setId(id);
        roomRepository.save(roomDetails);
        final Room updateRoom = roomRepository.save(room);
        return ResponseEntity.ok(updateRoom);

    }

    @DeleteMapping("/rooms/{id}")
    public Map<String,Boolean> deleteRoom(@PathVariable Long id) throws ResourceNotFoundException {
        Room room = roomRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Room not found for this id:"+ id));

        roomRepository.delete(room);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }
}

