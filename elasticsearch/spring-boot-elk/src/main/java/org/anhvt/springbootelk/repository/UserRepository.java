package org.anhvt.springbootelk.repository;

import org.anhvt.springbootelk.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * An interface declaration in order to perform CRUD operations for {@link User} resource
 *
 * @author trunganhvu
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
