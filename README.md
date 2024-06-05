
# WESTERN GOVERNOR UNIVERSITY

### Nhan Nguyen
### D287 - Java Frameworks PA

### C.  Customize the HTML user interface for your customer’s application. The user interface should include the shop name, the product names, and the names of the parts.

<pre>
    UPDATE - mainscreen.html
        Line 14 - &lt;title&gt; My Bicycle Shop &lt;title&gt;
        TO
        Line 14 - &lt;title&gt; Nhan's Incredible Pizza Company &lt;/title&gt;
        
        Line 19 - &lt;h1&gt;Shop &lt;/h1&gt;
        TO
        Line 19 - &lt;h1&gt;Buy Pizzas  &lt;/h1&gt;

        Line 21 - &lt;h2&gt;Parts &lt;/h2&gt;
        TO
        Line 21 - &lt;h2&gt;Toppings  &lt;/h2&gt;    

        Line 53 - &lt;h2&gt;Products &lt;/h2&gt;
        TO
        Line 53 - &lt;h2&gt;Pizzas  &lt;/h2&gt;
</pre>

### D.  Add an “About” page to the application to describe your chosen customer’s company to web viewers and include navigation to and from the “About” page and the main screen.

<pre>
    CREATE - about.html
    &lt;!DOCTYPE html&gt;
    &lt;html lang="en"&gt;
    &lt;head&gt;
        &lt;meta charset="UTF-8"&gt;    <!-- Bootstrap CSS -->
        &lt;link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"&gt;
        &lt;title&gt;About Us&lt;/title&gt;
    &lt;/head&gt;

    &lt;body&gt;
    &lt;div class="container"&gt;
        &lt;h1&gt;About Us&lt;/h1&gt;
        &lt;hr/&gt;
        &lt;p&gt;Welcome to Nhan's Incredible Pizza Company, where fun and joy exists for the whole family! WE'RE MORE THAN A PIZZA PLACE. We're food and fun under one roof. Pepperoni. . Salad. You name it, we got it. Dig into your favorite dishes made fresh daily!
        &lt;/p&gt;
        &lt;a href="http://localhost:8080/"&gt;Link to Main Screen&lt;/a&gt;
    &lt;/div&gt;
    &lt;/body&gt;
    &lt;/html&gt;

    INSERT - MainScreenControllerr.java, Line 55 - 58
    @GetMapping("/about")
    public String about() {
        return "about";
    }

    INSERT - mainscreen.html, Line 19
    &lt;div class="d-flex justify-content-between align-items-center"&gt;
        &lt;h1&gt;Buy Pizzas&lt;/h1&gt;
        &lt;a th:href="@{/about}"&gt;About Us&lt;/a&gt;
    &lt;/div&gt;
</pre>

### E.  Add a sample inventory appropriate for your chosen store to the application. You should have five parts and five products in your sample inventory and should not overwrite existing data in the database.

<pre>
    INSERT - BootStrapData, LINE 71
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
</pre>

### F.  Add a “Buy Now” button to your product list. Your “Buy Now” button must meet each of the following parameters:
•  The “Buy Now” button must be next to the buttons that update and delete products.
<pre>
INSERT - mainscreen.html, LINE 88

&lt;a th:href="@{/purchaseProduct(productID=${tempProduct.id})}" class="btn btn-primary btn-sm mb-3"
   onclick="if(!(confirm('Are you sure you want to purchase this product?')))return false"&gt;Buy Now&lt;/a&gt;
</pre>
•  The button should decrement the inventory of that product by one. It should not affect the inventory of any of the associated parts.
<pre>
    INSERT - product.java, LINE 111

    // Function to decrement inventory on successful purchase
    public boolean purchaseProduct() {
        if (this.inv >= 1) {
            this.inv--;
            return true;
        } else {
            return false;
        }
    }

    INSERT - AddProductController.java, LINE 177

    // Route to decrement product's inventory if successful
    @GetMapping("/purchaseProduct")
    public String purchaseProduct(@RequestParam("productID") int theId, Model theModel) {
        ProductService productService = context.getBean(ProductServiceImpl.class);
        Product product2 = productService.findById(theId);
        boolean purchaseConfirmation = product2.purchaseProduct();
        if (purchaseConfirmation) {
            productService.save(product2);
            return "purchasesuccess";
        } else {
            return "purchaseerror";
        }
    }
</pre>
•  Display a message that indicates the success or failure of a purchase.
<pre>
CREATE - purchasesuccess.html
&lt;!DOCTYPE html&gt;
&lt;html lang="en"&gt;
&lt;head&gt;
    &lt;meta charset="UTF-8"&gt;    <!-- Bootstrap CSS -->
    &lt;link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"&gt;
    &lt;title&gt;Purchase Successful&lt;/title&gt;
&lt;/head&gt;
&lt;body&gt;
&lt;div class="container d-flex justify-content-center align-items-center flex-column vh-100"&gt;
    &lt;h1&gt;Your product has been purchased!&lt;/h1&gt;
    &lt;a href="http://localhost:8080/"&gt;Link
        to Main Screen&lt;/a&gt;
&lt;/div&gt;
&lt;script src="https://cdn.jsdelivr.net/npm/@tsparticles/confetti@3.0.3/tsparticles.confetti.bundle.min.js"&gt;&lt;/script&gt;
&lt;script&gt;
    confetti({
        particleCount: 100,
        spread: 70,
        origin: { y: 0.6 },
    });
&lt;/script&gt;
&lt;/body&gt;
&lt;/html&gt;

CREATE purchaseerror.html

&lt;!DOCTYPE html&gt;
&lt;html lang="en"&gt;
&lt;head&gt;
    &lt;meta charset="UTF-8"&gt;    <!-- Bootstrap CSS -->
    &lt;link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous"&gt;
    &lt;title&gt;Purchase Error&lt;/title&gt;
&lt;/head&gt;
&lt;body&gt;
&lt;div class="container d-flex justify-content-center align-items-center flex-column vh-100"&gt;
    &lt;h1&gt;There was an error purchasing product. Check inventory.&lt;/h1&gt;
    &lt;a href="http://localhost:8080/"&gt;Link
        to Main Screen&lt;/a&gt;
&lt;/div&gt;
&lt;/body&gt;
&lt;/html&gt;
</pre>

### G.  Modify the parts to track maximum and minimum inventory by doing the following:
•  Add additional fields to the part entity for maximum and minimum inventory.
<pre>
INSERT - mainscreen.html, Line 41
    &lt;th&gt;Minimum&lt;/th&gt;
    &lt;th&gt;Maximum&lt;/th&gt;
Line 51
    &lt;td th:text="${tempPart.minimum}"&gt;1&lt;/td&gt;
    &lt;td th:text="${tempPart.maximum}"&gt;1&lt;/td&gt;
</pre>

•  Modify the sample inventory to include the maximum and minimum fields.
<pre>
INSERT - Part.java, Line 31
    @Min(value = 0, message = "Minimum inventory must be greater than 0")
    int minimum;
    int maximum;

    Line 96
    public int getMinimum() { return this.minimum; }
    public void setMinimum(int minimum) { this.minimum = minimum; }
    public int getMaximum() { return this.maximum; }
    public void setMaximum(int minimum) { this.minimum = maximum; }

INSERT - InhousePart.java and OutsourcedPart.java, Line 18
    this.minimum = 0;
    this.maximum = 100;
</pre>

•  Add to the InhousePartForm and OutsourcedPartForm forms additional text inputs for the inventory so the user can set the maximum and minimum values.
<pre>
INSERT - InhousePartForm.html AND OutsourcedPartForm.HTML, Line 24
&lt;p&gt;&lt;input type="text" th:field="*{minimum}" placeholder="Minimum" class="form-control mb-4 col-4"/&gt;&lt;/p&gt;
&lt;p th:if="${#fields.hasErrors('minimum')}" th:errors="*{minimum}"&gt;Inventory Error&lt;/p&gt;
&lt;p&gt;&lt;input type="text" th:field="*{maximum}" placeholder="Maximum" class="form-control mb-4 col-4"/&gt;&lt;/p&gt;
&lt;p th:if="${#fields.hasErrors('maximum')}" th:errors="*{maximum}"&gt;Inventory Error&lt;/p&gt;
</pre>
•  Rename the file the persistent storage is saved to.

<pre>
UPDATE - application.properties, Line 6
spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db102
TO
spring.datasource.url=jdbc:h2:file:~/spring-boot-h2-db103
</pre>

•  Modify the code to enforce that the inventory is between or at the minimum and maximum value.
<pre>
INSERT - Part.java, Line 108
    public void validateMinMax() {
        if (this.inv < this.minimum) {
            this.inv = this.minimum;
        } else if (this.inv > this.maximum ) {
            this.inv = this.maximum;
        }
    }
INSERT - InhousePartServiceImpl.java AND OutsourcedPartServiceIMpl.java, Line 54
this.validateMinMax();
</pre>

### H.  Add validation for between or at the maximum and minimum fields. The validation must include the following:
•  Display error messages for low inventory when adding and updating parts if the inventory is less than the minimum number of parts.

•  Display error messages for low inventory when adding and updating products lowers the part inventory below the minimum.

•  Display error messages when adding and updating parts if the inventory is greater than the maximum.


### I.  Add at least two unit tests for the maximum and minimum fields to the PartTest class in the test package.


### J.  Remove the class files for any unused validators in order to clean your code.

