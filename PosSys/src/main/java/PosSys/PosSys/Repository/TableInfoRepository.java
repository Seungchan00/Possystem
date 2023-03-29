
package PosSys.PosSys.Repository;

import PosSys.PosSys.domain.TableInfo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class TableInfoRepository {

    private final EntityManager em;

    public void save(TableInfo tableInfo){
        if(tableInfo.getTableInfo_number() == null){
            em.persist(tableInfo);
        }
      //  else{
        //}
    }

}
