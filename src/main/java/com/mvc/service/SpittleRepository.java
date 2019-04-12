package com.mvc.service;

import com.mvc.eitity.Spittle;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface SpittleRepository {
    List<Spittle> findSpittles(long max,int count);
    void save(Spittle spittle);

}
