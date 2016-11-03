
/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.ArrayList;
import java.util.List;

public class NameSurferEntry implements NameSurferConstants {
    private List<Integer> decades;
    private String name;

    /**
     * Creates a new NameSurferEntry from a data line as it appears in the data
     * file. Each line begins with the name, which is followed by integers
     * giving the rank of that name for each decade.
     */
    public NameSurferEntry(String line) {
        String[] splitLine = line.split("\\s+");
        name = splitLine[0];

        decades = new ArrayList<Integer>();
        for (int i = 1; i < splitLine.length; i++) {
            decades.add(Integer.parseInt(splitLine[i]));
        }
    }

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the rank associated with an entry for a particular decade. The
     * decade value is an integer indicating how many decades have passed since
     * the first year in the database, which is given by the constant
     * START_DECADE. If a name does not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {
        if (decade < decades.size()) {
            return decades.get(decade);
        }

        return 0;
    }

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    @Override
    public String toString() {
        return String.format("%s %s", name, decades);
    }
}
