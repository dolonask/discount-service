package kg.megacom.discountservice.services.impl;

import kg.megacom.discountservice.dao.DiscountRep;
import kg.megacom.discountservice.mappers.DiscountMapper;
import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.entity.Discount;
import kg.megacom.discountservice.models.responses.Response;
import kg.megacom.discountservice.services.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DiscountServiceImpl implements DiscountService {

    @Autowired
    private DiscountRep discountRep;

    @Override
    public Response save(DiscountDto discountDto) {

        Response response = Response.getResponse();

        Discount discount = DiscountMapper.INSTANCE.toDiscount(discountDto);

        if (discount.getId()==null) {
            Discount actualDiscount = discountRep.findByActiveAndPlace(true, discount.getPlace());
            if (actualDiscount != null) {
                actualDiscount.setActive(false);
                discountRep.save(actualDiscount);
            }
            discount = discountRep.save(discount);
        }else {
            discount =discountRep.save(discount);
        }
        response.setObject(discount);

        return response;
    }

    @Override
    public Response deactivateDiscount(Long id) {
        Response response = Response.getResponse();
        Discount discountForDeactivation  = discountRep.findByActiveIsTrueAndId(id);
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
        response.setObject(DiscountMapper.INSTANCE.toDiscountDtoList(discountList));
        return response;
    }
}
