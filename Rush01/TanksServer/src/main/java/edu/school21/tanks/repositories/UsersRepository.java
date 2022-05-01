package edu.school21.tanks.repositories;



import edu.school21.tanks.models.User;

import java.util.Optional;

public interface UsersRepository<T extends User> extends CrudRepository<T>{

    Optional<T> findByUserName(String userName);

    int getRecordsCount();

}