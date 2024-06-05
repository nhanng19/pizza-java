package com.example.demo.bootstrap;

import com.example.demo.domain.InhousePart;
import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.OutsourcedPartService;
import com.example.demo.service.OutsourcedPartServiceImpl;
import com.example.demo.service.ProductService;
import com.example.demo.service.ProductServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {

    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

       /*
        OutsourcedPart o= new OutsourcedPart();
        o.setCompanyName("Western Governors University");
        o.setName("out test");
        o.setInv(5);
        o.setPrice(20.0);
        o.setId(100L);
        outsourcedPartRepository.save(o);
        OutsourcedPart thePart=null;
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            if(part.getName().equals("out test"))thePart=part;
        }

        System.out.println(thePart.getCompanyName());
        */
        List<OutsourcedPart> outsourcedParts=(List<OutsourcedPart>) outsourcedPartRepository.findAll();
        for(OutsourcedPart part:outsourcedParts){
            System.out.println(part.getName()+" "+part.getCompanyName());
        }

        /*
        Product bicycle= new Product("bicycle",100.0,15);
        Product unicycle= new Product("unicycle",100.0,15);
        productRepository.save(bicycle);
        productRepository.save(unicycle);
        */

        if (partRepository.count() == 0) {
            InhousePart mushroom = new InhousePart();
            mushroom.setName("Mushroom");
            mushroom.setPrice(0.10);
            mushroom.setInv(10);

            InhousePart olive = new InhousePart();
            olive.setName("Olive");
            olive.setPrice(0.10);
            olive.setInv(10);

            InhousePart pineapple = new InhousePart();
            pineapple.setName("Pineapple");
            pineapple.setPrice(0.10);
            pineapple.setInv(10);

            InhousePart onion = new InhousePart();
            onion.setName("Onion");
            onion.setPrice(0.10);
            onion.setInv(10);

            InhousePart spinach = new InhousePart();
            spinach.setName("Spinach");
            spinach.setPrice(0.10);
            spinach.setInv(10);

            partRepository.save(mushroom);
            partRepository.save(olive);
            partRepository.save(pineapple);
            partRepository.save(onion);
            partRepository.save(spinach);
        }

        if (productRepository.count() == 0) {
            Product cheesePizza = new Product("Cheese Pizza", 7.99, 15);
            Product pepperoniPizza = new Product("Pepperoni Pizza", 9.99, 15);
            Product sausagePizza =  new Product("Sausage Pizza", 9.99, 15);
            Product steakPizza =  new Product("Steak Pizza", 10.99, 15);
            Product chickenPizza =  new Product("Chicken Pizza", 8.99, 15);

            productRepository.save(cheesePizza);
            productRepository.save(pepperoniPizza);
            productRepository.save(sausagePizza);
            productRepository.save(steakPizza);
            productRepository.save(chickenPizza);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
