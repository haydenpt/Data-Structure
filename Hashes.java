public class Hashes {
    public int hashCode(String s) {
        // Constant to multiply with unicode
        int g = 31;
        int hash = 0;
        /*
            t: 116
            h: 104
            i: 105
            s: 115
         */
        for (int i = 0; i < s.length(); i++) {
            /* ((116g + 104)g + 105)g + 115
                116g^3 + 104g^2 + 105g + 115 (siht)
             */
            hash = g * hash + s.charAt(i);
        }
        /*
        This is inversed of the above
        for (int i = s.length() - 1; i >= 0; i--) {
            // 116 + g(104 + g(105 + g(115)))
            // 116g^0 + 104g^1 + 105g^2 + 115g^3
            hash = g * hash + s.charAt(i);
        }

         */
        return hash;
    }
}

/*
int hashVal = data.hashCode(s);

hashVal = hashVal & 0x7FFFFFFF; // guarantee positive number

hashVal = hashVal % tableSize; // guarantee within the size of the table
 */