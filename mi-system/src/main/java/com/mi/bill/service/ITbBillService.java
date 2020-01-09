package com.mi.bill.service;

import com.mi.bill.domain.TbBill;
import java.util.List;

/**
 * 凭条Service接口
 * 
 * @author lhp
 * @date 2019-12-19
 */
public interface ITbBillService 
{
    /**
     * 查询凭条
     * 
     * @param id 凭条ID
     * @return 凭条
     */
    public TbBill selectTbBillById(Long id);

    /**
     * 查询凭条列表
     * 
     * @param tbBill 凭条
     * @return 凭条集合
     */
    public List<TbBill> selectTbBillList(TbBill tbBill);

    /**
     * 新增凭条
     * 
     * @param tbBill 凭条
     * @return 结果
     */
    public int insertTbBill(TbBill tbBill);

    /**
     * 修改凭条
     * 
     * @param tbBill 凭条
     * @return 结果
     */
    public int updateTbBill(TbBill tbBill);

    /**
     * 批量删除凭条
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteTbBillByIds(String ids);

    /**
     * 删除凭条信息
     * 
     * @param id 凭条ID
     * @return 结果
     */
    public int deleteTbBillById(Long id);
}
