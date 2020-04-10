package com.xjtu.happy.ticket.mapper.management;

import com.xjtu.happy.ticket.bean.TicketSeat;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.security.Provider;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

@Mapper
public interface TicketSeatMapper {
    @Select("select * from ticketseat where seatId = #{seatid}")
    public TicketSeat FindTicketSeatById(int seatid);

    @InsertProvider(type = Provider.class, method = "InsertTicketSeats")
    public int InsertTicketSeats(List<TicketSeat> ticketSeats);

    @Select("select count(*) from ticketseat")
    public int CountOfSeat();

    class Provider {
        /* 批量插入 */
        public String InsertTicketSeats(Map map) {
            List<TicketSeat> ticketSeats = (List<TicketSeat>) map.get("list");
            StringBuilder sb = new StringBuilder();
            sb.append("INSERT INTO ticketseat (seatType,seatNo,trainId,travelTime,ticketSeatStatus) VALUES ");
            MessageFormat mf = new MessageFormat(
                    "(#'{'list[{0}].seatType}, #'{'list[{0}].seatNo}, #'{'list[{0}].trainId}, #'{'list[{0}].travelTime}, #'{'list[{0}].ticketSeatStatus})"
            );

            for (int i = 0; i < ticketSeats.size(); i++) {
                sb.append(mf.format(new Object[]{i}));
                if (i < ticketSeats.size() - 1)
                    sb.append(",");
            }
            return sb.toString();
        }
    }
}
