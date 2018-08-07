package io.swagger.web.repo;


import io.swagger.model.UberOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UberOrderRepository extends JpaRepository<UberOrder, Long> {

}
