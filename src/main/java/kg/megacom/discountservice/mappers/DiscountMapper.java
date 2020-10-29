package kg.megacom.discountservice.mappers;

import kg.megacom.discountservice.models.dto.DiscountDto;
import kg.megacom.discountservice.models.entity.Discount;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DiscountMapper {

    DiscountMapper INSTANCE = Mappers.getMapper(DiscountMapper.class);

    Discount toDiscount(DiscountDto discountDto);

    DiscountDto toDiscountDto(Discount discount);

    List<DiscountDto> toDiscountDtoList(List<Discount> discountList);
}
