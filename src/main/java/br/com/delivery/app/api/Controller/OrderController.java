package br.com.delivery.app.api.Controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.delivery.app.Model.Order;
import br.com.delivery.app.Repository.OrderRepository;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/order")
public class OrderController {
	@Autowired  
	private OrderRepository order_product;
	
	@PostMapping
	public  ResponseEntity<?> create( @RequestBody @Valid Order order){
		order.setStatus(1);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Date date = new Date();
		order.setCreate_data( dateFormat.format(date));
	        order_product.save(order);
		return ResponseEntity.ok(order);
	}
	
	@PutMapping(value="status/{id}")
	  public ResponseEntity<Order> update(@PathVariable("id") long id, @RequestBody Order order){
	    return order_product.findById(id)
	        .map(record -> {
	            record.setStatus(order.getStatus());
	            Order updated = order_product.save(record);
	            return ResponseEntity.ok().body(updated);
	        }).orElse(ResponseEntity.notFound().build());
	  }
	
	@GetMapping(value="{id}")
	  public Optional<Order> find_order_by_id(@PathVariable("id") long id){
	    return order_product.findById(id);
	  }

	@GetMapping
	public ResponseEntity<?> categorys(){
		List<Order> list  = order_product.allByStatus();
		return ResponseEntity.ok(list);
	}
}
