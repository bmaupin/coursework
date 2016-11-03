
/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class NameSurferDataBase implements NameSurferConstants {
    private Map<String, NameSurferEntry> entriesByName;

    /**
     * Creates a new NameSurferDataBase and initializes it using the data in the
     * specified file. The constructor throws an error exception if the
     * requested file does not exist or if an error occurs as the file is being
     * read.
     */
    public NameSurferDataBase(String filename) {
        entriesByName = new HashMap<String, NameSurferEntry>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                NameSurferEntry entry = new NameSurferEntry(line.trim());
                entriesByName.put(entry.getName().toLowerCase(), entry);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    /**
     * Returns the NameSurferEntry associated with this name, if one exists. If
     * the name does not appear in the database, this method returns null.
     */
    public NameSurferEntry findEntry(String name) {
        return entriesByName.get(name.toLowerCase());
    }
}
