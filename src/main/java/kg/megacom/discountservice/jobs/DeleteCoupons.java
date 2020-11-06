package kg.megacom.discountservice.jobs;


import kg.megacom.discountservice.models.dto.UserDiscountDto;
import kg.megacom.discountservice.models.enums.DiscountStatus;
import kg.megacom.discountservice.services.UserDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeleteCoupons {

    @Autowired
    private UserDiscountService userDiscountService;


    @Scheduled(fixedRate = 120000)
    public void test(){

        List<UserDiscountDto> userDiscountListCreated = userDiscountService.getAllCreatedCoupons(DiscountStatus.CREATED);

        Date date = new Date();

        userDiscountListCreated.stream().peek(
                x->{
                    if (((date.getTime()-x.getAddDate().getTime())/6000.0)>=x.getDiscount().getLifeTime()){
                        userDiscountService.saveDiscount(x,DiscountStatus.CANCELLED);
                    }
                }
        ).collect(Collectors.toList());


        if (userDiscountListCreated!=null)
            userDiscountListCreated.clear();
    }
}
