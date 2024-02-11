package com.example.govoriigraya.services;

import org.springframework.stereotype.Service;

@Service
public class PhoneServiceImpl implements PhoneService {
    @Override
    public String standatrtise(String phone) {
        phone = phone.replaceAll("[\\s()-+]", "");
        if (phone.length() == 10) phone = "8" + phone;
        else if (phone.length() == 11) phone = '8' + phone.substring(1);
        return phone;
    }
}
