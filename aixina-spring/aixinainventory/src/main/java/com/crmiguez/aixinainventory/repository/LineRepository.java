package com.crmiguez.aixinainventory.repository;

import com.crmiguez.aixinainventory.entities.Line;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("lineRepository")
public interface LineRepository extends CrudRepository<Line, Long> {
    Line findByLineId(Long lineId);
}
