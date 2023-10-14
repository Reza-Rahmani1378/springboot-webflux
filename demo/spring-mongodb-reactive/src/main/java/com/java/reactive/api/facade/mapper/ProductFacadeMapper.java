package com.java.reactive.api.facade.mapper;

import com.java.reactive.api.dto.ProductInputModel;
import com.java.reactive.dal.model.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductFacadeMapper {

    ProductInputModel getProductInputModelFromEntity(Product product);

    Product getProductFromModel(ProductInputModel model);
}
