package top.gongtao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.gongtao.repository.UserRepository;

//@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


}
