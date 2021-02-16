package com.grperets.webshopv2.controller;

import com.grperets.webshopv2.dto.AuthRequestDTO;
import com.grperets.webshopv2.model.BaseUser;
import com.grperets.webshopv2.security.AuthUserDetails;
import com.grperets.webshopv2.security.AuthUserDetailsFactory;
import com.grperets.webshopv2.security.jwt.JwtAuthenticationFilter;
import com.grperets.webshopv2.security.jwt.JwtUtil;
import com.grperets.webshopv2.service.CustomerService;
import com.grperets.webshopv2.service.ManagerService;
import com.grperets.webshopv2.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthRestControllerV1 {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;


    @Autowired
    public AuthRestControllerV1(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @RequestMapping(value = "login", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthRequestDTO> getLoginForm(){
        AuthRequestDTO authRequestDTO = new AuthRequestDTO();
        return new ResponseEntity<>(authRequestDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO authRequestDTO){
        String username = authRequestDTO.getUsername();
        UserDetails userDetails = (AuthUserDetails) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, authRequestDTO.getPassword())).getPrincipal();
        String token = this.jwtUtil.generateToken(userDetails);


        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}
