import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double percent = 90.;

        try {
            List<Long> flightTimes = TicketsInfo.computeFlightTime(FileUtils.getJsonArrayFromFile("tickets.json", "tickets"));

            List<Double> computationsRes = new ArrayList<>();
            computationsRes.add(MathUtils.computeAvg(flightTimes));
            computationsRes.add(MathUtils.computePercentile(flightTimes, percent));

            System.out.println(computationsRes);
            //FileUtils.writeComputationsToFile((ArrayList<Double>) computationsRes, "results.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
