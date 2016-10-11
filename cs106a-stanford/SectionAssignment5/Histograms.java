import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Histograms {
    private static final String INPUT_FILENAME = "MidtermScores.txt";
    private static final int MAX_VALUE = 100;
    private static final int VALUE_RANGE = 10;

    private String[] histogramRanges;
    private BufferedReader reader;

    public static void main(String[] args) {
        Histograms histograms = new Histograms();
        histograms.run();
    }

    private void run() {
        initializeHistogram();
        readHistogramData();
        displayHistogram();
    }

    private void initializeHistogram() {
        histogramRanges = new String[MAX_VALUE / VALUE_RANGE + 1];
        Arrays.fill(histogramRanges, "");
    }

    private void readHistogramData() {
        try {
            reader = new BufferedReader(new FileReader(INPUT_FILENAME));
            String line = null;
            while ((line = reader.readLine()) != null) {
                int value = Integer.parseInt(line);
                histogramRanges[value / VALUE_RANGE] += "*";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayHistogram() {
        int histogramRangeStart = 0;
        int histogramRangeEnd = histogramRangeStart + VALUE_RANGE - 1;

        for (String histogramRange : histogramRanges) {
            String histogramRangeLabel = String.format("%02d-%02d", histogramRangeStart, histogramRangeEnd);

            System.out.printf("%s: %s\n", histogramRangeLabel, histogramRange);

            histogramRangeStart += VALUE_RANGE;
            histogramRangeEnd += VALUE_RANGE;
        }
    }
}
