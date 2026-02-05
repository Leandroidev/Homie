package com.homie.api.data;

import com.homie.api.models.Hogar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HogarRepository extends JpaRepository<Hogar,Long> {
}
