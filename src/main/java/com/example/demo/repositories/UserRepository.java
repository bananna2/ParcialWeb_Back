package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.UserDTO;
import com.example.demo.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Buscar un usuario por email
    User findByEmail(String email);

    // Verificar si existe un usuario con el email dado
    boolean existsByEmail(String email);

    // Obtener solo el email de un usuario por su ID
    @Query("SELECT u.email FROM User u WHERE u.id = :userId")
    String findEmailById(@Param("userId") Long userId);

    // Obtener un UserDTO basado en el email
    @Query("SELECT new com.gossip.arrienda_tu_finca.dto.UserDTO(u.email, u.name, u.surname, u.password, u.phone, u.isHost, u.isRenter) FROM User u WHERE u.email = :email")
    UserDTO findUserDTOByEmail(@Param("email") String email);

    // Obtener el ID de un usuario basado en su email
    @Query("SELECT u.id FROM User u WHERE u.email = :email")
    Long findIdByEmail(@Param("email") String email);

    // Obtener un UserDTO basado en el ID del usuario
    @Query("SELECT new com.gossip.arrienda_tu_finca.dto.UserDTO(u.email, u.name, u.surname, u.password, u.phone, u.isHost, u.isRenter) FROM User u WHERE u.id = :userId")
    UserDTO findUserDTOById(@Param("userId") Long userId);

    // Buscar un usuario por identificador
    User findByIdentificador(String identificador);

    // Verificar si existe un usuario con el identificador dado
    boolean existsByIdentificador(String identificador);
}