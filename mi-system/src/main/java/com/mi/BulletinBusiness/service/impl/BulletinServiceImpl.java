package com.mi.BulletinBusiness.service.impl;

import com.alibaba.fastjson.JSON;
import com.mi.BulletinBusiness.domain.Bulletin;
import com.mi.BulletinBusiness.mapper.BulletinMapper;
import com.mi.BulletinBusiness.service.BulletinService;
import com.mi.materialsDetailBusiness.mapper.TbmaterialsDetailMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;

@Service
public class BulletinServiceImpl implements BulletinService {
    @Autowired
    BulletinMapper bulletinMapper;
    @Autowired
    TbmaterialsDetailMapper tbmaterialsDetailMapper;
    @Transactional
    @Override
    public String LastThirtyDaysProfitCount() {
        Map<String,Object> map=new HashMap<>();
        Bulletin thisMonthBulletin = this.monthProfitBulletin();
        Bulletin monthPutBulletin=this.monthPutBulletin();
        Bulletin monthDeliveryBulletin=this.monthDeliveryBulletin();
        Map<String,Integer> count=new HashMap<>();
        Integer thisMonthcount=tbmaterialsDetailMapper.thisMonthcount();
        Integer lastMonthcount = tbmaterialsDetailMapper.lastMonthcount();
        count.put("thisMonth",thisMonthcount);
        count.put("lastMonth",lastMonthcount);
        map.put("thisMonthBulletin",thisMonthBulletin);
        map.put("monthPutBulletin",monthPutBulletin);
        map.put("monthDeliveryBulletin",monthDeliveryBulletin);
        map.put("thisMonthCount",count);
        return JSON.toJSONString(map);
    }

    /**
     * 本月收入额数据
     * @return
     */
    public Bulletin monthProfitBulletin(){
        Bulletin bulletin=new Bulletin();
        //本月物资收支数据
        Double ThisMonthPutPriceCount = bulletinMapper.ThisMonthPutPriceCount();//本月采购额
        Double ThisMonthDeliveryPrice = bulletinMapper.ThisMonthDeliveryPrice();//本月收入额
        bulletin.setPutPriceCount(ThisMonthPutPriceCount==null?0D:ThisMonthPutPriceCount);
        bulletin.setDeliveryPrice(ThisMonthDeliveryPrice==null?0D:ThisMonthDeliveryPrice);
        bulletin.setProfit();//计算本月收入
        Double lastMonthPutPriceCount = bulletinMapper.lastMonthPutPriceCount();//上月采购额
        Double lastMonthDeliveryPrice = bulletinMapper.lastMonthDeliveryPrice();//上月收入额
        bulletin.setLastProfit(lastMonthPutPriceCount==null?0D:lastMonthPutPriceCount,lastMonthDeliveryPrice==null?0D:lastMonthDeliveryPrice);//计算上月收入
        return bulletin;
    }
    /**
     * 本月入库额数据
     */
    public Bulletin monthPutBulletin(){
        Bulletin bulletin=new Bulletin();
        //本月物资入库数据
        Double ThisMonthPutPriceCount = bulletinMapper.ThisMonthPutPriceCount();//本月采购额
        Double lastMonthPutPriceCount = bulletinMapper.lastMonthPutPriceCount();//上月采购额
        bulletin.setPutPriceCount(ThisMonthPutPriceCount==null?0D:ThisMonthPutPriceCount);
        bulletin.setLastPutPriceCount(lastMonthPutPriceCount==null?0D:lastMonthPutPriceCount);
        return bulletin;
    }

    /**
     * 本月物资出库数据
     */
    public Bulletin monthDeliveryBulletin(){
        Bulletin bulletin=new Bulletin();
        //本月物资出库数据
        Double ThisMonthDeliveryPrice = bulletinMapper.ThisMonthDeliveryPrice();//本月出库额
        Double lastMonthDeliveryPrice = bulletinMapper.lastMonthDeliveryPrice();//上月出库额
        bulletin.setDeliveryPrice(ThisMonthDeliveryPrice==null?0D:ThisMonthDeliveryPrice);
        bulletin.setLastDeliveryPrice(lastMonthDeliveryPrice==null?0D:lastMonthDeliveryPrice);
        return bulletin;
    }
    public Bulletin getPecentage(Bulletin bulletin,Double a,Double b){
        String percentage="";
        Double res=a-b;//本时段和上时段差额
        NumberFormat nf  =  NumberFormat.getPercentInstance();
        nf.setMinimumFractionDigits( 2 );
        if(res>0){
            bulletin.setMsgType((short) 0);
            percentage  =  nf.format((res)/b);
        }else if(res==0D){
            bulletin.setMsgType((short) 2);
            percentage="0%";
        }else {
            bulletin.setMsgType((short) 1);
            if(a-b==0D){
                percentage=res+"%";
            }
            else{
                percentage  =  nf.format((res)/b);
            }
        }
        bulletin.setPercentage(percentage);
        return bulletin;
    }
}
