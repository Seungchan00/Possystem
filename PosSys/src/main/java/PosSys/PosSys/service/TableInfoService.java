package PosSys.PosSys.service;

import PosSys.PosSys.Repository.TableInfoRepository;
import PosSys.PosSys.domain.Restaurant;
import PosSys.PosSys.domain.TableInfo;
import PosSys.PosSys.domain.TableSeat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TableInfoService {
    private final TableInfoRepository tableInfoRepository;

    @Transactional(readOnly = false)
    public void saveTable(TableInfo tableInfo){
        tableInfoRepository.save(tableInfo);
    }

    public TableInfo findOne(Long tableinfoId){
        return tableInfoRepository.findOne(tableinfoId);
    }

    public List<TableInfo> findtableinfos(){
        return tableInfoRepository.findAll();
    }
    public List<TableInfo> findtableinfobyid(Long restaurantid){
        return tableInfoRepository.findAllbyRestaurantid(restaurantid);
    }

   // @Transactional(readOnly = false)
   // public void updateTableseat(Long id , TableSeat tableSeat){
   //    tableInfoRepository.UpdateTableSeat(id,tableSeat);
   // }

}
