package kg.megacom.discountservice.controllers;

import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.UserDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/userDiscount")
public class UserDiscountController {

    @Autowired
    private UserDiscountService userDiscountService;

    @GetMapping("/getDiscount")
    public Response getDiscountOfUser(@RequestParam Long userId, @RequestParam Long discountId){
        return userDiscountService.getUserDiscount(userId,discountId);
    }
}
