package org.anhvt.springbootelk.service;

import org.anhvt.springbootelk.domain.User;
import org.anhvt.springbootelk.exception.ResourceNotFoundException;
import org.anhvt.springbootelk.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service layer is responsible for performing basic CRUD operations on {@link User} resource.
 *
 * @author trunganhvu
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Allows to retrieve all existing users from the database.
     * Pagination is not implemented in order to keep it simple.
     *
     * @return the all existing {@link User} entities
     */
    public List<User> getAllUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    /**
     * Allows to get a user by the given user identifier.
     *
     * @param userId the user id
     * @return the {@link User}
     */
    public User getUser(final Long userId) {
        return findUserOrThrowNotFoundException(userId);
    }

    /**
     * Allows to create a new user and persist the resource in the database.
     * Operation is transactional.
     * Operation cannot be executed if client specifies user identifier.
     *
     * @param user the {@link User} resource
     * @return newly created user identifier
     */
    @Transactional
    public Long createUser(final User user) {
        //persist new user and return user identifier
        return this.userRepository.save(user).getId();
    }

    /**
     * Allows to update specific user.
     * Operation is transactional.
     *
     * @param user the {@link User} resource
     */
    @Transactional
    public void updateUser(final User user) {
        //check that user exists
        findUserOrThrowNotFoundException(user.getId());
        //persist user
        this.userRepository.save(user);
    }

    /**
     * Allows to delete a user by the given user identifier.
     * Operation is transactional.
     *
     * @param userId user identifier
     */
    @Transactional
    public void deleteUser(final Long userId) {
        //check that user exists
        findUserOrThrowNotFoundException(userId);
        //delete existing user
        this.userRepository.deleteById(userId);
    }

    private User findUserOrThrowNotFoundException(final Long userId) {
        User user = this.userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new ResourceNotFoundException(String.format("User %s not found", userId));
        }
        return user;
    }

//    /**
//     * Sets user dao.
//     *
//     * @param userRepository the user dao
//     */
//    void setUserRepository(final UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
}

