/* HashTableChained.java */

package dict;

import list.*;

/**
 * HashTableChained implements a Dictionary as a hash table with chaining. All
 * objects used as keys must have a valid hashCode() method, which is used to
 * determine which bucket of the hash table an entry is stored in. Each object's
 * hashCode() is presumed to return an int between Integer.MIN_VALUE and
 * Integer.MAX_VALUE. The HashTableChained class implements only the compression
 * function, which maps the hash code to a bucket in the table's range.
 *
 * DO NOT CHANGE ANY PROTOTYPES IN THIS FILE.
 **/

public class HashTableChained implements Dictionary {

	/**
	 * Place any data fields here.
	 **/
	List[] data;
	int nearestPrime;
	int size;
	int collisions;

	/**
	 * Construct a new empty hash table intended to hold roughly sizeEstimate
	 * entries. (The precise number of buckets is up to you, but we recommend
	 * you use a prime number, and shoot for a load factor between 0.5 and 1.)
	 **/

	public HashTableChained(int sizeEstimate) {
		// Your solution here.
		nearestPrime = sizeEstimate;
		while (!isPrime(nearestPrime)) {
			nearestPrime++;
		}
		data = new SList[nearestPrime];
		for (int i=0;i<nearestPrime;i++){
			data[i] =  new SList();
		}
		size = 0;
		collisions = 0;
	}

	// return true if x is prime
	private boolean isPrime(int x) {
		for (int i = 2; i < Math.sqrt(x); i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Construct a new empty hash table with a default size. Say, a prime in the
	 * neighborhood of 100.
	 **/

	public HashTableChained() {
		// Your solution here.
		nearestPrime = 100;
		while (!isPrime(nearestPrime)) {
			nearestPrime++;
		}
		data = new SList[nearestPrime];
		for (int i=0;i<nearestPrime;i++){
			data[i] =  new SList();
		}
		size = 0;
		collisions = 0;
	}

	/**
	 * Converts a hash code in the range Integer.MIN_VALUE...Integer.MAX_VALUE
	 * to a value in the range 0...(size of hash table) - 1.
	 *
	 * This function should have package protection (so we can test it), and
	 * should be used by insert, find, and remove.
	 **/

	int compFunction(int code) {
		// Replace the following line with your solution.
		int compHashCode;
		compHashCode=(((8*code + 88) % 16908799) % nearestPrime);
		if (compHashCode >= 0) {
			return compHashCode;
		} else {
			return (compHashCode + nearestPrime);
		}
	}

	/**
	 * Returns the number of entries stored in the dictionary. Entries with the
	 * same key (or even the same key and value) each still count as a separate
	 * entry.
	 * 
	 * @return number of entries in the dictionary.
	 **/

	public int size() {
		// Replace the following line with your solution.
		return size;
	}

	/**
	 * Tests if the dictionary is empty.
	 *
	 * @return true if the dictionary has no entries; false otherwise.
	 **/

	public boolean isEmpty() {
		// Replace the following line with your solution.
		return (size == 0);
	}

	/**
	 * Create a new Entry object referencing the input key and associated value,
	 * and insert the entry into the dictionary. Return a reference to the new
	 * entry. Multiple entries with the same key (or even the same key and
	 * value) can coexist in the dictionary.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the key by which the entry can be retrieved.
	 * @param value
	 *            an arbitrary object.
	 * @return an entry containing the key and value.
	 **/

	public Entry insert(Object key, Object value) {
		// Replace the following line with your solution.
		Entry e = new Entry();
		e.key = key;
		e.value = value;
		int myHashCode = compFunction(key.hashCode());
		if (!data[myHashCode].isEmpty()) {
			collisions++;
		}
		data[myHashCode].insertFront(e);
		size++;
		return e;
	}

	/**
	 * Search for an entry with the specified key. If such an entry is found,
	 * return it; otherwise return null. If several entries have the specified
	 * key, choose one arbitrarily and return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 **/

	public Entry find(Object key) {
		// Replace the following line with your solution.
		int myHashCode = compFunction(key.hashCode());
		if (data[myHashCode].isEmpty()) {
			return null;
		} else {
			ListNode p = data[myHashCode].front();
			Entry e = null;
			try {
				e = (Entry) p.item();
				while (p.isValidNode()) {
					if (e.key.equals(key)) {
						return e;
					}
					p = p.next();
				}
			} catch (InvalidNodeException e1) {
				e1.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * Remove an entry with the specified key. If such an entry is found, remove
	 * it from the table and return it; otherwise return null. If several
	 * entries have the specified key, choose one arbitrarily, then remove and
	 * return it.
	 *
	 * This method should run in O(1) time if the number of collisions is small.
	 *
	 * @param key
	 *            the search key.
	 * @return an entry containing the key and an associated value, or null if
	 *         no entry contains the specified key.
	 */

	public Entry remove(Object key) {
		// Replace the following line with your solution.
		int myHashCode = compFunction(key.hashCode());
		if (data[myHashCode].isEmpty()) {
			return null;
		} else {
			ListNode p = data[myHashCode].front();
			Entry e = null;
			try {
				e = (Entry) p.item();
				while (p.isValidNode()) {
					if (e.key.equals(key)) {
						p.remove();
						size--;
						if (!data[myHashCode].isEmpty()) {
							collisions--;
						}
						return e;
					}
					p = p.next();
				}
			} catch (InvalidNodeException e1) {
				e1.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * Remove all entries from the dictionary.
	 */
	public void makeEmpty() {
		// Your solution here.
		for (int i=0;i<nearestPrime;i++){
			data[i] = new SList();
		}
		size=0;
		collisions=0;
	}
	
	
	//print out information for my HashTable
	public String toString(){
		String result="";
		result = result+"N="+nearestPrime+"\n";
		result = result+"Size="+size+"\n";
		result = result+"Load Rate="+(double)size/nearestPrime+"\n";
		double expectedCollisions = (size-nearestPrime)+nearestPrime*Math.pow((1-(double)1/nearestPrime), size);
		result = result+"The expected number of collisions="+expectedCollisions+"\n";
		result = result+"The actual number of collisions="+collisions+"\n";
		for (int i=0;i<nearestPrime;i++){
			result = result+"["+data[i].length()+"]";
			if (i % 10 == 9){
				result+="\n";
			}
		}
		return result;
	}
	
	
	//test HashTableChained
	public static void main(String[] args){
		HashTableChained table = new HashTableChained(6);
		System.out.println("=====================size, isEmpty=========================");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());
        
        System.out.println("=====================insert================================");
        table.insert("1", "The first one");
        table.insert("2", "The second one");
        table.insert("3", "The third one");
        table.insert("what", "nani?");
        table.insert("the","Eh-heng");
        table.insert("hell!","impolite");
        System.out.println("table's size is: " + table.size());
        System.out.println("table is Empty: " + table.isEmpty());
        System.out.println(table.toString());

        
        System.out.println("====================find, remove===========================");
        Entry e1 = table.find("6");
        if(e1 != null)
                System.out.println("The item found is: [ " + e1.toString() + " ]");
        else
                System.out.println("The is no such item in the table to be found.");
        
        Entry e2 = table.remove("hell!");
        if(e2 != null)
                System.out.println("The item deleted is: [ " + e2.toString() + " ]");
        else
                System.out.println("The is no such item in the table to be deleted.");
        
        System.out.println(table.toString());
        
        System.out.println("=====================makeEmpty=============================");
        table.makeEmpty();
        System.out.println(table.toString());

	}

}
