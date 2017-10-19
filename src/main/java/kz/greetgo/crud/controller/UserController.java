package kz.greetgo.crud.controller;

import kz.greetgo.crud.entity.User;
import kz.greetgo.crud.entity.request.AddUserRequest;
import kz.greetgo.crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User create(@RequestBody AddUserRequest addUserRequest) {
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setSurname(addUserRequest.getSurname());
        return userRepository.save(user);
    }

    @RequestMapping(method=RequestMethod.GET, value="{id}")
    public User get(@PathVariable String id) {
        return userRepository.getOne(Long.parseLong(id));
    }

    @RequestMapping(method=RequestMethod.PUT, value="{id}")
    public User update(@PathVariable String id, @RequestBody AddUserRequest addUserRequest) {
        User user = userRepository.findOne(Long.parseLong(id));
        user.setName(addUserRequest.getName());
        user.setSurname(addUserRequest.getSurname());
        return userRepository.save(user);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public void delete(@PathVariable String id) {
        userRepository.delete(Long.parseLong(id));
    }
}
