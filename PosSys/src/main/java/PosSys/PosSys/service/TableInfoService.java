package PosSys.PosSys.service;

import PosSys.PosSys.Repository.TableInfoRepository;
import PosSys.PosSys.domain.Restaurant;
import PosSys.PosSys.domain.TableInfo;
import PosSys.PosSys.domain.TableSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TableInfoService {
    private final TableInfoRepository tableInfoRepository;


    @Transactional(readOnly = false)
    public void saveTable(TableInfo tableInfo) {
        tableInfoRepository.save(tableInfo);
    }

    public TableInfo findOne(Long tableinfoId) {
        return tableInfoRepository.findOne(tableinfoId);
    }

    public List<TableInfo> findtableinfos() {
        return tableInfoRepository.findAll();
    }

    public List<TableInfo> findtableinfobyid(Long restaurantid) {
        return tableInfoRepository.findAllbyRestaurantid(restaurantid);
    }

    @Transactional(readOnly = false)
    @Scheduled(fixedRate = 60000) // 60000ms = 1분
    public void updateRemainingTimes() {
        List<TableInfo> tableInfos = tableInfoRepository.findAll();

        for (TableInfo tableInfo : tableInfos) {
            int currentRemainingTime = tableInfo.getRemaintime();

            if (currentRemainingTime <= 0) {
                if (tableInfo.getTable_seat() != TableSeat.NOSEATED) {
                    // tableInfo.setTable_seat(TableSeat.NOSEATED);
                    tableInfo.setRemaintime(0); // Set remaining time to 0
                    tableInfoRepository.save(tableInfo);
                }
                continue;
            }
            tableInfo.setRemaintime((currentRemainingTime - 60));
            tableInfoRepository.save(tableInfo);
    }

    }
}


