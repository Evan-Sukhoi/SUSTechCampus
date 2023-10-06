package com.sustech.campus.repository;

import com.sustech.campus.model.BusLine;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusLineRepository extends JpaRepository<BusLine, Integer> {
    // 根据主键查询
    @Query("SELECT b FROM BusLine b WHERE b.busLineId = :busLineId")
    BusLine customFindById(@Param("busLineId") Integer busLineId);

    // 根据主键删除
    @Modifying
    @Query("DELETE FROM BusLine b WHERE b.busLineId = :busLineId")
    void customDeleteById(@Param("busLineId") Integer busLineId);

    // 添加 BusLine
    @Modifying
    @Query("INSERT INTO BusLine (lineId, station, index) VALUES (:lineId, :station, :index)")
    void customAddBusLine(@Param("lineId") Integer lineId, @Param("station") String station, @Param("index") Integer index);

    // 修改站点名称
    @Modifying
    @Query("UPDATE BusLine b SET b.station = :station WHERE b.busLineId = :busLineId")
    void customUpdateStation(@Param("busLineId") Integer busLineId, @Param("station") String station);

    // 修改索引
    @Modifying
    @Query("UPDATE BusLine b SET b.index = :index WHERE b.busLineId = :busLineId")
    void customUpdateIndex(@Param("busLineId") Integer busLineId, @Param("index") Integer index);
}

