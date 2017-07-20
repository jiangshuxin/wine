package com.wuxian99.finance.basedata.service.wine;

import com.wuxian99.finance.basedata.domain.UserAddressEntity;
import com.wuxian99.finance.basedata.domain.UserEntity;
import com.wuxian99.finance.basedata.repository.wine.UserAddressRepository;
import com.wuxian99.finance.basedata.repository.wine.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAddressRepository userAddressRepository;

    public UserEntity findByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public UserEntity findByUserId(Long userId) {
        return userRepository.findOne(userId);
    }

    public UserEntity saveOrUpdateUser(UserEntity user){
        return userRepository.save(user);
    }

    public List<UserAddressEntity> findUserAddressesByUserId(Long userId) {
        return userAddressRepository.findByUserId(userId);
    }

    public UserAddressEntity findUserAddressById(Long addressId) {
        return userAddressRepository.findOne(addressId);
    }

    public UserAddressEntity saveOrUpdateUserAddress(UserAddressEntity address){
        return userAddressRepository.save(address);
    }

}
