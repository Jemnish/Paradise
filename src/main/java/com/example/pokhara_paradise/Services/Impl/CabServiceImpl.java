package com.example.pokhara_paradise.Services.Impl;

import com.example.pokhara_paradise.entity.Cab;
import com.example.pokhara_paradise.UserPojo.CabPojo;
import com.example.pokhara_paradise.repo.CabRepo;
import com.example.pokhara_paradise.Services.CabService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CabServiceImpl implements CabService {
    public final CabRepo cabRepo;
    @Override
    public String save_cab_record(CabPojo cabPojo) {
        Cab cab = new Cab();
        cab.setFullname(cabPojo.getFullname());
        cab.setPhone(cabPojo.getPhone());
        cab.setCar(cabPojo.getCar());
        cab.setPeople(cabPojo.getPeople());
        cab.setDate(cabPojo.getDate());
        cab.setTime(cabPojo.getTime());
        cabRepo.save(cab);
        return "created";
    }

    @Override
    public List<Cab> findAll() {
        return this.cabRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        cabRepo.deleteById(id);

    }
}
