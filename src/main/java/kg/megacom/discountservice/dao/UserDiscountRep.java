package kg.megacom.discountservice.dao;

import kg.megacom.discountservice.models.entity.Discount;
import kg.megacom.discountservice.models.entity.User;
import kg.megacom.discountservice.models.entity.UserDiscount;
import kg.megacom.discountservice.models.enums.DiscountStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDiscountRep extends JpaRepository<UserDiscount,Long> {

    UserDiscount findByIdIs(Long userDiscountId);

    UserDiscount findByUserAndDiscountAndDiscountStatus(User user, Discount discount, DiscountStatus discountStatus);

    int countAllByDiscountIdAndDiscountStatusIn(Long id,List<DiscountStatus> discountStatusList);

    int countAllByDiscountIdAndUserIdAndDiscountStatusAndAddDateAfter(Long discountId, Long userId, DiscountStatus discountStatus, Date startDate);

    List<UserDiscount> findAllByDiscountStatus(DiscountStatus discountStatus);

}
