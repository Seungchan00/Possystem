
package PosSys.PosSys.Repository;

import PosSys.PosSys.domain.Restaurant;
import PosSys.PosSys.domain.TableInfo;
import PosSys.PosSys.domain.TableSeat;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Table;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class TableInfoRepository {

    private final EntityManager em;

    public void save(TableInfo tableInfo){
        if(tableInfo.getId() == null){
            em.persist(tableInfo);
        }
        else{
            em.merge(tableInfo);
         }
    }
    public TableInfo findOne(Long id){
        return em.find(TableInfo.class, id);
    }

    public List<TableInfo> findAll () {
        return em.createQuery("select T from TableInfo T", TableInfo.class).getResultList();
    }
  //  public void UpdateTableSeat(Long id , TableSeat tableSeat){
   //     TableInfo tableinfo = new TableInfo();
    //    if()

  //  }

}
