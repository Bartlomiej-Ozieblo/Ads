package com.ads.repository;

import com.ads.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Integer> {

}
