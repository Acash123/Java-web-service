package pizzaloop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(path="/demo")


public class MainController {
    @Autowired
    private PizzaRepository pizzaRepository;
    @Autowired
    private OrderRepository orderRepository;

    private static final String SUCCESS= "Saved";
    /*
     * READ Operation
     * This method will list all the pizzas in the table
     * URI to access this: http://localhost:8080/demo/all
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<PizzaDetails> getPizzaDetails() {
        return pizzaRepository.findAll();
    }
    @GetMapping(path="/Order")
    public @ResponseBody Iterable<OrderDetails> getOrderDetails() {
        return orderRepository.findAll();
    }

    /*
     * READ Operation based on Pizza ID
     * This method will return the details of a pizza specified by the id
     * URI to access this: http://localhost:8080/demo/findByPizzaId?id=2
     */
    @GetMapping(path="/findByPizzaId")
    public @ResponseBody List<PizzaDetails> getPizzaById(@RequestParam Integer id) {
        return pizzaRepository.findByPizzaId(id);
    }

    /*
     * CREATE Operation
     * This method will crate new pizza item in the database table
     * and returns the SUCCESS message
     * URI to access this: http://localhost:8080/demo/add?name=VegiPizza&description=VegiSupreme&price=2500.75
     */
    @GetMapping(path="/add")
    public @ResponseBody String addNewPizza(@RequestParam String name, @RequestParam String description, @RequestParam Double price) {

        PizzaDetails pizzaDetails = new PizzaDetails();
        pizzaDetails.setName(name);
        pizzaDetails.setDescription(description);
        pizzaDetails.setPrice(price);

        pizzaRepository.save(pizzaDetails);
        return SUCCESS;
    }

    /*
     * DELETE Operation
     * This method will delete existing pizza item by finding it using the ID
     * and returns the deleted item
     * URI to access this: http://localhost:8080/demo/deleteByPizzaId?id=2
     */
    @GetMapping(path="/deleteByPizzaId")
    public @ResponseBody List<PizzaDetails> deletePizzaById(@RequestParam Integer id) {
        return pizzaRepository.deleteByPizzaId(id);
    }

    /*
     * UPDATE Operation
     * This method will update existing pizza details by finding it using the ID
     * and returns the updated data
     * URI to access this: http://localhost:8080/demo/update?id=1&name=updatedname&description=updated&price=1234.56
     */
    @GetMapping(path="/update")
    public @ResponseBody List<PizzaDetails> updatePizzaDetails(@RequestParam Integer id, @RequestParam String name, @RequestParam String description, @RequestParam Double price) {
        //First get all the pizza details according to the provided ID
        List<PizzaDetails> pizzaDetailsList = pizzaRepository.findByPizzaId(id);
        if(!pizzaDetailsList.isEmpty()) {
            //Iterate through the pizza list
            for(PizzaDetails pizzaDetails: pizzaDetailsList) {
                //Set new values
                pizzaDetails.setName(name);
                pizzaDetails.setDescription(description);
                pizzaDetails.setPrice(price);
                //Update existing pizza item
                pizzaRepository.save(pizzaDetails);
            }
        }
        return pizzaRepository.findByPizzaId(id);
    }
    //Order Details Controller Code

//------------------------------------------------------------------------------------------------------------------------------------------//

   // @GetMapping(path="/all")
   /*
    * READ Operation based on Pizza ID
    * This method will return the details of a pizza specified by the id
    * URI to access this: http://localhost:8080/demo/getOrderById?orderId=2
    */
    @GetMapping(path="/findByOrderId")
    public @ResponseBody List<OrderDetails> getOrderById(@RequestParam Integer id) {
        return orderRepository.findByOrderId(id);
    }
    /*
     * CREATE Operation
     * This method will crate new pizza item in the database table
     * and returns the SUCCESS message
     * URI to access this: http://localhost:8080/demo/addOrder?orderId=VegiPizza&userId=VegiSupreme&pizzaName=chease&qty=VegiSupreme&totalPrice=chease&paymentMethod=VegiSupreme&phoneNumber=chease&Address=123&OrderStatus=123&pizzaId=1
     */
    //Im writing this custom controller to gt the order by user id



//Test
    @GetMapping(path="/addOrder")
    public @ResponseBody String addNewOrder(@RequestParam Integer orderId, @RequestParam Integer  userId, @RequestParam  String pizzaName ,@RequestParam int qty,@RequestParam Double totalPrice,@RequestParam String paymentMethod,@RequestParam int phoneNumber,@RequestParam String Address,@RequestParam String OrderStatus,@RequestParam int pizzaId) {

        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderId(orderId);
        orderDetails.setUserId(userId);
        orderDetails.setPizzaName(pizzaName);
        orderDetails.setQty(qty);
        orderDetails.setTotalPrice(totalPrice);
        orderDetails.setPaymentMethod(paymentMethod);
        orderDetails.setPhoneNumber(phoneNumber);
        orderDetails.setAddress(Address);
        orderDetails.setOrderStatus(OrderStatus);
        orderDetails.setPizzaId(pizzaId);
        orderRepository.save(orderDetails);
        return SUCCESS;
    }

    /*
     * DELETE Operation
     * This method will delete existing pizza item by finding it using the ID
     * and returns the deleted item
     * URI to access this: http://localhost:8080/demo/deleteByOrderId?id=2
     */
    @GetMapping(path="/deleteByOrderId")
    public @ResponseBody List<OrderDetails> deleteByOrderId(@RequestParam Integer id) {
        return orderRepository.deleteByOrderId(id);
    }

    /*
     * UPDATE Operation
     * This method will update existing pizza details by finding it using the ID
     * and returns the updated data
     * URI to access this: http://localhost:8080/demo/update?id=1&name=updatedname&description=updated&price=1234.56
     */
    @GetMapping(path="/updateOrder")
    public @ResponseBody
    List<OrderDetails> updateOrderDetails(@RequestParam Integer orderId, @RequestParam Integer  userId, @RequestParam  String pizzaName ,@RequestParam int qty,@RequestParam Double totalPrice,@RequestParam String paymentMethod,@RequestParam int phoneNumber,@RequestParam String Address,@RequestParam String OrderStatus,@RequestParam int pizzaId) {
        //First get all the pizza details according to the provided ID
        List<OrderDetails> orderDetailsList = orderRepository.findByOrderId(orderId);
        if(!orderDetailsList.isEmpty()) {
            //Iterate through the pizza list
            for(OrderDetails orderDetails: orderDetailsList) {
                //Set new values
                orderDetails.setPizzaId(pizzaId);
                orderDetails.setOrderStatus(OrderStatus);
                orderDetails.setPizzaName(pizzaName);
                orderDetails.setQty(qty);
                orderDetails.setUserId(userId);
              orderDetails.setTotalPrice(totalPrice);
              orderDetails.setPaymentMethod(paymentMethod);
              orderDetails.setPhoneNumber(phoneNumber);
              orderDetails.setAddress(Address);
                //Update existing pizza item
                orderRepository.save(orderDetails);
            }
        }
        return orderRepository.findByOrderId(orderId);
    }





}