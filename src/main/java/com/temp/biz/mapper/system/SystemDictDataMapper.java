package com.temp.biz.mapper.system;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.temp.biz.domain.vo.system.dict.DictDataResVO;
import com.temp.biz.domain.vo.system.dict.DictDataSimpleRespVO;
import com.temp.biz.entity.system.SystemDictData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 字典数据 Mapper
 *
 * @author Hollis
 * @since 2024-06-13 10:47
 */
public interface SystemDictDataMapper extends BaseMapper<SystemDictData> {

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return {@link List }<{@link DictDataResVO }> 字典数据列表
     */
    List<DictDataResVO> listByDictType(@Param("dictType") String dictType);

    /**
     * 获得全部字典数据列表
     */
    List<DictDataSimpleRespVO> listSimple();
}