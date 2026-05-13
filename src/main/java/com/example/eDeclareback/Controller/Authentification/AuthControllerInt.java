package com.example.eDeclareback.Controller.Authentification;

import com.example.eDeclareback.Entity.Server.ServerReponse;
import com.example.eDeclareback.Entity.Utilisateur.BasicAuthData;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/eDeclare/api/auth")
@CrossOrigin("*")
public interface AuthControllerInt {
    @GetMapping("/ping")
    ResponseEntity<String> ping();
    @PostMapping("/create")
    ResponseEntity<ServerReponse> createUser(@RequestParam("user") String user);
    @PostMapping("/login")
    ResponseEntity<BasicAuthData> loginUser(@RequestParam("auth") String user);

}
