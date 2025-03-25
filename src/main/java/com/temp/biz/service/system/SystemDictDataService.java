package com.temp.biz.service.system;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.temp.biz.domain.vo.system.dict.DictDataResVO;
import com.temp.biz.domain.vo.system.dict.DictDataSimpleRespVO;
import com.temp.biz.entity.system.SystemDictData;
import com.temp.biz.mapper.system.SystemDictDataMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 字典数据 Service
 *
 * @author Hollis
 * @since 2024-06-13 10:47
 */
@Service
public class SystemDictDataService extends ServiceImpl<SystemDictDataMapper, SystemDictData> {

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return {@link List }<{@link DictDataResVO }> 字典数据列表
     */
    public List<DictDataResVO> listByDictType(String dictType) {
        return baseMapper.listByDictType(dictType);
    }

    /**
     * 获得全部字典数据列表
     */
    public List<DictDataSimpleRespVO> listSimple() {
        return baseMapper.listSimple();
    }
}
