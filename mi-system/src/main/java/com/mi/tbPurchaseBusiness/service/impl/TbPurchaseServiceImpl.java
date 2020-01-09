package com.mi.tbPurchaseBusiness.service.impl;

import com.mi.bill.domain.TbBill;
import com.mi.bill.mapper.TbBillMapper;
import com.mi.common.core.text.Convert;
import com.mi.common.utils.DateUtils;
import com.mi.materialsBusiness.domain.Tbmaterials;
import com.mi.materialsBusiness.mapper.TbmaterialsMapper;
import com.mi.materialsDetailBusiness.domain.TbmaterialsDetail;
import com.mi.materialsDetailBusiness.mapper.TbmaterialsDetailMapper;
import com.mi.requirementsPlan.domain.TbRequirementsPlan;
import com.mi.requirementsPlan.mapper.TbRequirementsPlanMapper;
import com.mi.system.domain.SysUser;
import com.mi.system.mapper.SysUserMapper;
import com.mi.tbPurchaseBusiness.domain.TbPurchase;
import com.mi.tbPurchaseBusiness.mapper.TbPurchaseMapper;
import com.mi.tbPurchaseBusiness.service.ITbPurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 采购计划Service业务层处理
 * 
 * @author lhp
 * @date 2019-11-13
 */
@Service
public class TbPurchaseServiceImpl implements ITbPurchaseService 
{
    @Autowired
    private TbPurchaseMapper tbPurchaseMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TbRequirementsPlanMapper tbRequirementsPlanMapper;
    @Autowired
    private TbmaterialsMapper tbmaterialsMapper;
    @Autowired
    private TbmaterialsDetailMapper tbmaterialsDetailMapper;
    @Autowired
    TbBillMapper tbBillMapper;
    @Autowired
    RedisTemplate redisTemplate;
    /**
     * 查询采购计划
     * 
     * @param id 采购计划ID
     * @return 采购计划
     */
    @Override
    public TbPurchase selectTbPurchaseById(Integer id)
    {
        TbPurchase tbPurchase=tbPurchaseMapper.selectTbPurchaseById(id);
        tbPurchase.setTbRequirementsPlan(tbRequirementsPlanMapper.selectTbRequirementsPlanById(tbPurchase.getPlanId()));
        tbPurchase.setBuyer(sysUserMapper.selectUserById(tbPurchase.getUserId().longValue()));
        return tbPurchase;
    }

    /**
     * 查询采购计划列表
     * 
     * @param tbPurchase 采购计划
     * @return 采购计划
     */
    @Override
    public List<TbPurchase> selectTbPurchaseList(TbPurchase tbPurchase)
    {
        List<TbPurchase> tbPurchases=tbPurchaseMapper.selectTbPurchaseList(tbPurchase);
        Iterator<TbPurchase> iterator = tbPurchases.iterator();
        System.err.println(tbPurchases);
        if(tbPurchases.size()>0) {
            while (iterator.hasNext()) {
                TbPurchase next = iterator.next();
                next.setTbRequirementsPlan(tbRequirementsPlanMapper.selectTbRequirementsPlanById(next.getPlanId()));//获取采购计划的信息
                if(next.getTbRequirementsPlan()!=null)
                next.getTbRequirementsPlan().setTbmaterials(tbmaterialsMapper.selectTbmaterialsById(next.getTbRequirementsPlan().getMaterialsId()));
                next.setBuyer(sysUserMapper.selectUserById(next.getUserId().longValue()));
            }
        }
        return tbPurchases;
    }

    /**
     * 新增采购计划
     * 
     * @param tbPurchase 采购计划
     * @return 结果
     */
    @Transactional
    @Override
    public int insertTbPurchase(TbPurchase tbPurchase)
    {
        tbPurchase.setCreateTime(DateUtils.getNowDate());
        TbRequirementsPlan tbRequirementsPlan=new TbRequirementsPlan();
        tbRequirementsPlan.setId(tbPurchase.getPlanId());
        tbRequirementsPlan.setStatus(1);//改变采购计划状态为采购中
        int res =tbRequirementsPlanMapper.updateTbRequirementsPlan(tbRequirementsPlan);//更新采购计划
        return tbPurchaseMapper.insertTbPurchase(tbPurchase);
    }

    /**
     * 修改采购计划
     * 修改物资库存量
     * @param tbPurchase 采购计划
     * @return 结果
     */

    @Transactional
    @Override
    public int updateTbPurchase(TbPurchase tbPurchase,Long userId)
    {
        return tbPurchaseMapper.updateTbPurchase(tbPurchase);
    }

    /**
     * 删除采购计划对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbPurchaseByIds(String ids)
    {
        return tbPurchaseMapper.deleteTbPurchaseByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除采购计划信息
     * 
     * @param id 采购计划ID
     * @return 结果
     */
    public int deleteTbPurchaseById(Integer id)
    {
        return tbPurchaseMapper.deleteTbPurchaseById(id);
    }

    /**
     *
     * @param id 采购计划ID
     * @param status 采购计划状态
     * @param materialsId 物资ID
     * @param amount 采购数量
     * @return
     */
    @Transactional
    @Override
    public int accept(Integer id, Integer status, Integer materialsId, Integer amount, Integer planId, SysUser user, HttpSession session) {
        TbBill bill=new TbBill();

        TbmaterialsDetail detail=new TbmaterialsDetail();
        detail.setCreateTime(DateUtils.getNowDate());
        detail.setUserId(user.getUserId());
        detail.setMaterialsId(materialsId);
        detail.setAmount(amount);
        TbRequirementsPlan tbRequirementsPlan=new TbRequirementsPlan();
        tbRequirementsPlan.setId(planId);
        Tbmaterials tbmaterials = tbmaterialsMapper.selectTbmaterialsById(materialsId);
        bill.setAcoumt(amount);
        bill.setMoney(Double.valueOf(tbmaterials.getPutPrice()*amount));
        if(status==1){
            status=1;//验收
            tbRequirementsPlan.setStatus(2);//更新需求计划为已完成
            detail.setType(1);//设置状态为入库
            bill.setType(1);

        }
       else if(status==2){
            amount=-amount;//退回
            tbRequirementsPlan.setStatus(1);//更新需求计划为待采购
            detail.setType(0);//设置状态为退回
            bill.setType(0);
        }
        TbPurchase t=new TbPurchase();
        t.setId(id);
        t.setStatus(status);
        tbmaterialsDetailMapper.insertTbmaterialsDetail(detail);//保存库存变动信息
        synchronized (this){//增加同步锁，避免库存覆盖
            tbmaterialsMapper.updateAmount(materialsId,amount);//更新物资库存
        }
        tbRequirementsPlanMapper.updateTbRequirementsPlan(tbRequirementsPlan);//更新需求计划信息
        bill.setDetailId(detail.getId());
        bill.setMaterialsName(tbmaterials.getName());


        bill.setCreateTime(DateUtils.getNowDate());
        bill.setHandler(user.getUserName());
        tbBillMapper.insertTbBill(bill);
        redisTemplate.opsForValue().set("bill"+bill.getId(),bill,30, TimeUnit.MINUTES);//存储到redis
        session.setAttribute("billId",bill.getId());
        return tbPurchaseMapper.updateTbPurchase(t);//更新采购计划信息
    }
}
