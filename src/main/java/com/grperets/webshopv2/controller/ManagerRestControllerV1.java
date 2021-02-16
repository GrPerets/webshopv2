package com.grperets.webshopv2.controller;

import com.grperets.webshopv2.dto.ManagerDTO;
import com.grperets.webshopv2.exception.ServiceException;
import com.grperets.webshopv2.model.Manager;
import com.grperets.webshopv2.model.Role;
import com.grperets.webshopv2.service.ManagerService;
import com.grperets.webshopv2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/managers")
public class ManagerRestControllerV1 {

    private final ManagerService managerService;
    private final RoleService roleService;

    @Autowired
    public ManagerRestControllerV1(ManagerService managerService, RoleService roleService) {
        this.managerService = managerService;
        this.roleService = roleService;
    }


    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> getManager(@PathVariable("id") Long id){
        if(id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Manager manager = managerService.getManagerById(id);
        if (manager == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ManagerDTO managerDTO = ManagerDTO.fromManager(manager);
        return new
                ResponseEntity<>(managerDTO, HttpStatus.OK);
    }
    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> createManager(@RequestBody @Valid ManagerDTO managerDTO){
        if(managerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (this.managerService.createManager(managerDTO.toManager())){
            return new ResponseEntity<>(managerDTO, HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(value = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ManagerDTO> updateManager(@RequestBody @Valid ManagerDTO managerDTO){
        if (managerDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (this.managerService.updateManager(managerDTO.toManager())){
            return new ResponseEntity<>(managerDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }
    @RequestMapping(value = ("{id}"), method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Manager> deleteManager(@PathVariable("id") Long id){
        if (this.managerService.deleteManager(id)){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ManagerDTO>> getAllManagers(){
        List<Manager> managers = this.managerService.getAllManagers();

        if (managers.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ManagerDTO> managerDTOS = managers.stream().map(manager -> ManagerDTO.fromManager(manager)).collect(Collectors.toList());

        return new ResponseEntity<>(managerDTOS, HttpStatus.OK);
    }

}
