package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.DiscountRep;
import kg.megacom.discountservice.mappers.DiscountMapper;
import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.entity.Discount;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRep discountRep;

    @Override
    public Response save(DiscountDto discountDto) {

        Response response = Response.getResponse();

        Discount newDiscount = DiscountMapper.INSTANCE.toDiscount(discountDto);

      List<Discount> currDiscounts = discountRep.findAllByActiveIsTrueAndPlace(newDiscount.getPlace());

      if (!currDiscounts.isEmpty()) {
          currDiscounts.stream()
                  .filter(x -> (discountDto.getStartDate().after(x.getStartDate()) && discountDto.getStartDate().before(x.getEndDate()))
                          || (discountDto.getEndDate().after(x.getStartDate()) && discountDto.getEndDate().before(x.getEndDate()))
                          || (x.getStartDate().after(discountDto.getStartDate()) && x.getStartDate().before(discountDto.getEndDate()))
                          || (x.getEndDate().after(discountDto.getStartDate()) && x.getEndDate().before(discountDto.getEndDate())))
                  .peek(x -> {
                      x.setActive(false);
                      discountRep.save(x);
                  })
                  .collect(Collectors.toList());
      }
      newDiscount = discountRep.save(newDiscount);
            response.setObject(DiscountMapper.INSTANCE.toDiscountDto(newDiscount));

        return response;
    }



    @Override
    public Response deactivateDiscount(Long id) {
        Response response = Response.getResponse();
        Discount discountForDeactivation  = discountRep.findByActiveIsTrueAndId(id);
        if (discountForDeactivation == null){
            response.setStatus(404);
            response.setMessage("Скидка отсутствует");
            return response;
        }
        discountForDeactivation.setActive(false);
        discountForDeactivation = discountRep.save(discountForDeactivation);
        response.setObject(DiscountMapper.INSTANCE.toDiscountDto(discountForDeactivation));
        return response;
    }

    @Override
    public Response getDiscount(Long id) {
        Response response = Response.getResponse();
        Discount discount = discountRep.findById(id).orElse(null);
        if (discount==null) {
            response.setStatus(404);
            response.setMessage("Скидка отсутствует");
            return response;
        }
        response.setObject(DiscountMapper.INSTANCE.toDiscountDto(discount));
        return response;
    }

    @Override
    public Response getAllByActivity(boolean active) {
        Response response = Response.getResponse();
        List<Discount> discountList = discountRep.findAllByActive(active);
        if (discountList.isEmpty()){
            response.setStatus(0);
            response.setMessage("На данный момент нет скидок");
        }
        response.setObject(DiscountMapper.INSTANCE.toDiscountDtoList(discountList));
        return response;
    }

    @Override
    public DiscountDto getDiscountById(Long discountId) {
        Discount discount = discountRep.findById(discountId).orElse(null);
        return DiscountMapper.INSTANCE.toDiscountDto(discount);
    }
}
