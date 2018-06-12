package apjp2017.hw3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.lang.System.out;

/**
 * Implement all methods given in this class using lambda expressions.
 * 
 * @author chencc
 *
 */
public class HW3 {

	

	/**
	 * Return a new list which is the result of sorting the input list words by the length (in increasing order) of each word, and then by
	 * lexicographical order if they are of the same length.
	 * You can call {@link java.util.Collections#sort(List, java.util.Comparator) Collections.sort() } for in-place sorting.
	 * 
	 * @param  words  a list of String to be sorted.	  
	 * @return a new list for the sorting result.
	 */
	
	public static List<String> sortWordsByLength(List<String> words) {

		// Make a new copy words_ of words and
		// sort words_ IN PLACE using Collections.sort
		// Put your code here! ...
		
		List<String> newWords = new ArrayList<String>(words);
		Collections.sort(newWords);
		Collections.sort(newWords, (s1,s2) -> s1.length() - s2.length());
		return newWords;

	}

	
	/**
	 * Return a new list which is the result of sorting input words by the number of characters which occurs in the input character
	 * array cs. For instance, if words = ['aabbc', 'bbbcd', 'abcdef'], then the call of
	 * sortWordsByNumberOfChar(list, 'a', 'b','c') will result in ['abcdef', 'bbbcd', 'aabbc'] since
	 * 'abcdef' has 3 orccurrences of {a,b,c}, while 'bbbcd' and 'aabbc' have 4 and 5 occurrences of {a,b,c}, respectively. 
	 *  
	 * @param words  a list of String to be sorted.
	 * @param cs characters to be compared with
	 * @return a new list for the sorting result.
	 */
	public static List<String> sortWordsByNumberOfChar(List<String> words, char... cs) {

		// Make a new copy words_ of words and
		// sort words_ IN PLACE using Collections.sort
		// Put your code here! ...
		List<String> newWords = new ArrayList<String>(words);
		Collections.sort(newWords,(s1,s2) -> {
			int countS1 = 0;
			int countS2 = 0;
			for(int i = 0; i <  s1.toString().toCharArray().length; i++) {
				for(int j = 0; j < cs.length; j++) {
					char[] s1str = s1.toString().toCharArray();
					if(s1str[i] == cs[j]) {
						countS1++;
					}
				}
			}
			for(int x = 0; x <  s2.toString().toCharArray().length; x++) {
				for(int y = 0; y < cs.length; y++) {
					char[] s2str = s2.toString().toCharArray();
					if(s2str[x] == cs[y]) {
						countS2++;
					}
				}
			}
			return countS1 - countS2;
		});
		
		return newWords;
	}
	
	
	
	
	/**
	 * Implement the filter() method which will return a new list consisting of all elements in the input list which pass the test() method of the iput predicate pred.
	 * The returned elements are ordered according to their original position in the input list.
	 * For instance , if list=[1,2,3,4,5,6] and pred is {@literal (x)-> x % 2 == 0 }, then the result 
	 * is [2,4,6]. The result should be equivalent to the execution of
	 * the code:
	 * 
	 * <pre>
	 * list.stream().filter(pred).collect(Collectors.toList());// BUT YOU CANNOT USE Stream API in this implementation.
	 * </pre>  
	 *  
	 * @param list
	 *            : a non-null list of element to be filtered
	 * @param pred
	 *            : the predicate
	 * @return a new list consisting of all elements passing the test of pred.
	 */
	public static <T> List<T> filter(List<T> list, Predicate<? super T> pred) {

		// Put your code here! ...
		List<T> ans = new ArrayList<T>();
		for(int i = 0; i < list.size(); i++) {
			if(pred.test(list.get(i)) == true) {
				ans.add(list.get(i));
			}
		}
		return ans;
	}
	

	/**
	 * Make a static method called map. It should take a List<T> l and a
	 * Function<? super T, ?extends U> f and return a new List { f(e) | e in l }
	 * of type List<U> that contains the results of applying the Function to
	 * each element of the original List. E.g.: List<String> excitingWords =
	 * map(words, s -> s + "!"); List<String> eyeWords = map(words, s ->
	 * s.replace("i", "eye")); List<String> upperCaseWords = map(words,
	 * String::toUpperCase); The result should be equivalent to the execution of
	 * the code:
	 * 
	 * <pre>
	 * list.stream().map(f).collect(Collectors.toList()) ; BUT YOU CANNOT USE
	 * Stream API in this implementation.
	 */

	public static <T, U> List<U> map(List<T> list, Function<? super T, ? extends U> f) {

		// Put your code here! ...
		List<U> rlt = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			rlt.add(f.apply(list.get(i)));
		}
		return rlt;
	}

	/**
	 * Implement the flatMap function on list<T>. The result should be
	 * equivalent to the execution of the code:
	 * 
	 * <pre>
	 * list.stream().flatMap(f).collect(Collectors.toList()) ; BUT YOU CANNOT
	 * USE Stream API in this implementation.
	 * 
	 * @param list
	 * @param f
	 *            a function from T to List<R>
	 * @return a list fo type list<R>
	 */

	public static <T, R> List<R> flatMap(List<T> list, Function<? super T, List<R>> f) {
		// The result should be equivalent to the code:
		// list.stream().flatMap(f) ;
		// but YOU CANNOT USE this stream API in this implementation.
		List<R> rlt = new ArrayList<>();

		// Put your code here! ...
		for(int i = 0; i < list.size(); i++) {
			for(int j = 0; j < f.apply(list.get(i)).size() ; j++) {
				rlt.add((R) f.apply(list.get(i)).get(j));
			}
			
		}
		return rlt;
	}

	/**
	 * Partition the input list into two lists, one being the list [ e | p(e) is
	 * true ] and the other the list [ e | p(e) returns false ]. The result are
	 * put in a map (HashMap or TreeMap) under the key Boolean.True and
	 * Boolean.False, respectively. The result should be equivalent to the
	 * execution of the code:
	 * 
	 * <pre>
	 * list.stream().collect(Collectors.partitoningBy(p));
	 * </pre>
	 * 
	 * But YOU CANNOT USE Stream API in this implementation.
	 * 
	 * @param list
	 * @param p
	 * @return
	 */
	public static <T> Map<Boolean, List<T>> partition(List<T> list, Predicate<? super T> p) {
		// The result should be equivalent to the code:
		// list.stream().collect(Collectors.partitoningBy(p)) ;
		// but YOU CANNOT USE this stream API in this implementation.
		Map<Boolean, List<T>> rlt = new HashMap<>();
		List<T> falseList = new ArrayList<T>();
		List<T> trueList = new ArrayList<T>();
		// Put your code here! ...
		for(int i = 0; i < list.size(); i++) {
			if(p.test(list.get(i)) == false) {
				falseList.add(list.get(i));
				rlt.put(p.test(list.get(i)), falseList);
			}
			if(p.test(list.get(i)) == true) {
				trueList.add(list.get(i));
				rlt.put(p.test(list.get(i)), trueList);
			}
		}
		if(falseList.isEmpty()) {
			falseList.add((T) " ");
			rlt.put(false, falseList);
		}
		return rlt;
	}

	/**
	 * Implement the collect function on list<T>. The result should be
	 * equivalent to the execution of the code:
	 * 
	 * <pre>
	 * list.stream().collect(col);
	 * </pre>
	 * 
	 * But YOU CANNOT USE stream API in this implementation.
	 * 
	 * @param list
	 * @param col
	 *            a collector of type Collector<T,A,R>
	 * @return a value of type R.
	 */

	public static <T, A, R> R collect(List<T> list, Collector<T, A, R> col) {
		// The result should be equivalent to the code:
		// list.stream().collect(col) ;
		// but YOU CANNOT USE this stream API in this implementation.

		// Put your code here! ...
		R rlt = null;
		A A1 = col.supplier().get();
		for(int i = 0; i < list.size(); i++) {
			col.accumulator().accept(A1, list.get(i));
			rlt = col.finisher().apply(A1);
		}
		return rlt;
	}
	
	
	/**
	 * 
	 * 
	 * Implement the 'reduce' method for lists by imitating the reduce method in Streaming API
	 * {@link java.util.stream.Stream#reduce(Object, BiFunction, java.util.function.BinaryOperator) Stream.reduce()}
	 *  Ex: If list = (3,4,5,6), and unit is 100 and accumulator is '-', then
	 * reduce(list, unit, (x,y) -> x -y ) would result in ((((100 - 3) -4) - 5) - 6) = 82. 
	 * @param list a list of elements to be reduced
	 * @param unit the initial accumulated value. 
	 * @param accumulator a BiFunction of type (R, T) --> R to absorb next element into the accumulator.  
	 * @return the reduced result.
	 */
	
	
	public static <T,R> R reduce( List<T> list, R unit,  BiFunction<R,? super T,R> accumulator ) {
		// The result should be equivalent to the code:
		// list.stream().reduce(R, accumulator, null) ;
		// but YOU CANNOT USE this stream API in this implementation.
		
		// Put your code here! ...
		R R1 = unit;
		for(int i = 0; i < list.size(); i++) {
			R1 = accumulator.apply(R1, list.get(i));
		}
         return R1 ;		
	}
	
	
	////////////////////////////////////////////
	// The following are test data           ///
	////////////////////////////////////////////
	
	static final String s1 = "The researchers said that women tend to take on a bigger share of responsibilities at home and may face more pressure and stress than men when they work long hours. Work for women is also less satisfying due to the need to balance demands at work and family obligations.";

	final static String[] s2 = s1.split("[ .]+");

	static final String s3 = "Apple chief executive officer Tim Cook made his first appearance at China’s World Internet Conference, using the surprise keynote to call for future Internet and artificial intelligence (AI) technologies to be infused with privacy, security and humanity."
			+ " Cook made the comments yesterday at the opening ceremony for the conference -- an event overseen by the Cyberspace Administration of China and designed to globally promote China’s vision of a more censored and controlled Internet.";

	final static String[] s4 = s3.split("[ ,.()-]+");;


	// do not change words!
	public final static List<String> s2words = Collections.unmodifiableList(Arrays.asList(s2));
	public final static List<String> s4words = Collections.unmodifiableList(Arrays.asList(s4));

	public final static List<String> s2words1 = s2words.subList(11, 40);
	public final static List<String> s4words1 = s4words.subList(6, 50);

	public final static List<String> words20 = Collections
			.unmodifiableList(Arrays.asList("100", "300", "20", "40", "77", "88", "1001", "6", "57", "90"));

	public final static List<String> words21 = Collections
			.unmodifiableList(Arrays.asList("2100", "1300", "210", "410", "77", "818", "1001", "61", "57", "901"));

	/////////////////////////////////////////////
	/////////////End Of Test Data////////////////
    /////////////////////////////////////////////
	
	public static void selfTest() {

		out.println("\n>>> Sort strings in s2words by length first and by lexicogrphical order secondly:");
		out.println(">>> s2words = " + s2words);
		out.println(" result: " + sortWordsByLength(new ArrayList<>(s2words)));

		out.println("\n>>> Sort strings in s4words by length first and by lexicogrphical order secondly:");
		out.println(">>> s4words = " + s4words);
		out.println(" result: " + sortWordsByLength(new ArrayList<>(s4words)));

		out.println("\n>>> Sort strings in s2words by the number of occurrences of 'a', 'e', 'i', 'o' and 'u': ");
		out.println(">>> s2words = " + s2words);
		out.println("result: " + sortWordsByNumberOfChar(new ArrayList<>(s2words), 'a', 'e', 'i', 'o', 'u'));

		out.println("\n>>> Sort strings in s4words by the number of occurrences of 'c', 's','t','m' and 'p': ");
		out.println(">>> s4words = " + s4words);
		out.println("result: " + sortWordsByNumberOfChar(new ArrayList<>(s4words), 'c', 's', 't', 'm', 'p'));

		out.println("\n>>> filter strings in s2words with length > 10 ");
		out.println(">>> s2words = " + s2words);
		out.println("result: " + filter(new ArrayList<>(s2words), s -> s.length() > 10));

		out.println("\n>>> filter strings in s4words starting with a capitalized letter");
		out.println(">>> s4words = " + s4words);
		out.println("result: " + filter(new ArrayList<>(s4words), s -> Character.isUpperCase(s.charAt(0))));

		out.println("\n>>> Map words in s2words1 to upperCase:");
		out.println(">>> s2words1 = " + s2words1);
		out.println(map(s2words1, String::toUpperCase));

		out.println("\n>>> Map words in s4words1 to upperCase:");
		out.println(">>> s4words1 = " + s4words1);
		out.println(map(s4words1, String::toUpperCase));

		out.println("\n>>> flatMap words in words20 to character list:");
		out.println(">>> words20 = " + words20);
		Function<String, List<Character>> as = s -> s.chars().mapToObj(c -> new Character((char) c))
				.collect(Collectors.toList());
		List<Character> rlt = flatMap(words20, as);
		out.println(rlt);

		out.println("\n>>> flatMap words in words21 to character list:");
		out.println(">>> words21 = " + words21);
		Function<String, List<Character>> as2 = s -> s.chars().mapToObj(c -> new Character((char) c))
				.collect(Collectors.toList());
		List<Character> rlt2 = flatMap(words21, as);
		out.println(rlt2);

		out.println("\n>>> partition words in words20 into two list depending on whether its length <= 10 :");
		out.println(">>> words20 = " + words20);
		out.println(partition(words20, s -> s.length() <= 10));

		out.println("\n>>> partition words in s4words into two list depending on whether its length <= 10 :");
		out.println(">>> s4words = " + s4words);
		out.println(partition(s4words, s -> s.length() <= 10));

		out.println("\n>>> partition words in words21 into two list depending on whether its length > 2:");
		out.println(">>> words21 = " + words21);
		out.println(partition(words21, s -> s.length() > 2));

		out.println("\n>>> group strings in words20 according to their length:");
		out.println(">>> words20 = " + words20);
		out.println(collect(words20, Collectors.groupingBy(s -> s.length())));

		out.println("\n>>> group strings in words21 according to their first letter:");
		out.println(">>> words21 = " + words21);
		out.println(collect(words21, Collectors.groupingBy(s -> s.charAt(0))));
		
		
		out.println("\n>>> reduce strings in words21 to concate them:");
		out.println(">>> words21 = " + words21);
		out.println(reduce(words21, "", (acc, s ) -> acc+s ));
		
		out.println("\n>>> reduce strings in words21 to concate them:");
		out.println(">>> words21 = " + words21);
		out.println(reduce(words21, "", (acc, s ) -> acc+s ));

		//////////////////////////////////////////////////////////////////////////////
		// Your result for selftest() should look like the text show in file
		// HW3ExpectedResult.txt
		/////////////////////////////////////////////////////////////////////////////

	}

	
	
	public static void main(String[] args) {		
		
		// Your result for selftest() should look like the text shown in file HW31ExpectedResult.txt
		selfTest();
		
		// can optionally add your own test code here!!

	}

}