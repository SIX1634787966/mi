package com.mi.statistics.mapper;

import com.mi.statistics.domain.MaterialsDetailStatistics;
import com.mi.statistics.domain.TbStatistics;
import org.apache.ibatis.annotations.SelectProvider;

import java.text.SimpleDateFormat;
import java.util.List;

public interface MaterialsDetailStatisticsMapper {
    @SelectProvider(method = "getStatisticsSqlByTime",type = MaterialsDetailStatisticsSql.class)
    List<MaterialsDetailStatistics> statistics(TbStatistics tbStatistics);


    class MaterialsDetailStatisticsSql{
        public String getStatisticsSqlByTime(TbStatistics tbStatistics){
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
            String begin="'"+simpleDateFormat.format(tbStatistics.getBeginTime())+"'";
            String end="'"+simpleDateFormat.format(tbStatistics.getEndTime())+"'";
            String time=" AND b.create_time BETWEEN "+begin+" and "+end;
            StringBuffer sql=new StringBuffer();
            sql.append("SELECT ")
            .append("(SELECT SUM(b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=1 ").append(time)
                    .append(" )as putNumber,")
            .append("(SELECT SUM(a.put_price*b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=1").append(time)
                    .append(" )as putMoney,")
            .append("(SELECT SUM(b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=2").append(time)
                    .append(" )as deliveryNumber,")
            .append("(SELECT SUM(a.delivery_price*b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=2").append(time)
                    .append(" )as deliveryMoney,")
            .append("(SELECT SUM(b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=0").append(time)
                    .append(" )as backNumber,")
            .append("(SELECT SUM(a.put_price*b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=0").append(time)
                    .append(" )as backMoney,")
            .append("(SELECT SUM(b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=3").append(time)
                    .append(" )as scrapNumber,")
            .append("(SELECT SUM(a.put_price*b.amount)  from tb_materials a,tb_materials_detail b where a.ID=b.materials_id and b.type=3").append(time)
                    .append(" )as scrapMoney")
            .append(" FROM DUAL");

            return sql.toString();
        }
    }
}
