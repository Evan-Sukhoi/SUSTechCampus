package com.sustech.campus.service.abandoned;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sustech.campus.model.BusLine;
import com.sustech.campus.repository.BusLineRepository;

import java.util.List;

@Service
public class BusLineService {

    private final BusLineRepository busLineRepository;

    @Autowired
    public BusLineService(BusLineRepository busLineRepository) {
        this.busLineRepository = busLineRepository;
    }

    // 添加巴士线路
    public boolean addBusLine(Integer lineId, String station, Integer index) {
        try {
            busLineRepository.addBusLine(lineId,station,index);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false; // 如果保存过程中出现异常，则返回 false
        }
    }

    // 根据巴士线路ID查询巴士线路
    public BusLine getBusLineById(Integer busLineId) {
        return busLineRepository.customFindById(busLineId);
    }

    // 删除巴士线路
    public void deleteBusLineById(Integer busLineId) {
        busLineRepository.customDeleteById(busLineId);
    }

    // 查询所有巴士线路
    public List<BusLine> getAllBusLines() {
        return busLineRepository.findAll();
    }
}
