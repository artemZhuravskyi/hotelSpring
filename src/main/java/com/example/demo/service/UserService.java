package com.example.demo.service;

import com.example.demo.DAO.UserRepository;
import com.example.demo.DTO.UserDTO;
import com.example.demo.exception.UserException;
import com.example.demo.model.User;
import com.example.demo.model.enums.Role;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

import static com.example.demo.messages.Messages.USER_ALREADY_EXISTS_MSG;

@AllArgsConstructor
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void register(UserDTO userDTO, Role role) throws UserException {

        if (userRepository.findUserByEmail(userDTO.getEmail())
                .isPresent()) {
            throw new UserException(
                    String.format(USER_ALREADY_EXISTS_MSG, userDTO.getEmail())
            );
        }

        User newUser = User.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
//                .state(User.State.ACTIVE)
                .role(role)
                .build();

        userRepository.save(newUser);
    }

    public void register(UserDTO userDTO) throws UserException{
        register(userDTO, Role.ROLE_USER);
    }

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email)
                .orElseThrow(NoSuchElementException::new);
    }
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<String> handleItemNotFoundException(NoSuchElementException e) {
        return new ResponseEntity<>("No Such Element Exceptions happen! Incorrect Data. ", HttpStatus.NOT_FOUND);
    }
}
