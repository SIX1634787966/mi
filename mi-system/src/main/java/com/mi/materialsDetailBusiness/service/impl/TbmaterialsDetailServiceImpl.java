package com.mi.materialsDetailBusiness.service.impl;

import com.mi.common.core.text.Convert;
import com.mi.common.utils.DateUtils;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsBusiness.mapper.TbmaterialsMapper;
import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import com.mi.materialsDetailBusiness.mapper.TbmaterialsDetailMapper;
import com.mi.materialsDetailBusiness.service.ITbmaterialsDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;

/**
 * 物资出入库详情Service业务层处理
 * 
 * @author mi
 * @date 2019-11-19
 */
@Service
public class TbmaterialsDetailServiceImpl implements ITbmaterialsDetailService 
{
    @Autowired
    private TbmaterialsDetailMapper tbmaterialsDetailMapper;
    @Autowired
    private TbmaterialsMapper tbmaterialsMapper;
    /**
     * 查询物资出入库详情
     * 
     * @param id 物资出入库详情ID
     * @return 物资出入库详情
     */
    @Override
    public TbmaterialsDetail selectTbmaterialsDetailById(Long id)
    {
        return tbmaterialsDetailMapper.selectTbmaterialsDetailById(id);
    }

    /**
     * 查询物资出入库详情列表
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 物资出入库详情
     */
    @Override
    public List<TbmaterialsDetail> selectTbmaterialsDetailList(TbmaterialsDetail tbmaterialsDetail)
    {
        List<TbmaterialsDetail> details = tbmaterialsDetailMapper.selectTbmaterialsDetailList(tbmaterialsDetail);
        Iterator<TbmaterialsDetail> iterator = details.iterator();
        while (iterator.hasNext()){
            TbmaterialsDetail next = iterator.next();
            next.setTbmaterials(tbmaterialsMapper.selectTbmaterialsById(next.getMaterialsId()));
        }
        return details;
    }

    /**
     * 新增物资出入库详情
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTbmaterialsDetail(TbmaterialsDetail tbmaterialsDetail)
    {

        tbmaterialsDetail.setCreateTime(DateUtils.getNowDate());
        Tbmaterials tbmaterials=tbmaterialsMapper.selectTbmaterialsById(tbmaterialsDetail.getMaterialsId());
        tbmaterials.setAmount(tbmaterials.getAmount()-tbmaterialsDetail.getAmount());
        tbmaterialsMapper.updateTbmaterials(tbmaterials);
        return tbmaterialsDetailMapper.insertTbmaterialsDetail(tbmaterialsDetail);
    }

    /**
     * 修改物资出入库详情
     * 
     * @param tbmaterialsDetail 物资出入库详情
     * @return 结果
     */
    @Override
    public int updateTbmaterialsDetail(TbmaterialsDetail tbmaterialsDetail)
    {
        tbmaterialsDetail.setUpdateTime(DateUtils.getNowDate());
        return tbmaterialsDetailMapper.updateTbmaterialsDetail(tbmaterialsDetail);
    }

    /**
     * 删除物资出入库详情对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbmaterialsDetailByIds(String ids)
    {
        return tbmaterialsDetailMapper.deleteTbmaterialsDetailByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物资出入库详情信息
     * 
     * @param id 物资出入库详情ID
     * @return 结果
     */
    public int deleteTbmaterialsDetailById(Long id)
    {
        return tbmaterialsDetailMapper.deleteTbmaterialsDetailById(id);
    }

    @Override
    public List<TbmaterialsDetail> newTenPut(Integer type) {
        List<TbmaterialsDetail> list=tbmaterialsDetailMapper.newTenPut(type);
        for (int i = 0; i < list.size(); i++) {
            TbmaterialsDetail tbmaterialsDetail = list.get(i);
            tbmaterialsDetail.setTbmaterials(tbmaterialsMapper.selectTbmaterialsById(tbmaterialsDetail.getMaterialsId()));
        }
        return list;
    }

    @Override
    public Boolean checkAmount(Integer amount, Integer materialsId) {
        Integer s=tbmaterialsMapper.checkAmount(materialsId);
        if(s>=amount){
            return true;

        }else {
            return false;

        }
    }
}
