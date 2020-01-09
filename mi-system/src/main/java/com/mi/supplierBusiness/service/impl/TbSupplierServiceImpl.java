package com.mi.supplierBusiness.service.impl;

import com.mi.common.core.domain.Ztree;
import com.mi.common.core.text.Convert;
import com.mi.common.exception.BusinessException;
import com.mi.common.utils.DateUtils;
import com.mi.common.utils.StringUtils;
import com.mi.supplierBusiness.domain.TbSupplier;
import com.mi.supplierBusiness.mapper.TbSupplierMapper;
import com.mi.supplierBusiness.service.ITbSupplierService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 供应商Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Service
public class TbSupplierServiceImpl implements ITbSupplierService 
{
    private static final Logger log = LoggerFactory.getLogger(TbSupplierServiceImpl.class);


    @Autowired
    private TbSupplierMapper tbSupplierMapper;
    /**
     * 查询供应商
     * @return 供应商菜单树
     */
    @Override
    public List<Ztree> selectTbSupplierTree() {
        List<TbSupplier> list=tbSupplierMapper.selectTbSupplierTree();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (TbSupplier tbSupplier : list) {

            Ztree ztree=new Ztree();
            ztree.setId(tbSupplier.getId().longValue());
            ztree.setName(tbSupplier.getBrand());
            ztree.setTitle(tbSupplier.getBrand());
            ztrees.add(ztree);
        }
        return  ztrees;
    }

    /**
     * 导入供应商
     * @param tbSupplierList
     * @param updateSupport
     * @param operName
     * @return
     */
    @Override
    public String importTbSupplier(List<TbSupplier> tbSupplierList, boolean updateSupport, String operName) {
        if (StringUtils.isNull(tbSupplierList) || tbSupplierList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (TbSupplier tbSupplier : tbSupplierList)
        {
            try
            {
                this.insertTbSupplier(tbSupplier);
                successNum++;
                successMsg.append("<br/>" + successNum + "、供应商 " + tbSupplier.getBrand() + " 导入成功");

            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、供应商 " + tbSupplier.getBrand()+ " 导入失败：";
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

    /**
     * 查询供应商
     * 
     * @param id 供应商ID
     * @return 供应商
     */
    @Override
    public TbSupplier selectTbSupplierById(Integer id)
    {
        return tbSupplierMapper.selectTbSupplierById(id);
    }

    /**
     * 查询供应商列表
     * 
     * @param tbSupplier 供应商
     * @return 供应商
     */
    @Override
    public List<TbSupplier> selectTbSupplierList(TbSupplier tbSupplier)
    {
        return tbSupplierMapper.selectTbSupplierList(tbSupplier);
    }

    /**
     * 新增供应商
     * 
     * @param tbSupplier 供应商
     * @return 结果
     */
    @Override
    public int insertTbSupplier(TbSupplier tbSupplier)
    {
        tbSupplier.setCreateTime(DateUtils.getNowDate());
        return tbSupplierMapper.insertTbSupplier(tbSupplier);
    }

    /**
     * 修改供应商
     * 
     * @param tbSupplier 供应商
     * @return 结果
     */
    @Override
    public int updateTbSupplier(TbSupplier tbSupplier)
    {
        tbSupplier.setUpdateTime(DateUtils.getNowDate());
        return tbSupplierMapper.updateTbSupplier(tbSupplier);
    }

    /**
     * 删除供应商对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbSupplierByIds(String ids)
    {
        return tbSupplierMapper.deleteTbSupplierByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除供应商信息
     * 
     * @param id 供应商ID
     * @return 结果
     */
    public int deleteTbSupplierById(Integer id)
    {
        return tbSupplierMapper.deleteTbSupplierById(id);
    }
}
