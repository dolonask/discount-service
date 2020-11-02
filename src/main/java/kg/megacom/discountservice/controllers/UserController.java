package kg.megacom.discountservice.controllers;


import kg.megacom.discountservice.models.dto.UserDto;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public Response saveUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

    @PutMapping("/update")
    public Response updateUser(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }

}
