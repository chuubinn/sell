package com.imooc.sell.repository;


import com.imooc.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

   @Autowired
   private  ProductCategoryRepository repository;

   @Test
   public void findByIdTest() {
       Optional<ProductCategory> productCategory = repository.findById(1);
       System.out.println(productCategory.toString());
   }
   @Test
   @Transactional
   /*public void saveTest() {
         ProductCategory productCategory = new ProductCategory();
         productCategory.setCategoryName("女生最爱");
         productCategory.setCategoryType(3);
         repository.save(productCategory);
       }
    */
   public void saveTest() {
       ProductCategory productCategory = new ProductCategory("女生最爱",3);

       ProductCategory result= repository.save(productCategory);

       Assert.assertNotNull(result);
     //  Assert.assertNotEquals(null,result);
   }
   @Test
   public void  findByCategoryTypeIn() {
       List<Integer> list = Arrays.asList(2,3);
       List<ProductCategory> result = repository.findByCategoryTypeIn(list);
       Assert.assertNotEquals(0,result.size());

   }

}