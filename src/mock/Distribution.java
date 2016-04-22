package mock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by schoen on 4/21/16.
 */
public class Distribution {
    private int startIndex;
    private int stopIndex;
    private ArrayList<Integer> distributionListX = new ArrayList<>();
    private ArrayList<Integer> distributionListY = new ArrayList<>();


    public Distribution(String input) {
        try {
            parseDistribution(input);
        } catch (Exception e) {
            System.out.println("error parsing fastqc distribution file:");
            e.printStackTrace();
        }
    }

    private void parseDistribution(String input) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(input));
        while (reader.ready()) {
            String[] line = reader.readLine().split("\t");
            int xMax = Integer.valueOf(line[0].split("-")[1]);
            int xMin = Integer.valueOf(line[0].split("-")[0]);
            int range = xMax - xMin + 1;
            int value = Double.valueOf(line[1]).intValue() / range;
            for (int i = xMin; i <= xMax; i++) {
                distributionListX.add(i);
                distributionListY.add(value);
            }
        }
        startIndex = distributionListX.get(0);
        stopIndex = distributionListX.get(distributionListX.size() - 1);
    }

    private int function(int i) {
        return distributionListY.get(i - startIndex);
    }

    int drawFromDistribution() {
        double randomMultiplier = 0;
        for (int i = startIndex; i <= stopIndex; i++) {
            randomMultiplier += function(i);
        }

        Random r = new Random();
        double randomDouble = r.nextDouble() * randomMultiplier;

        int randomFromDistribution = startIndex;
        randomDouble = randomDouble - function(randomFromDistribution);
        while (randomDouble >= 0) {
            randomFromDistribution++;
            randomDouble = randomDouble - function(randomFromDistribution);
        }

        return randomFromDistribution;
    }
}
