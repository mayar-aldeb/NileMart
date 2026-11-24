package org.route.nilemartspringboot.mapper.product;

import javax.annotation.processing.Generated;
import org.route.nilemartspringboot.dto.product.AddProductRequest;
import org.route.nilemartspringboot.dto.product.ProductResponse;
import org.route.nilemartspringboot.model.Product;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-11-24T18:01:02+0200",
    comments = "version: 1.6.3, compiler: javac, environment: Java 26.0.2 (Oracle Corporation)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductResponse toProductResponse(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductResponse productResponse = new ProductResponse();

        productResponse.setId( product.getId() );
        productResponse.setName( product.getName() );
        productResponse.setCategory( product.getCategory() );
        productResponse.setPrice( product.getPrice() );

        return productResponse;
    }

    @Override
    public Product toProduct(AddProductRequest request) {
        if ( request == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.name( request.getName() );
        product.category( request.getCategory() );
        product.price( request.getPrice() );
        product.inStock( request.getInStock() );
        if ( request.getActive() != null ) {
            product.active( String.valueOf( request.getActive() ) );
        }

        return product.build();
    }
}
