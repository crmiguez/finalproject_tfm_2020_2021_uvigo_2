package com.crmiguez.aixinainventory.service.line;

import com.crmiguez.aixinainventory.entities.Line;

import java.util.List;
import java.util.Optional;

public interface ILineService {
    public List<Line> findAllLines();
    public Optional<Line> findLineById(Long lineId);
    public Line addLine(Line line);
    public Line updateLine(Line lineDetails, Line line);
    public void deleteLine (Line line);

}
