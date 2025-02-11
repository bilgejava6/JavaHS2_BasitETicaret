package com.muhammet.mapper;

import com.muhammet.dto.request.AddUrunRequestDto;
import com.muhammet.entity.Urun;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct -> İki java sınıfının bir birilerine nesensel olarak alanları
 * ile aktarılmasını sağlar.
 * Propslarını dikkatli olarak ayarlayınız.
 * componentModel -> bir interface in nasıl IMPL sınıfının yaratılması gerektiğini
 * belirlediğimiz bir özelliktir. Spring için kullanıyor olduğumuzdan buraya
 * "spring" değerini vereceğiz.
 * Urun fromAddUrunRequestTto(dto); böyle bir yapıda, dto içersindeki veriler
 * urun sınıfını içerisindeki alanlara akatarılır. Bu işlemde tam eşleşme aranır,
 * eğer olurda eşleşmeyen alanlar olursa hata fırlatır. Bunun olmamasını sağlamak için
 * eşleşmeyen alanları görmezden gel diyebiliriz.
 *
 */
@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface UrunMapper {
    /**
     * Bu interface in bir nesnesine ihtiyacımız var bu nedenle bir kalıp olarak şu kodlama yapılır
     */
    UrunMapper INSTANCE = Mappers.getMapper(UrunMapper.class);

    /**
     * Dönüşümünü istediğimiz sınıflar için methodlar oluşturuyoruz.
     */
    Urun eyMapperDtoDanAldiklarinIleBanaUrunVer(AddUrunRequestDto dto);
}
