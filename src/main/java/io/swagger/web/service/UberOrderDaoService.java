package io.swagger.web.service;

import io.swagger.model.Category;
import io.swagger.model.UberOrder;
import io.swagger.web.repo.CategoryRepository;
import io.swagger.web.repo.UberOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@ComponentScan(basePackages = { "io.swagger", "io.swagger.web" })
public class UberOrderDaoService {

    @Autowired
    private UberOrderRepository UberOrderRepository;


    public UberOrder save(UberOrder UberOrder) {
        return UberOrderRepository.save(UberOrder);
    }



}
