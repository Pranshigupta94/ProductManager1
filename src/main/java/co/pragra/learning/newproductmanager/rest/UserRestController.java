package co.pragra.learning.newproductmanager.rest;

import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController

public class UserRestController {
    private UserRepo repo;

    public UserRestController(UserRepo repo) {
        this.repo = repo;
    }

@PostMapping("/api/user") //it is for used post method
    public User createUser(@RequestBody User user) { // JSON will come from request body
     user.setCreatedDate(new Date());
     user.setUpdateDate(new Date());
      return this.repo.save(user); //save give created record

    }
    @GetMapping("/api/user")
    public List<User> getAllUsers() {
        return this.repo.findAll();
    }
}
