package com.digitalhouse.msusers.infrastructure.service;

import com.digitalhouse.msusers.domain.models.User;
import com.digitalhouse.msusers.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BillService billService;

    public User findAllBills(String id) {
        User user = userRepository.findUser(id);
        user.setBills(billService.getBillsByCustomerBill(id));
        return user;
    }
}
