public class HashTable {
    private String[] table;
    private int capacity;
    private int actSize;

    /**
     * Constructor for HashTable class.
     * @param size The number of items in the table
     */
    public HashTable(int size) {
        this.actSize = 0;
        this.capacity = (int) (1.4 * (double) size);
        //increase the capacity to 1.4 * the number of items

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

    /**
     * Helper method to check if a number is prime.
     * @param size The current size of the hashmap.
     * @return true if the size is prime, false otherwise.
     */
    private boolean isPrime(int size) {
        /* prime number calculation adapted from https://www.tutorialspoint.com/java-program-to-check-for-prime-and-find-next-prime-in-java */
        
        for(int i = 2; i < size; i++) {
            if(size % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Helper method to calculate the nearest, larger prime number for a given integer.
     * @param size The integer to find the next prime number for.
     * @return The next larger prime number.
     */
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

    /**
     * This method inserts an item into the HashMap.
     * @param item the element to insert.
     */
    public void insert(String item) {
        int spot = hashFunction(item);

        //use the hash function to find the spot to insert the item
        if(this.table[spot] != null) {
            spot = probe(spot);
            //if the spot is full, probe to find a new spot
        }

        table[spot] = item;
        //insert the item
        this.actSize++;
        //increment the size to keep track of how many elements are in the hashmap
    }

    /**
     * This is the Hash function for the HashMap.
     * @param item The item to find a spot for in the map.
     * @return The ideal spot for the item
     */
    private int hashFunction(String item) {
        //JENKINS ONE-AT-A-TIME HASH: https://en.wikipedia.org/wiki/Jenkins_hash_function 
        int hash, i;
        char[] itemArray = item.toCharArray();

        for(hash = i = 0; i < itemArray.length; i++) {
            hash += itemArray[i];   //add the ascii value of the current char to the hash
            hash += (hash << 10);   //shift the hash 10 bits to the right and add it to the current value
            hash ^= (hash >> 6);    //shift the hash 6 bits to the left and XOR it with the current value
        }

        hash += (hash << 3);    //shift the hash 3 bits to the right and add it to the current value
        hash ^= (hash >> 11);   //shift the hash 11 bits to the left and XOR it with the current value
        hash += (hash << 15);   //shift the hash 15 bits to the right and add it to the current value

        hash %= this.capacity;  //ensure the hash is between 0 and capacity - 1

        if(hash < 0) {
            hash += this.capacity;  //if the hash is negative, add the capacity to get it in the desired range
        }

        return hash;
    }

    /**
     * Helper method to use linear probing if a spot is full.
     * @param start The index of the table to begin probing
     * @return the first open spot using linear probing
     */
    private int probe(int start) {
        while(this.table[start] != null) {
            start = (start + 1) % this.capacity;
            //increment the index modulo capacity of the table until an empty spot is found
        }

        return start;
    }

    /**
     * This method analyzes the performance of the HashMap in several different ways. 
     * This inclues load factor, hash efficiency, average reads per record, and longest chain when searcing.
     * @return a String containing the performance stats.
     */
    public String analyzePerformance() {
        double loadFactor = (double) this.actSize / (double) this.capacity;
        //load factor is actual number of records / table capacity
        
        int numReads = 0;
        int longestChain = 0;
        //numReads holds the total number of reads to access each element in the table
        //longestChain holds the longest number of reads to access a single element

        for(String each: this.table) {
            if(each == null) {
                continue;
                //if the table element is empty, do nothing
            }

            int result = find(each);
            //find the number of reads to get to the String

            numReads += result;
            if(result > longestChain) {
                longestChain = result;
                //if the number of reads to find the current element is longer than any previous search
                    //overwrite the longestChain value
            }
        }

        double readsPerRecord = (double) numReads / this.actSize;
        //average number of reads/record is number of reads in total divided by number of records

        double hashEfficiency = (double) (loadFactor / readsPerRecord);
        //hash efficiency is load factor divided by avg reads per record
        
        StringBuilder result = new StringBuilder();
        result.append("Average number of reads per record: " + readsPerRecord + "\n");
        result.append("Load factor: " + loadFactor + " or " + loadFactor * 100 + "%\n");
        result.append("Hashing efficiency: " + hashEfficiency + " or " + hashEfficiency * 100 + "%\n");
        result.append("Longest chain when searching: " + longestChain + " reads");
        //add all the information to a StringBuilder object

        return result.toString();
    }

    /**
     * This method searches for an item in the HashMap and returns the index it is found at.
     * @param item The item to be searched for.
     * @return The index the item is found at.
     */
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
