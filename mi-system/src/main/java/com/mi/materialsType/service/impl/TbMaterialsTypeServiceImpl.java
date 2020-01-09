package com.mi.materialsType.service.impl;

import com.mi.common.core.domain.Ztree;
import com.mi.common.core.text.Convert;
import com.mi.common.utils.DateUtils;
import com.mi.materialsType.domain.TbMaterialsType;
import com.mi.materialsType.mapper.TbMaterialsTypeMapper;
import com.mi.materialsType.service.ITbMaterialsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 物资类型Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-27
 */
@Service
public class TbMaterialsTypeServiceImpl implements ITbMaterialsTypeService 
{
    @Autowired
    private TbMaterialsTypeMapper tbMaterialsTypeMapper;
    /**
     * 加载物资类型列表树
     */
    @Override
    public List<Ztree> selectMaterialsTypeTree() {
        List<TbMaterialsType> list=tbMaterialsTypeMapper.selectTbMaterialsTypeList(null);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TbMaterialsType type : list) {
            Ztree ztree=new Ztree();
            ztree.setId(type.getId().longValue());
            ztree.setName(type.getName());
            ztree.setTitle(type.getName());
            ztrees.add(ztree);
        }
        return  ztrees;
    }

    /**
     * 查询物资类型
     * 
     * @param id 物资类型ID
     * @return 物资类型
     */
    @Override
    public TbMaterialsType selectTbMaterialsTypeById(Integer id)
    {
        return tbMaterialsTypeMapper.selectTbMaterialsTypeById(id);
    }

    /**
     * 查询物资类型列表
     * 
     * @param tbMaterialsType 物资类型
     * @return 物资类型
     */
    @Override
    public List<TbMaterialsType> selectTbMaterialsTypeList(TbMaterialsType tbMaterialsType)
    {
        return tbMaterialsTypeMapper.selectTbMaterialsTypeList(tbMaterialsType);
    }

    /**
     * 新增物资类型
     * 
     * @param tbMaterialsType 物资类型
     * @return 结果
     */
    @Override
    public int insertTbMaterialsType(TbMaterialsType tbMaterialsType)
    {
        tbMaterialsType.setCreateTime(DateUtils.getNowDate());
        return tbMaterialsTypeMapper.insertTbMaterialsType(tbMaterialsType);
    }

    /**
     * 修改物资类型
     * 
     * @param tbMaterialsType 物资类型
     * @return 结果
     */
    @Override
    public int updateTbMaterialsType(TbMaterialsType tbMaterialsType)
    {
        tbMaterialsType.setUpdateTime(DateUtils.getNowDate());
        return tbMaterialsTypeMapper.updateTbMaterialsType(tbMaterialsType);
    }

    /**
     * 删除物资类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbMaterialsTypeByIds(String ids)
    {
        return tbMaterialsTypeMapper.deleteTbMaterialsTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物资类型信息
     * 
     * @param id 物资类型ID
     * @return 结果
     */
    public int deleteTbMaterialsTypeById(Integer id)
    {
        return tbMaterialsTypeMapper.deleteTbMaterialsTypeById(id);
    }
}
