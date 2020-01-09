package com.mi.materialsBusiness.service.impl;

import com.mi.bill.domain.TbBill;
import com.mi.bill.mapper.TbBillMapper;
import com.mi.common.core.domain.Ztree;
import com.mi.common.core.text.Convert;
import com.mi.common.exception.BusinessException;
import com.mi.common.utils.DateUtils;
import com.mi.common.utils.StringUtils;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsBusiness.mapper.TbmaterialsMapper;
import com.mi.materialsBusiness.service.ITbmaterialsService;
import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import com.mi.materialsDetailBusiness.mapper.TbmaterialsDetailMapper;
import com.mi.materialsType.mapper.TbMaterialsTypeMapper;
import com.mi.supplierBusiness.mapper.TbSupplierMapper;
import com.mi.system.domain.SysUser;
import com.mi.warehouseBusiness.mapper.TbWarehouseMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 物资管理Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-12
 */
@Service
public class TbmaterialsServiceImpl implements ITbmaterialsService 
{
    private static final Logger log = LoggerFactory.getLogger(TbmaterialsServiceImpl.class);



    @Autowired
    private TbSupplierMapper tbSupplierMapper;
    @Autowired
    private TbmaterialsMapper tbmaterialsMapper;
    @Autowired
    private TbWarehouseMapper tbWarehouseMapper;
    @Autowired
    private TbMaterialsTypeMapper tbMaterialsTypeMapper;
    @Autowired
    TbmaterialsDetailMapper tbmaterialsDetailMapper;
    @Autowired
    TbBillMapper tbBillMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     *
     * @param tbmaterialsList 物资列表
     * @param updateSupport
     * @param operName
     * @return
     */
    @Override
    public String importMaterials(List<Tbmaterials> tbmaterialsList, boolean updateSupport, String operName) {

        if (StringUtils.isNull(tbmaterialsList) || tbmaterialsList.size() == 0)
        {
            throw new BusinessException("导入用户数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (Tbmaterials tbmaterials : tbmaterialsList)
        {
            try
            {
                this.insertTbmaterials(tbmaterials);
                successNum++;
                successMsg.append("<br/>" + successNum + "、物资 " + tbmaterials.getName() + " 导入成功");

            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、物资 " + tbmaterials.getName()+ " 导入失败：";
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
     * 出库以及报废
     * @param tbmaterials
     * @return
     */
    @Transactional
    @Override
    public Integer updateAmountById(Tbmaterials tbmaterials, SysUser user, Integer type, HttpSession session) {
        Integer checkAmount = tbmaterialsMapper.checkAmount(tbmaterials.getId());
        if(checkAmount>tbmaterials.getAmount()) {//如果库存足够
            Tbmaterials temp = tbmaterialsMapper.selectTbmaterialsById(tbmaterials.getId());
            TbmaterialsDetail t = new TbmaterialsDetail();
            t.setMaterialsId(tbmaterials.getId());
            t.setAmount(tbmaterials.getAmount());
            t.setCreateTime(DateUtils.getNowDate());
            t.setUserId(user.getUserId());
            t.setType(type);//设置状态
            TbBill bill=new TbBill();
            bill.setAcoumt(tbmaterials.getAmount());//物资数量
            bill.setHandler(user.getUserName());//操作员
            bill.setType(type);
            if(type==2){
                bill.setMoney(Double.valueOf(tbmaterials.getAmount()*temp.getDeliveryPrice()));//出库金额

            }else  if(type==3){
                bill.setMoney(Double.valueOf(tbmaterials.getAmount()*temp.getPutPrice()));//报废金额

            }
            bill.setMaterialsName(temp.getName());//物资名
            bill.setCreateTime(DateUtils.getNowDate());
            tbmaterialsDetailMapper.insertTbmaterialsDetail(t);//需要自增主键
            bill.setDetailId(t.getId());
            Integer i = tbBillMapper.insertTbBill(bill);//需要自增主键
            redisTemplate.opsForValue().set("bill"+bill.getId(),bill,30, TimeUnit.MINUTES);//存储到redis
            session.setAttribute("billId",bill.getId());
            synchronized (this){
                tbmaterialsMapper.updateAmountById(tbmaterials);//更新库存

            }
            return i;
        }else {
            return 0;
        }

    }

    /**
     * 查询物资
     * @return 物资列表
     */
    @Override
    public List<Ztree> selectMaterialsTree() {
      List<Tbmaterials> tbmaterials=  tbmaterialsMapper.selectMaterialsTree();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (Tbmaterials tbmaterials1 : tbmaterials) {

            Ztree ztree=new Ztree();
            ztree.setId(tbmaterials1.getId().longValue());
            ztree.setName(tbmaterials1.getName());
            ztree.setTitle(tbmaterials1.getName());
            ztrees.add(ztree);
        }
        return  ztrees;
    }
    /**
     * 查询物资管理
     * 
     * @param id 物资管理ID
     * @return 物资管理
     */
    @Override
    public Tbmaterials selectTbmaterialsById(Integer id)
    {
        Tbmaterials tbmaterials=  tbmaterialsMapper.selectTbmaterialsById(id);
        tbmaterials.setTbSupplier(tbSupplierMapper.selectTbSupplierById(tbmaterials.getSupplierId()));
        tbmaterials.setTbWarehouse(tbWarehouseMapper.selectTbWarehouseById(tbmaterials.getWarehouseId()));
        tbmaterials.setTbMaterialsType(tbMaterialsTypeMapper.selectTbMaterialsTypeById(tbmaterials.getTypeId()));
        return tbmaterials;
    }

    /**
     * 查询物资管理列表
     * 
     * @param tbmaterials 物资管理
     * @return 物资管理
     */
    @Override
    public List<Tbmaterials> selectTbmaterialsList(Tbmaterials tbmaterials)
    {
        List<Tbmaterials> tbmaterialsList = tbmaterialsMapper.selectTbmaterialsList(tbmaterials);
        Iterator<Tbmaterials> iterator = tbmaterialsList.iterator();
        while (iterator.hasNext()){
            Tbmaterials temp=iterator.next();
            temp.setTbSupplier(tbSupplierMapper.selectTbSupplierByMaterialsId(temp.getId()));
            temp.setTbMaterialsType(tbMaterialsTypeMapper.selectTbMaterialsTypeById(temp.getTypeId()));
        }
        return tbmaterialsList;
    }

    /**
     * 新增物资管理
     * 
     * @param tbmaterials 物资管理
     * @return 结果
     */
    @Override
    public int insertTbmaterials(Tbmaterials tbmaterials)
    {
        tbmaterials.setCreateTime(DateUtils.getNowDate());
        return tbmaterialsMapper.insertTbmaterials(tbmaterials);
    }

    /**
     * 修改物资管理
     * 
     * @param tbmaterials 物资管理
     * @return 结果
     */
    @Override
    public int updateTbmaterials(Tbmaterials tbmaterials)
    {
        tbmaterials.setUpdateTime(DateUtils.getNowDate());
        return tbmaterialsMapper.updateTbmaterials(tbmaterials);
    }

    /**
     * 删除物资管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbmaterialsByIds(String ids)
    {
        return tbmaterialsMapper.deleteTbmaterialsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物资管理信息
     * 
     * @param id 物资管理ID
     * @return 结果
     */
    public int deleteTbmaterialsById(Integer id)
    {
        return tbmaterialsMapper.deleteTbmaterialsById(id);
    }
}
