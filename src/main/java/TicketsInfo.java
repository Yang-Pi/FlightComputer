import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class TicketsInfo {
    /* Ticket info example
        "origin": "VVO",
        "origin_name": "Владивосток",
        "destination": "TLV",
        "destination_name": "Тель-Авив",
        "departure_date": "12.05.18",
        "departure_time": "16:20",
        "arrival_date": "12.05.18",
        "arrival_time": "22:10",
        "carrier": "TK",
        "stops": 3,
        "price": 12400
     */
    public static List<Long> computeFlightTime(JSONArray ticketsFullInfo) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.M.yyyy hh:mm");
        List<Long> flightTimes = new ArrayList<>();

        for (Object ticketObject : ticketsFullInfo) {
            JSONObject ticket = (JSONObject) ticketObject;
            try {
                String origin = ticket.get("origin").toString();
                String destination = ticket.get("destination").toString();

                if (!origin.equals("VVO") && !destination.equals("TLV")) {
                    return Collections.emptyList();
                }

                Date departureDate = sdf.parse( formatDateFromShortYearToFullYear(ticket.get("departure_date").toString()) + " " + ticket.get("departure_time"));
                Date arrivalDate = sdf.parse(formatDateFromShortYearToFullYear(ticket.get("arrival_date").toString()) + " " + ticket.get("arrival_time"));

                flightTimes.add(Math.abs(new Date(arrivalDate.getTime() -  departureDate.getTime()).getTime()) / 60000); //milliseconds to minutes

            } catch (java.text.ParseException e) {
                e.printStackTrace();
            }
        }

        return flightTimes;
    }

    public static String formatDateFromShortYearToFullYear(String shortDate) {
        StringBuffer fullDate = new StringBuffer(shortDate);
        return fullDate.insert(shortDate.length() - 2, "20").toString();
    }
}
