package com.mi.BulletinBusiness.mapper;

import org.apache.ibatis.annotations.Select;

public interface BulletinMapper {
    /**
     * 本月入库物资价格总和
     * @return
     */
    @Select("select SUM(tb_materials.put_price*tb_materials_detail.amount) as putPriceCount from tb_materials,tb_materials_detail where tb_materials.ID=tb_materials_detail.materials_id and tb_materials_detail.type=1 and DATE_FORMAT( tb_materials_detail.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )")
    Double ThisMonthPutPriceCount();

    /**
     * 本月出库商品价值总和
     * @return
     */
    @Select("select SUM(tb_materials.delivery_price*tb_materials_detail.amount) as deliveryPrice from tb_materials,tb_materials_detail where tb_materials.ID=tb_materials_detail.materials_id and tb_materials_detail.type=2 and DATE_FORMAT( tb_materials_detail.create_time, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )")
    Double ThisMonthDeliveryPrice();

    /**
     * 上月入库物资价格总和
     * @return
     */
    @Select("select SUM(tb_materials.put_price*tb_materials_detail.amount) as putPriceCount from tb_materials,tb_materials_detail where tb_materials.ID=tb_materials_detail.materials_id and tb_materials_detail.type=1 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( tb_materials_detail.create_time, '%Y%m' ) ) =1")
    Double lastMonthPutPriceCount();

    /**
     * 上月出库商品价值总和
     * @return
     */
    @Select("select SUM(tb_materials.delivery_price*tb_materials_detail.amount) as deliveryPrice from tb_materials,tb_materials_detail where tb_materials.ID=tb_materials_detail.materials_id and tb_materials_detail.type=2 and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format( tb_materials_detail.create_time, '%Y%m' ) ) =1")
    Double lastMonthDeliveryPrice();

}
