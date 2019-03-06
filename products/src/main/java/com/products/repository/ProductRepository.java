
package com.products.repository;
import com.products.model.Brand;
import com.products.model.Product;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author sgtomar
 * 
 * ProductRepository will manage all database related operation for Product
 * 
 */

 @Repository
public interface ProductRepository extends CrudRepository<Product,Integer> {

     @Query("select NEW com.products.model.Product(p.id,p.name,p.price,p.brand,p.onSale) from com.products.model.Product p group by p.id,p.brand order by p.brand ,p.price")
     List<Product> findProductByBrand();

}
