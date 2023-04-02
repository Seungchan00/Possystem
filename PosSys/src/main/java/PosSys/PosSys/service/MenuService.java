package PosSys.PosSys.service;

import PosSys.PosSys.Repository.MenuRepository;
import PosSys.PosSys.domain.Menu;
import PosSys.PosSys.domain.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;

    @Transactional(readOnly = false)
    public void saveMenu(Menu menu){
        menuRepository.save(menu);
    }

}
