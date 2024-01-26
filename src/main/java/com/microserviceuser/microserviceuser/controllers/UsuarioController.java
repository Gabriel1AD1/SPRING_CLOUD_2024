package com.microserviceuser.microserviceuser.controllers;

import com.microserviceuser.microserviceuser.models.entitys.User;
import com.microserviceuser.microserviceuser.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Handler Controller
/*
* Use Controller directorions Request
*
* */
@RestController
public class UsuarioController {
    @Autowired
    private UserService service;

    //List of user
    @GetMapping
    public List<User> listar(){
        return service.listAll();
    }

    //PathVariable ID by handler
    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable(name = "id") Long id){
        //Optional was created for store user o null
        Optional<User> userOptional = service.findById(id);
        //Valitation yes UserOptional isPresent o not present
        //Yes present returun Response Entity.ok(USerOPtional.get) g
        //get User by id
        if(userOptional.isPresent()){
            return ResponseEntity.ok(userOptional.get());
        }
        //Else retorun NoFound in application web
        return ResponseEntity.notFound().build();
    }

    //Create user for save in database
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }


    //Update User
    //Params ID , OBJECT USER

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@RequestBody User user, @PathVariable Long id){
        //VALITATION YES Service in contain user of the id
        Optional<User> userById = service.findById(id);

        //Yes user is present Update User
        if(userById.isPresent()){
            User userDB = userById.get();

            userDB.setName(user.getName());

            userDB.setEmail(user.getEmail());

            userDB.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(userDB));
        }
        return ResponseEntity.notFound().build();
    }

    //Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        Optional<User> userOptional = service.findById(id);
        if(userOptional.isPresent()){
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
