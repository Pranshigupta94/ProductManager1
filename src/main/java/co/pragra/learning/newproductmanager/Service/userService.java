package co.pragra.learning.newproductmanager.Service;

import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.exception.InvalidUserDataException;
import co.pragra.learning.newproductmanager.repo.UserRepo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class userService {
    private final UserRepo repo;

    public userService(UserRepo repo) {
        this.repo = repo;
    }

    public User createUser(User user) {
        if( user==null ) {
            throw new InvalidUserDataException("User can't be null");
        }
        if(user.getFirstName()==null || user.getFirstName().isEmpty() ) {
            throw new InvalidUserDataException("First name can't be null or empty");
        }

        user.setUpdateDate(new Date());
        user.setCreatedDate(new Date());

return repo.save(user);


    }
    public User updateUser(User user) {
        if( user==null ) {
            throw new InvalidUserDataException("User can't be null");
        }

        Optional<User> userOptional = repo.findById(user.getId());
        userOptional.orElseThrow(() -> new InvalidUserDataException("User not found"));
        User dBUser = userOptional.get();
        dBUser.setFirstName(user.getFirstName());
        dBUser.setLastName(user.getLastName());
        dBUser.setEmail(user.getEmail());
        dBUser.setUpdateDate(new Date());
        return repo.save(dBUser);

    }
    public List<User> getAllByFilters(Optional<String> firstName,
                                      Optional<String> lastName) {
        if( firstName.isPresent() && lastName.isPresent() ) {
            return repo.findByFirstNameAndLastName(firstName.get(), lastName.get());
        }
        if( firstName.isPresent() ) {
            return repo.findByFirstName(firstName.get());
        }
        if( lastName.isPresent() ) {
            return repo.findByLastName(lastName.get());
        }
return repo.findAll();
    }

    public Optional<User> findById(Long id) {
        return this.repo.findById(id);
    }
}
