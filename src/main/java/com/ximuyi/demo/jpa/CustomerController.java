package com.ximuyi.demo.jpa;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/***
 * Consider defining a bean named 'entityManagerFactory' in your configuration
 * 删除 C:\Users\chenjingjun\.m2\repository\org\hibernate\hibernate-core目录下的文件，然后maven - update project即可。
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerRepository repository;

	@ApiOperation(value="列表", notes="获取所有的列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Iterable<Customer> getCustomers(){
        return repository.findAll();
    }

    /**
     * Ambiguous handler methods mapped for HTTP path 'http://localhost:8080/customer/21
     * @RequestMapping(value = "/{id}",method = RequestMethod.GET)
     * public com.ximuyi.demo.jpa.Customer com.ximuyi.demo.jpa.CustomerController.getCustomerById(long)
     *   @RequestMapping(value = "/{lastName}",method = RequestMethod.GET)
     * public java.lang.Iterable com.ximuyi.demo.jpa.CustomerController.getCustomerByLastName(java.lang.String)
     * @param id
     * @return
     */
    @RequestMapping(value = "/id_{id}",method = RequestMethod.GET)
    public Customer getCustomerById(@PathVariable("id") long id){
	    return repository.findById(id).orElseGet(Customer::new);
    }

    @RequestMapping(value = "/lastName_{lastName}",method = RequestMethod.GET)
    public Iterable<Customer> getCustomerByLastName(@PathVariable("lastName") String lastName){
        return repository.findByLastName(lastName);
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String updateCustomer(@PathVariable("id")long id , @RequestParam(value = "firstName", required = true)String firstName,
                                  @RequestParam(value = "lastName" ,required = true) String lastName){
        Customer customer = new Customer();
        customer.setId(id);
        customer.setFirstName(lastName);
        customer.setLastName(firstName);
        Customer jpaAccount = repository.save(customer);
        return jpaAccount != null ? customer.toString() : "fail";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postCustomer(@RequestParam(value = "firstName")String firstName, @RequestParam(value = "lastName" )String lastName){
        Customer customer = new Customer();
        customer.setFirstName(lastName);
        customer.setLastName(firstName);
        Customer jpaAccount = repository.save(customer);
        return jpaAccount != null ? customer.toString() : "fail";
    }


}
