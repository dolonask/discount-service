package kg.megacom.discountservice.controllers;

import kg.megacom.discountservice.models.appDto.PlaceAppDto;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/place")
public class PlaceController {

    @Autowired
    private PlaceService placeService;

    @PostMapping("/save")
    public Response savePlace(@RequestBody PlaceAppDto placeAppDto){
        return placeService.save(placeAppDto);
    }

    @PutMapping ("/update")
    public Response updatePlace(@RequestBody PlaceAppDto placeAppDto){
        return placeService.updatePlace(placeAppDto);
    }

    @DeleteMapping("/delete")
    public Response deletePlace(@RequestParam Long id){
    return placeService.deactivatePlace(id);
    }

    @GetMapping("/getByActivity")
    public Response getAllByActive(@RequestParam boolean active){
        return placeService.getAllByActive(active);
    }

}
