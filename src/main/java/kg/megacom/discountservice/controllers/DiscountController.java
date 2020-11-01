package kg.megacom.discountservice.controllers;


import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/discount")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @PostMapping("/save")
    public Response saveDiscount(@Valid @RequestBody DiscountDto discountDto){
        return discountService.save(discountDto);
    }
    @PutMapping("/update")
    public Response updateDiscount(@Valid @RequestBody DiscountDto discountDto){
        return discountService.save(discountDto);
    }

    @DeleteMapping("/delete")
    public Response deleteDiscount(@RequestParam Long id){
        return discountService.deactivateDiscount(id);
    }
    @GetMapping("/getOne")
    public Response getOneDiscount(@RequestParam Long id){
        return  discountService.getDiscount(id);
    }

    @GetMapping("/getByActivity")
    public Response getAllDiscountsByActive(@RequestParam boolean active){
        return discountService.getAllByActivity(active);
    }
}
