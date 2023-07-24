package com.example.pokhara_paradise.Services;

import com.example.pokhara_paradise.UserPojo.CabPojo;
import com.example.pokhara_paradise.entity.Cab;

import java.util.List;


public interface CabService {
    String save_cab_record(CabPojo cabPojo);
    List<Cab> findAll();
    void deleteById(Integer id);
}
