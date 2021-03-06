package com.tdh.cache;

import com.tdh.domain.Bzdm;
import com.tdh.domain.BzdmExample;
import com.tdh.domain.Depart;
import com.tdh.domain.DepartExample;
import com.tdh.mapper.BzdmMapper;
import com.tdh.mapper.DepartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Puti
 * @date 2022/4/11 10:26
 */
@Component
public class Caches {

    @Autowired
    private DepartMapper departMapper;

    @Autowired
    private BzdmMapper bzdmMapper;

    private final String KIND_GENDER = "00003";
    public static final Map<String, Depart> departMap = new HashMap<>();
//    public static final Map<String, Bzdm> genderMap = new HashMap<>();
    public static final Map<String, List<Bzdm>> bzdm_kind_Map = new HashMap<>();


    @PostConstruct
    private void initDepartMap() {
        //查库加载缓存
        DepartExample departExample = new DepartExample();
        departExample.createCriteria();
        List<Depart> departs = departMapper.selectByExample(departExample);

        for (Depart depart : departs) {
            departMap.put(depart.getBmdm(), depart);
        }
    }

    @PostConstruct
    private void initGenderMap(){
        BzdmExample bzdmExample = new BzdmExample();
        bzdmExample.createCriteria().andKindEqualTo(KIND_GENDER);
        List<Bzdm> bzdms = bzdmMapper.selectByExample(bzdmExample);

        for (Bzdm bzdm : bzdms) {
            if(bzdm_kind_Map.containsKey(bzdm.getKind())){
                bzdm_kind_Map.get(bzdm.getKind()).add(bzdm);
            }else{
                List<Bzdm> list = new ArrayList<>();
                list.add(bzdm);
                bzdm_kind_Map.put(bzdm.getKind(), list);
            }
        }
    }
}
