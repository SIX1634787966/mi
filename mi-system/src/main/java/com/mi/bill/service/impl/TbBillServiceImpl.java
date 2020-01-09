package com.mi.bill.service.impl;

import java.util.List;
import com.mi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mi.bill.mapper.TbBillMapper;
import com.mi.bill.domain.TbBill;
import com.mi.bill.service.ITbBillService;
import com.mi.common.core.text.Convert;

/**
 * 凭条Service业务层处理
 * 
 * @author lhp
 * @date 2019-12-19
 */
@Service
public class TbBillServiceImpl implements ITbBillService 
{
    @Autowired
    private TbBillMapper tbBillMapper;

    /**
     * 查询凭条
     * 
     * @param id 凭条ID
     * @return 凭条
     */
    @Override
    public TbBill selectTbBillById(Long id)
    {
        return tbBillMapper.selectTbBillById(id);
    }

    /**
     * 查询凭条列表
     * 
     * @param tbBill 凭条
     * @return 凭条
     */
    @Override
    public List<TbBill> selectTbBillList(TbBill tbBill)
    {
        return tbBillMapper.selectTbBillList(tbBill);
    }

    /**
     * 新增凭条
     * 
     * @param tbBill 凭条
     * @return 结果
     */
    @Override
    public int insertTbBill(TbBill tbBill)
    {
        tbBill.setCreateTime(DateUtils.getNowDate());
        return tbBillMapper.insertTbBill(tbBill);
    }

    /**
     * 修改凭条
     * 
     * @param tbBill 凭条
     * @return 结果
     */
    @Override
    public int updateTbBill(TbBill tbBill)
    {
        tbBill.setUpdateTime(DateUtils.getNowDate());
        return tbBillMapper.updateTbBill(tbBill);
    }

    /**
     * 删除凭条对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTbBillByIds(String ids)
    {
        return tbBillMapper.deleteTbBillByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除凭条信息
     * 
     * @param id 凭条ID
     * @return 结果
     */
    public int deleteTbBillById(Long id)
    {
        return tbBillMapper.deleteTbBillById(id);
    }
}
