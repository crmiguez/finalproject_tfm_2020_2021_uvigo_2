package com.crmiguez.aixinainventory.service.line;

import com.crmiguez.aixinainventory.entities.Line;
import com.crmiguez.aixinainventory.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("lineService")
public class LineService implements ILineService {

    @Autowired
    @Qualifier("lineRepository")
    private LineRepository lineRepository;

    @Override
    public List<Line> findAllLines() {
        return (List<Line>) lineRepository.findAll();
    }

    @Override
    public Optional<Line> findLineById(Long lineId){ return lineRepository.findById(lineId); }

    @Override
    public Line addLine(Line line){
        return lineRepository.save(line);
    }

    @Override
    public void deleteLine (Line line){
        lineRepository.delete(line);
    }

    @Override
    public Line updateLine(Line lineDetails, Line line){

        line.setInvoice(lineDetails.getInvoice());
        line.setItem(lineDetails.getItem());
        line.setItemSet(lineDetails.getItemSet());
        line.setUnits(lineDetails.getUnits());
        line.setInvoiceUnitCost(lineDetails.getInvoiceUnitCost());
        line.setInvoiceTotalCost(lineDetails.getInvoiceTotalCost());

        return lineRepository.save(line);
    }

}
