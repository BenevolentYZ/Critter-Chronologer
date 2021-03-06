package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.data.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    PetService petService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        //throw new UnsupportedOperationException();
        return petService.save(petDTO);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        //throw new UnsupportedOperationException();
        return petService.getOne(petId);
    }

    @GetMapping
    public List<PetDTO> getPets(){
        //throw new UnsupportedOperationException();
        return petService.getPets();
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        //throw new UnsupportedOperationException();
        return petService.getPetsByOwner(ownerId);
    }
}
