package by.tms.quizletclone.controller.rest;

import by.tms.quizletclone.dto.UserRegDTO;
import by.tms.quizletclone.service.restService.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "${api.path}" + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<?> userRegistration(@Valid @RequestBody UserRegDTO request){
//        RegistrationResponse response = authService.saveUser(request);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }

}
