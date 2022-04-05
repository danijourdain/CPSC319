public class HashTable {
    private String[] table;
    private int capacity;

    public HashTable(int capacity) {
        this.capacity = capacity + capacity / 3;
        //increase the capacity t0 1.33 times its original size

        if(!isPrime(this.capacity)) {
            this.capacity = findPrime();
            //then find the nearest prime number to the new capacity
            //if it isn't already prime
        }

        this.table = new String[this.capacity];
        for(int i = 0; i < this.capacity; i++) {
            this.table[i] = null;
            //fill the entire table will null values
        }
    }

    private boolean isPrime(int size) {
        /* prime number calculation adapted from https://www.tutorialspoint.com/java-program-to-check-for-prime-and-find-next-prime-in-java */
        
        for(int i = 2; i < size; i++) {
            if(size % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int findPrime() {
        /* prime number calculation adapted from https://www.tutorialspoint.com/java-program-to-check-for-prime-and-find-next-prime-in-java */

        int size = this.capacity + 1;
        for(int i = 2; i < size; i++) {
            if(size % i == 0) {
                size++;
                i = 2;
            }
        }

        return size;
    }

    public void insert(String item) {
        int spot = hashFunction(item);
        //use the hash function to find the spot to insert the item
        if(this.table[spot] != null) {
            spot = probe(spot);
            //if the spot is full, probe to find a new spot
        }

        //System.out.println("Inserting " + item + " at " + spot + ".");

        table[spot] = item;
        //insert the item
    }

    private int hashFunction(String item) {
        /* ATTEMPT 5: 

        /* ATTEMPT 4: djb2 -> 37.49% w/ lf of 74%. 49.99% w/ lf of 49.99% 
        http://www.cse.yorku.ca/~oz/hash.html#:~:text=If%20you%20just%20want%20to,Also%20see%20tpop%20pp. */
        char[] itemArray = item.toCharArray();
        int hash = 0;
        int c;
        for(int i = 0; i < itemArray.length; i++) {
            c = itemArray[i];
            hash = (c + hash * 33) % this.capacity;
        }

        return (int) hash;


        /* ATTEMPt 3: sdbm -> 37.49% 
        char[] itemArray = item.toCharArray();
        int hash = 0;
        int c;
        for(int i = 0; i < itemArray.length; i++) {
            c = itemArray[i];
            hash = Math.abs(c + hash * 65599);
        }

        return (int) hash % this.capacity;
        
        /* ATTEMPT 2: https://cseweb.ucsd.edu/~kube/cls/100/Lectures/lec16/lec16-15.html -> 37.48% 

        char[]value = item.toCharArray();
        int h = 0;
		int off = 0;
		char val[] = value;
		int len = value.length;
 
		for (int i = 0; i < len; i++)
	    h = 31*h + val[off++];
 
		return Math.abs(h) % this.capacity;

        /* ATTEMPT 1: folding and adding -> only about 7.5% hash efficiency at best
        char[] itemArray = item.toCharArray();
        int size = itemArray.length;
        if(size % 2 != 0) {
            size++;
        }

        int[] asciiValues = new int[size];
        for(int i = 0 ; i < size; i++) {
            if(i < itemArray.length) {
                asciiValues[i] = itemArray[i];
            }
            else {
                asciiValues[i] = 0;
            }
        }

        int sum = 0;
        for(int i = 0; i + 1 < size; i += 2) {
            sum += (asciiValues[i] * 100 + asciiValues[i + 1]) % this.capacity;
        }

        if(sum < 0 || sum >= this.capacity) {
            sum = sum % this.capacity;
        }

        return sum;
        */
    }

    private int probe(int start) {
        while(this.table[start] != null) {
            start = (start + 1) % this.capacity;
            //increment the index modulo capacity of the table until an empty spot is found
        }

        return start;
    }

    public String analyzePerformance() {
        int counter = 0;
        int numReads = 0;

        for(String item: this.table) {
            if(item == null) {
                counter++;
                //counts the number of null spots
            }
            else {
                numReads += find(item);
                //counts the number of reads to find each object
            }
        }

        int numRecords = this.capacity - counter;   //number of records in the table
        double loadFactor = (double)numRecords / this.capacity;    //load factor
        double hashEfficiency = loadFactor / (numReads/numRecords);    //hash efficiency
        System.out.println("Load factor is " + loadFactor + " or " + loadFactor * 100 + "%.\n");
        System.out.println("Hash efficiency is " + hashEfficiency + " or " + hashEfficiency * 100 + "%.");

        StringBuilder result = new StringBuilder();
        result.append("Load factor is " + loadFactor + " or " + loadFactor * 100 + "%.\n");
        result.append("Hash efficiency is " + hashEfficiency + " or " + hashEfficiency * 100 + "%.");

        return result.toString();
    }

    public int find(String item) {
        int spot = hashFunction(item);
        //find the ideal spot for the item

        if(this.table[spot].equals(item)) {
            return 1;
            //if the item is in this spot, return 1
        }
        else {
            int newSpot = spot;
            int counter = 1;
            while(this.table[newSpot].equals(item) == false) {
                newSpot = (newSpot + 1) % this.capacity;
                counter++;
                //increment a counter for the number of reads
                if(newSpot == spot) {
                    System.out.println("Could not find item in table. Program terminating");
                    System.exit(1);
                }
            }
            
            return counter;
        }
    }
}
