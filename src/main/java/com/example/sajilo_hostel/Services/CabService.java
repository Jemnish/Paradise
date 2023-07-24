package com.example.sajilo_hostel.Services;

import com.example.sajilo_hostel.UserPojo.CabPojo;
import com.example.sajilo_hostel.entity.Cab;

import java.util.List;


public interface CabService {
    String save_cab_record(CabPojo cabPojo);
    List<Cab> findAll();
    void deleteById(Integer id);
}
