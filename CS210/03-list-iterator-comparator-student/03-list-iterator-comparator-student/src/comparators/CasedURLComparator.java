/*
 * Copyright 2023 Marc Liberatore.
 */

package comparators;

import java.util.Comparator;
import java.util.Objects;

/**
 * A comparator to determine the order of two web pages. The two web pages are
 * compared lexicographically. However, if the CasedURLComparator is created
 * with ignoreCase set to true, then the comparison should be case-insensitive.
 */
public class CasedURLComparator implements Comparator<WebPageRecord> {
    final boolean ignoreCase;

    public CasedURLComparator(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public int compare(WebPageRecord x, WebPageRecord y) {
        // PART 3 Comparators
        // TASK: Implement the compare method to compare two WebPageRecord
        // objects based on their URL. Consider whether case should be ignored
        // or not when performing the comparison.
        String xURL = x.URL;
        String yURL = y.URL;

        if (this.ignoreCase == true) {
            xURL = xURL.toLowerCase();
            yURL = yURL.toLowerCase();
        }
        
        if (xURL != null) { 
            return xURL.compareTo(yURL);
        } else { // xURL is null 
            return xURL == yURL ? 0 : yURL.compareTo(xURL); // if xURL and yURL are null then return 0, else just do .compareTo 
        }
    }

}