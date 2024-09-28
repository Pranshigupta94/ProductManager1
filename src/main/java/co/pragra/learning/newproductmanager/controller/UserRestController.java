package co.pragra.learning.newproductmanager.controller;

import co.pragra.learning.newproductmanager.Service.userService;
import co.pragra.learning.newproductmanager.entity.User;
import co.pragra.learning.newproductmanager.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class UserRestController {
    private final userService service;



@PostMapping("/api/user") //it is for used post method
    public User createUser(@RequestBody User user) { // JSON will come from request body
   /*  user.setCreatedDate(new Date());
     user.setUpdateDate(new Date());*/
      return this.service.createUser(user); //save give created record

    }
    @GetMapping("/api/user")
    public List<User> getUsers(@RequestParam(value = "firstName", required = false) Optional<String> firstName,
                               @RequestParam(value = "lastName", required = false) Optional<String> lastName ) {
    return this.service.getAllByFilters(firstName, lastName);

    }
    @GetMapping("/api/user/{id}")
    public Optional<User> findById(@PathVariable("id") Long id) {
    return this.service.findById(id);
    }

}
