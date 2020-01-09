package com.mi.materialsDetailBusiness.mapper;

import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 物资出入库详情Mapper接口
 * 
 * @author mi
 * @date 2019-11-19
 */
public interface TbmaterialsDetailMapper 
{
    /**
     * 查询物资出入库详情
     * 
     * @param id 物资出入库详情ID
     * @return 物资出入库详情
     */
    public TbmaterialsDetail selectTbmaterialsDetailById(Long id);

    /**
     * 查询物资出入库详情列表
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 物资出入库详情集合
     */
    public List<TbmaterialsDetail> selectTbmaterialsDetailList(TbmaterialsDetail tbmaterialsDetail);

    /**
     * 新增物资出入库详情
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 结果
     */
    public int insertTbmaterialsDetail(TbmaterialsDetail tbmaterialsDetail);

    /**
     * 修改物资出入库详情
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 结果
     */
    public int updateTbmaterialsDetail(TbmaterialsDetail tbmaterialsDetail);

    /**
     * 删除物资出入库详情
     * 
     * @param id 物资出入库详情ID
     * @return 结果
     */
    public int deleteTbmaterialsDetailById(Long id);

    /**
     * 批量删除物资出入库详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbmaterialsDetailByIds(String[] ids);

    int updateTbmaterialsDetailByTbmaterialsId(Integer id);

    /**
     * 本月条目总数
     * @return
     */
    @Select("select COUNT(0) from tb_materials_detail where DATE_FORMAT( create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )")
    int thisMonthcount();
    /**
     * 上月条目总数
     * @return
     */
    @Select("select COUNT(0) from tb_materials_detail where PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( create_time, '%Y%m' ) ) =1")
    int lastMonthcount();
    List<TbmaterialsDetail> newTenPut(@Param("type") Integer type);
}
