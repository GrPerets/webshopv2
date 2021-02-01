package com.grperets.webshopv2.controller;

import com.grperets.webshopv2.dto.ManagerDTO;
import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Product;
import com.grperets.webshopv2.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerRestControllerV1 {

    private final ManagerService managerService;

    @Autowired
    public ManagerRestControllerV1(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> getManager(@PathVariable("id") Long id){
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Manager manager = managerService.getById(id);
        if (manager == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ManagerDTO managerDTO = ManagerDTO.fromManager(manager);
        return new
                ResponseEntity<>(managerDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> saveManager(@RequestBody ManagerDTO managerDTO){
        if(managerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.managerService.save(managerDTO.toManager());
        return new ResponseEntity<>(managerDTO, HttpStatus.CREATED);

    }
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> updateManager(@RequestBody ManagerDTO managerDTO){
        if (managerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        this.managerService.save(managerDTO.toManager());
        return new ResponseEntity<>(managerDTO, HttpStatus.OK);

    }
    @RequestMapping(value = ("{id}"), method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manager> deleteManager(@PathVariable("id") Long id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Manager manager = this.managerService.getById(id);
        if (manager == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.managerService.delete(manager);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDTO>> getAllManager(){
        List<Manager> managers = this.managerService.getAll();

        if (managers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ManagerDTO> managerDTOS = new ArrayList<>();
        for (Manager manager: managers){
            managerDTOS.add(ManagerDTO.fromManager(manager));
        }

        return new ResponseEntity<>(managerDTOS, HttpStatus.OK);
    }

}
