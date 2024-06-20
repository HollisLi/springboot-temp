package com.temp.biz.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * 演示 Convert
 *
 * @author Hollis
 * @since 2024/06/18 下午4:07
 */
@Mapper
public interface DemoConvert {

    DemoConvert INSTANCE = Mappers.getMapper(DemoConvert.class);

}