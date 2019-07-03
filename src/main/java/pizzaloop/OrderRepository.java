package pizzaloop;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
public interface OrderRepository extends  CrudRepository<OrderDetails, Integer>{
    /*
     * Find Order by Id
     */
    List<OrderDetails> findByOrderId(Integer id);

    //

    /*
     * Delete Order by Id
     */
    List<OrderDetails> deleteByOrderId(Integer id);


}
