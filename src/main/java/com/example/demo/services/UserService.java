package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repositories.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Create a new user
     * @param userDTO the data to create the user
     */
    public void createUser(UserDTO userDTO) {
        validateUserDTO(userDTO);

        User user = new User();
        user.setIdentificador(userDTO.getIdentificador());
        user.setValor(userDTO.getValor());
        user.setNombreContratante(userDTO.getNombreContratante());
        user.setDocumentoContratante(userDTO.getDocumentoContratante());
        user.setNombreContratantista(userDTO.getNombreContratantista());
        user.setDocumentoContratantista(userDTO.getDocumentoContratantista());
        user.setFechaInicial(userDTO.getFechaInicial());
        user.setFechaFinal(userDTO.getFechaFinal());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new UserNotFoundException("Error creating user", e);
        }
    }

    /**
     * Get a user by ID
     * @param userId the ID of the user
     * @return UserDTO with the user data
     */
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        return mapToDTO(user);
    }

    /**
     * Get all users
     * @return List<UserDTO> with all users
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Update a user
     * @param userId the ID of the user to update
     * @param userDTO the new data
     */
    public void updateUser(Long userId, UserDTO userDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.setIdentificador(userDTO.getIdentificador());
        user.setValor(userDTO.getValor());
        user.setNombreContratante(userDTO.getNombreContratante());
        user.setDocumentoContratante(userDTO.getDocumentoContratante());
        user.setNombreContratantista(userDTO.getNombreContratantista());
        user.setDocumentoContratantista(userDTO.getDocumentoContratantista());
        user.setFechaInicial(userDTO.getFechaInicial());
        user.setFechaFinal(userDTO.getFechaFinal());

        userRepository.save(user);
    }

    /**
     * Delete a user
     * @param userId the ID of the user to delete
     */
    public void deleteUser(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
        userRepository.deleteById(userId);
    }

    /**
     * Validate the user data
     * @param userDTO the data to validate
     */
    private void validateUserDTO(UserDTO userDTO) {
        if (!StringUtils.hasText(userDTO.getIdentificador())) {
            throw new IllegalArgumentException("Identificador is required");
        }
        if (userDTO.getValor() == null) {
            throw new IllegalArgumentException("Valor is required");
        }
        if (!StringUtils.hasText(userDTO.getNombreContratante())) {
            throw new IllegalArgumentException("Nombre Contratante is required");
        }
        if (!StringUtils.hasText(userDTO.getDocumentoContratante())) {
            throw new IllegalArgumentException("Documento Contratante is required");
        }
        if (!StringUtils.hasText(userDTO.getNombreContratantista())) {
            throw new IllegalArgumentException("Nombre Contratantista is required");
        }
        if (!StringUtils.hasText(userDTO.getDocumentoContratantista())) {
            throw new IllegalArgumentException("Documento Contratantista is required");
        }
        if (userDTO.getFechaInicial() == null) {
            throw new IllegalArgumentException("Fecha Inicial is required");
        }
        if (userDTO.getFechaFinal() == null) {
            throw new IllegalArgumentException("Fecha Final is required");
        }
    }

    /**
     * Map User entity to UserDTO
     * @param user the user entity
     * @return the user DTO
     */
    private UserDTO mapToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setIdentificador(user.getIdentificador());
        userDTO.setValor(user.getValor());
        userDTO.setNombreContratante(user.getNombreContratante());
        userDTO.setDocumentoContratante(user.getDocumentoContratante());
        userDTO.setNombreContratantista(user.getNombreContratantista());
        userDTO.setDocumentoContratantista(user.getDocumentoContratantista());
        userDTO.setFechaInicial(user.getFechaInicial());
        userDTO.setFechaFinal(user.getFechaFinal());
        return userDTO;
    }
}