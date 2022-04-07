public class MyHashTable {
	private class HashEntry{
		int element; 	// data 
		boolean isActive;
		
		HashEntry(int e){
			this(e, true);
		}
		HashEntry(int e, boolean i){
			element = e;
			isActive = i;
		}
	}
	
	private HashEntry[] array; 
	private int capacity; 
	private int currSize;
	private static int DEFAULT_CAPACITY = 11; 
	
	public MyHashTable() {
		this(DEFAULT_CAPACITY);
	}
	
	public MyHashTable(int c) {
		capacity = c;
		// initialize the hash table and make it logically empty
		currSize = 0;
		array = new HashEntry[c];
		for (int i=0; i < array.length; i++) {
			array[i] = null;
		}
	}
	
	private int myHash(int e) {
		return e % capacity;
	}

    public static int hash1(String key, int size) {
        int ans = 0;
        for(char ch: key.toCharArray()) {
            ans += ch;
        }

        return ans % size;
    }

    /**
     * folding and adding
     * @param key
     * @param size
     * @return
     */
    public static int hash2(String key, int size) {
        int ans = 0;
        int primeDivisor = 19937;
        char[] array = key.toCharArray();

        for(int i = 0; i < array.length; i+=2) {
            //concat ascii code of arr[i] and arr[i + 1]
            ans = (ans + concatInt((int)array[i], (int)array[i+1])) % primeDivisor;
        }
        //pad with white space if array is odd length
        if(array.length % 2 == 1) {
            ans = (ans + concatInt(array[array.length - 1], 32)) % primeDivisor;
        }

        return ans % size;
    }

    private static int concatInt(int a, int b) {
        int ans = b;
        while(b > 0) {
            a *= 10;
            b /= 10;
        }

        return ans + a;
    }

    private static int reverseConcatInt(int a, int b) {
        while(b > 0) {
            a = a * 10 + b % 10;
            b /= 10;
        }

        return a;
    }

    public static int hash3(String key, int size) {
        int ans = 0;
        int primeDivisor = 19937;
        char[] array = key.toCharArray();

        for(int i = 0; i < array.length; i+=2) {
            //concat ascii code of arr[i] and arr[i + 1]
            ans = (ans + reverseConcatInt((int)array[i], (int)array[i+1])) % primeDivisor;
        }
        //pad with white space if array is odd length
        if(array.length % 2 == 1) {
            ans = (ans + reverseConcatInt(array[array.length - 1], 32)) % primeDivisor;
        }

        return ans % size;
    }
	
	public int findPosLinearProbing(int e) {	
		// first compute the hash value h(e)
		int currPos = myHash(e);
		// resolve collisions
		for (int i = 0; i < capacity-1; i++) {
			if (array[currPos] == null ||
					!array[currPos].isActive||
					array[currPos].element == e) {
				return currPos;
			}
			currPos = (currPos + 1) % capacity;
		}
		return currPos;
	}
	
	public int findPosQuadraticProbing(int e) {
		int startPos = myHash(e);
		int currPos = startPos;	// the position we probe
		int i = 1;
		for (int j = 0; j < capacity; j++) {
			if (array[currPos] == null ||
					!array[currPos].isActive||
					array[currPos].element == e) {
				return currPos;
			}
			if (j%2 == 0) {
				currPos = startPos + i*i;
			}else {
				currPos = startPos - i*i;
				i ++;
			}
			currPos = Math.floorMod(currPos, capacity); 
		}
		return currPos;
	}
	
	boolean isActive(int pos) {
		return array[pos] != null && array[pos].isActive;
	}
	
	public void insert(int e) {
		// first find a position to insert
//		int pos = findPosLinearProbing(e);
		int pos = findPosQuadraticProbing(e);
		// check whether the position is open or not
		if (isActive(pos)) return;
		// if failed, then return
		array[pos] = new HashEntry(e);
	}
	
	public void printTable() {
		for (int i = 0; i < capacity; i++) {
			if (isActive(i)) {
				System.out.println("Index " + i + ": " + array[i].element);
			}else {
				System.out.println("Index " + i + ": empty");
			}
				
		}
	}
	
	
	public static void main(String[] args) {
		int[] arr = {13, 15, 6, 24, 21};
		MyHashTable table = new MyHashTable(7);
		
		for (int a: arr) {
			table.insert(a);
		}
		
		table.printTable();

        String key = "LOWELL      ";
        System.out.println("Hash 1:\t" + hash1(key, 101));
        System.out.println("Hash 2:\t" + hash2(key, 101));
        System.out.println("Hash 3:\t" + hash3(key, 101));
        System.out.println("reverse concat:\t" + reverseConcatInt(734, 211));

	}

}
