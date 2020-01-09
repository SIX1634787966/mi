package com.mi.warehouseBusiness.service.impl;

import com.mi.common.core.domain.Ztree;
import com.mi.common.core.text.Convert;
import com.mi.common.exception.BusinessException;
import com.mi.common.utils.DateUtils;
import com.mi.common.utils.StringUtils;
import com.mi.warehouseBusiness.domain.TbWarehouse;
import com.mi.warehouseBusiness.mapper.TbWarehouseMapper;
import com.mi.warehouseBusiness.service.ITbWarehouseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 仓库管理Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Service
public class TbWarehouseServiceImpl implements ITbWarehouseService 
{
    private static final Logger log = LoggerFactory.getLogger(TbWarehouseServiceImpl.class);

    @Autowired
    private TbWarehouseMapper tbWarehouseMapper;

    /**
     * 查询仓库管理
     * 
     * @param id 仓库管理ID
     * @return 仓库管理
     */
    @Override
    public TbWarehouse selectTbWarehouseById(Integer id)
    {
        return tbWarehouseMapper.selectTbWarehouseById(id);
    }

    /**
     * 查询仓库管理列表
     * 
     * @param tbWarehouse 仓库管理
     * @return 仓库管理
     */
    @Override
    public List<TbWarehouse> selectTbWarehouseList(TbWarehouse tbWarehouse)
    {
        return tbWarehouseMapper.selectTbWarehouseList(tbWarehouse);
    }

    /**
     * 新增仓库管理
     * 
     * @param tbWarehouse 仓库管理
     * @return 结果
     */
    @Override
    public int insertTbWarehouse(TbWarehouse tbWarehouse)
    {
        tbWarehouse.setCreateTime(DateUtils.getNowDate());
        return tbWarehouseMapper.insertTbWarehouse(tbWarehouse);
    }

    /**
     * 修改仓库管理
     * 
     * @param tbWarehouse 仓库管理
     * @return 结果
     */
    @Override
    public int updateTbWarehouse(TbWarehouse tbWarehouse)
    {
        tbWarehouse.setUpdateTime(DateUtils.getNowDate());
        return tbWarehouseMapper.updateTbWarehouse(tbWarehouse);
    }

    /**
     * 删除仓库管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbWarehouseByIds(String ids)
    {
        return tbWarehouseMapper.deleteTbWarehouseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除仓库管理信息
     * 
     * @param id 仓库管理ID
     * @return 结果
     */
    public int deleteTbWarehouseById(Integer id)
    {
        return tbWarehouseMapper.deleteTbWarehouseById(id);
    }

    /**
     * 仓库树
     * @return
     */
    @Override
    public List<Ztree> selectWarehouseTree() {
        List<TbWarehouse> tbWarehouses=  tbWarehouseMapper.selectTbWarehouseTree();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TbWarehouse tbWarehouse : tbWarehouses) {

            Ztree ztree=new Ztree();
            ztree.setId(tbWarehouse.getId().longValue());
            ztree.setName(tbWarehouse.getName());
            ztree.setTitle(tbWarehouse.getName());
            ztrees.add(ztree);
        }
        return  ztrees;
    }

    @Override
    public String importWarehouse(List<TbWarehouse> tbWarehouseList, boolean updateSupport, String operName) {

        if (StringUtils.isNull(tbWarehouseList) || tbWarehouseList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (TbWarehouse tbWarehouse : tbWarehouseList)
        {
            try
            {
                    this.insertTbWarehouse(tbWarehouse);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、仓库 " + tbWarehouse.getName() + " 导入成功");

            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、仓库 " + tbWarehouse.getName()+ " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }
}
