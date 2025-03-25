package com.temp.biz.controller.system;

import com.temp.biz.domain.vo.system.dict.DictDataResVO;
import com.temp.biz.domain.vo.system.dict.DictDataSimpleRespVO;
import com.temp.biz.service.system.SystemDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 管理后台 - 字典
 *
 * @author Hollis
 * @since 2024/06/13 上午10:48
 */
@Validated
@RestController
@RequestMapping("/system/dict-data")
@RequiredArgsConstructor
public class DictDataController {

    private final SystemDictDataService systemDictDataService;

    /**
     * 根据字典类型查询字典数据信息
     *
     * @param dictType 字典类型
     * @return {@link List }<{@link DictDataResVO }> 字典数据列表
     */
    @GetMapping("/list-by-type")
    public List<DictDataResVO> listByDictType(@RequestParam("type") String dictType) {
        return systemDictDataService.listByDictType(dictType);
    }

    /**
     * 获得全部字典数据列表
     */
    @GetMapping("/list-all-simple")
    public List<DictDataSimpleRespVO> listSimple() {
        return systemDictDataService.listSimple();
    }
}
