
/* ListSorts.java */

import list.*;

public class ListSorts {

	private final static int SORTSIZE = 100;

	/**
	 * makeQueueOfQueues() makes a queue of queues, each containing one item of
	 * q. Upon completion of this method, q is empty.
	 * 
	 * @param q
	 *            is a LinkedQueue of objects.
	 * @return a LinkedQueue containing LinkedQueue objects, each of which
	 *         contains one object from q.
	 **/
	public static LinkedQueue makeQueueOfQueues(LinkedQueue q) {
		// Replace the following line with your solution.
		LinkedQueue newQ = new LinkedQueue();
		while (!q.isEmpty()) {
			LinkedQueue tempQ = new LinkedQueue();
			try {
				tempQ.enqueue(q.dequeue());
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
			newQ.enqueue(tempQ);
		}
		return newQ;
	}

	/**
	 * mergeSortedQueues() merges two sorted queues into a third. On completion
	 * of this method, q1 and q2 are empty, and their items have been merged
	 * into the returned queue.
	 * 
	 * @param q1
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @param q2
	 *            is LinkedQueue of Comparable objects, sorted from smallest to
	 *            largest.
	 * @return a LinkedQueue containing all the Comparable objects from q1 and
	 *         q2 (and nothing else), sorted from smallest to largest.
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static LinkedQueue mergeSortedQueues(LinkedQueue q1, LinkedQueue q2) {
		// Replace the following line with your solution.
		Object p = null, q = null;
		if (q1.isEmpty()) {
			return q2;
		}
		if (q2.isEmpty()) {
			return q1;
		}
		try {
			p = q1.dequeue();
			q = q2.dequeue();
		} catch (QueueEmptyException e1) {
			e1.printStackTrace();
		}
		LinkedQueue resultQ = new LinkedQueue();
		while (true) {
			try {
				if (((Comparable) p).compareTo(q) < 0) {
					resultQ.enqueue(p);
					if (!q1.isEmpty()) {
						p = q1.dequeue();
					} else {
						resultQ.enqueue(q);
						break;
					}
				} else {
					resultQ.enqueue(q);
					if (!q2.isEmpty()) {
						q = q2.dequeue();
					} else {
						resultQ.enqueue(p);
						break;
					}
				}
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
		if (q1.isEmpty()) {
			resultQ.append(q2);
		} else {
			resultQ.append(q1);
		}
		return resultQ;
	}

	/**
	 * partition() partitions qIn using the pivot item. On completion of this
	 * method, qIn is empty, and its items have been moved to qSmall, qEquals,
	 * and qLarge, according to their relationship to the pivot.
	 * 
	 * @param qIn
	 *            is a LinkedQueue of Comparable objects.
	 * @param pivot
	 *            is a Comparable item used for partitioning.
	 * @param qSmall
	 *            is a LinkedQueue, in which all items less than pivot will be
	 *            enqueued.
	 * @param qEquals
	 *            is a LinkedQueue, in which all items equal to the pivot will
	 *            be enqueued.
	 * @param qLarge
	 *            is a LinkedQueue, in which all items greater than pivot will
	 *            be enqueued.
	 **/
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void partition(LinkedQueue qIn, Comparable pivot, LinkedQueue qSmall, LinkedQueue qEquals,
			LinkedQueue qLarge) {
		// Your solution here.
		Object i = null;
		while (!qIn.isEmpty()) {
			try {
				i = qIn.dequeue();
				if (pivot.compareTo(i) > 0) {
					qSmall.enqueue(i);
				} else if (pivot.compareTo(i) < 0) {
					qLarge.enqueue(i);
				} else {
					qEquals.enqueue(i);
				}
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * mergeSort() sorts q from smallest to largest using mergesort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 **/
	public static void mergeSort(LinkedQueue q) {
		// Your solution here.
		if (q.isEmpty()) {
			return;
		}
		LinkedQueue tempQ = makeQueueOfQueues(q);
		LinkedQueue i, j;
		while (tempQ.size() > 1) {
			try {
				i = (LinkedQueue) tempQ.dequeue();
				j = (LinkedQueue) tempQ.dequeue();
				tempQ.enqueue(mergeSortedQueues(i, j));
			} catch (QueueEmptyException e) {
				e.printStackTrace();
			}
		}
		try {
			q.append((LinkedQueue) tempQ.dequeue());
		} catch (QueueEmptyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * quickSort() sorts q from smallest to largest using quicksort.
	 * 
	 * @param q
	 *            is a LinkedQueue of Comparable objects.
	 **/
	@SuppressWarnings("rawtypes")
	public static void quickSort(LinkedQueue q) {
		// Your solution here.
		if (q.isEmpty()) {
			return;
		}
		LinkedQueue smallQ = new LinkedQueue();
		LinkedQueue largeQ = new LinkedQueue();
		LinkedQueue equalsQ = new LinkedQueue();
		Object pivot = q.nth((int) (q.size() * Math.random() + 1));
		partition(q, (Comparable) pivot, smallQ, equalsQ, largeQ);
		quickSort(smallQ);
		quickSort(largeQ);
		q.append(smallQ);
		q.append(equalsQ);
		q.append(largeQ);
	}

	/**
	 * makeRandom() builds a LinkedQueue of the indicated size containing
	 * Integer items. The items are randomly chosen between 0 and size - 1.
	 * 
	 * @param size
	 *            is the size of the resulting LinkedQueue.
	 **/
	public static LinkedQueue makeRandom(int size) {
		LinkedQueue q = new LinkedQueue();
		for (int i = 0; i < size; i++) {
			q.enqueue(new Integer((int) (size * Math.random())));
		}
		return q;
	}

	/**
	 * main() performs some tests on mergesort and quicksort. Feel free to add
	 * more tests of your own to make sure your algorithms works on boundary
	 * cases. Your test code will not be graded.
	 **/
	public static void main(String[] args) {

		LinkedQueue q = makeRandom(10);
		System.out.println(q.toString());
		mergeSort(q);
		System.out.println(q.toString());

		q = makeRandom(10);
		System.out.println(q.toString());
		quickSort(q);
		System.out.println(q.toString());

		Timer stopWatch = new Timer();
		for (int i = 1; i <= 100000; i = i * 10) {
			stopWatch.reset();
			q = makeRandom(SORTSIZE * i);
			stopWatch.start();
			mergeSort(q);
			stopWatch.stop();
			System.out.println("Mergesort time, " + SORTSIZE * i + " Integers: " + stopWatch.elapsed() + " msec.");

			stopWatch.reset();
			q = makeRandom(SORTSIZE * i);
			stopWatch.start();
			quickSort(q);
			stopWatch.stop();
			System.out.println("Quicksort time, " + SORTSIZE * i + " Integers: " + stopWatch.elapsed() + " msec.");
		}
	}

}
