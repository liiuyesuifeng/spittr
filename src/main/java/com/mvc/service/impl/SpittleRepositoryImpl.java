package com.mvc.service.impl;

import com.mvc.eitity.Spittle;
import com.mvc.service.SpittleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class SpittleRepositoryImpl implements SpittleRepository {
    @Override
    public List<Spittle> findSpittles(long max, int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i = 0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }

    @Override
    public void save(Spittle spittle) {
        System.out.println(spittle.getLatitude() + " ; " + spittle.getContext().length());
    }
}
