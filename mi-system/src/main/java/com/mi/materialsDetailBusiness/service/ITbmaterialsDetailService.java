package com.mi.materialsDetailBusiness.service;

import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import java.util.List;

/**
 * 物资出入库详情Service接口
 * 
 * @author mi
 * @date 2019-11-19
 */
public interface ITbmaterialsDetailService 
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
     * 批量删除物资出入库详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbmaterialsDetailByIds(String ids);

    /**
     * 删除物资出入库详情信息
     * 
     * @param id 物资出入库详情ID
     * @return 结果
     */
    public int deleteTbmaterialsDetailById(Long id);

    /**
     * 查询最新十条出入库信息
     * @param type 出入库类型
     * @return
     */
     List<TbmaterialsDetail> newTenPut(Integer type);

    /**
     * 校验库存
     * @param amount
     * @param materialsId
     * @return
     */
    Boolean checkAmount(Integer amount,Integer materialsId);
}
