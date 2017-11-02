package nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WordSimilarity {
	private static HashMap<String, Integer> countWordFrequencies(final List<String> tokWords) {
		HashMap<String, Integer> wordCounts = new HashMap<>();
		for (String tok : tokWords) {
			if (wordCounts.containsKey(tok)) {
				wordCounts.put(tok, wordCounts.get(tok) + 1);
			} else {
				wordCounts.put(tok, 1);
			}
		}
		return wordCounts;
	}
	private static List<Word> getNMostFreq(HashMap<String, Integer> wordFrequencies) {
		ArrayList<Word> wordList = new ArrayList<>();
		for (Map.Entry<String, Integer> word : wordFrequencies.entrySet()) {
			wordList.add(new Word(word.getKey(), word.getValue()));
		}
		Collections.sort(wordList);
		return wordList.subList(0, 19);
	}
	private static void incrMap(final String tok, HashMap<String, Integer> resultVec) {
		if (resultVec.containsKey(tok)) {
			resultVec.put(tok, resultVec.get(tok) + 1);
		}
	}
	private static List<Integer> getVector(final String word, 
			final List<String> corpus, final List<Word> dimensions, final int windowSize) {
		//n lookahead / look back
		int lookAround = (windowSize - 1)/2;
		
		HashMap<String, Integer> resultMap = new HashMap<>();
		for (Word w : dimensions) resultMap.put(w.getVal(), 0);
		
		//set iterators at lookAround positions apart
		Iterator<String> corpusIt1 = corpus.iterator();
		Iterator<String> corpusIt2 = corpus.iterator();
		for (int i=0; i<lookAround; i++) corpusIt2.next();
		
		String tok;
		while (corpusIt2.hasNext()) {
			if ((tok = corpusIt2.next()).equals(word.toLowerCase())) {
				for (int i=0; i<lookAround; i++) {
					incrMap(corpusIt1.next(), resultMap);
					incrMap(corpusIt2.next(), resultMap);
				}
			} else {
				corpusIt1.next();
			}
		}
		
		ArrayList<Integer> resultVec = new ArrayList<>();
		for (Word w : dimensions) {
			resultVec.add(resultMap.get(w.getVal()));
		}
		return resultVec;
	}
	public static double calculateSimilarity(final List<Integer> vectA, final List<Integer> vectB) {
		int nominator = 0;
		double denominator;
		
		Iterator<Integer> aIt = vectA.iterator();
		Iterator<Integer> bIt = vectB.iterator();
		while (aIt.hasNext()) nominator += (aIt.next()*bIt.next());
		
		Iterator<Integer> aIt2 = vectA.iterator();
		Iterator<Integer> bIt2= vectB.iterator();
		int v1sq = 0, v2sq = 0;
		while (aIt2.hasNext()) {
			v1sq += Math.pow(aIt2.next(), 2);
			v2sq += Math.pow(bIt2.next(), 2);
		}
		denominator = Math.sqrt(v1sq) * Math.sqrt(v2sq);
		
		return nominator / denominator;
	}
	public static void printVec(final String word, final List<Word> dimensions, final List<Integer> counts) {
		System.out.print(word + "{ ");
		Iterator<Word> dimIt = dimensions.iterator();
		Iterator<Integer> countIt = counts.iterator();
		while (dimIt.hasNext()) {
			System.out.print("(" + dimIt.next().getVal() + " : " + countIt.next() + ")");
			if (dimIt.hasNext()) System.out.print(",");
		}
		System.out.println("}");
	}
	public static void testSample() {
		ArrayList<String> testCorpus = new ArrayList<>();
		java.util.Collections.addAll(testCorpus,
				"i", "am", "a", "piece", "of", "cake",
				"with", "jelly");
		ArrayList<Word> testDimensions = new ArrayList<>();
		java.util.Collections.addAll(testDimensions,
				new Word("i", 0), new Word("am", 0), new Word("a", 0), new Word("piece", 0), new Word("of", 0), new Word("cake", 0));
		printVec("i", testDimensions, getVector("i", testCorpus, testDimensions, 5));
		
	}
	public static void main(String[] args) {
		String fileName = "tokenizedText2.txt";
		int windowSize = 5;
		
		CorpusRetriever retriever = new CorpusRetriever();
		
		List<String> corpus = retriever.getTokWords(fileName);
		List<String> cleanedCorpus = retriever.cleanTokens(corpus);
		HashMap<String, Integer> wordFrequencies = countWordFrequencies(cleanedCorpus);
		List<Word> dimensions = getNMostFreq(wordFrequencies);
		
		printVec("President", dimensions, getVector("President", corpus, dimensions, windowSize));
		printVec("Obama", dimensions, getVector("Obama", corpus, dimensions, windowSize));
		printVec("bush", dimensions, getVector("bush", corpus, dimensions, windowSize));
		printVec("afghanistan", dimensions, getVector("afghanistan", corpus, dimensions, windowSize));
		printVec("iraq", dimensions, getVector("iraq", corpus, dimensions, windowSize));
		printVec("war", dimensions, getVector("war", corpus, dimensions, windowSize));
		
		List<Integer> presidentVec = getVector("President", corpus, dimensions, windowSize);
		List<Integer> obamaVec = getVector("Obama", corpus, dimensions, windowSize);
		List<Integer> bushVec = getVector("bush", corpus, dimensions, windowSize);
		List<Integer> afghanistanVec = getVector("afghanistan", corpus, dimensions, windowSize);
		List<Integer> iraqVec = getVector("iraq", corpus, dimensions, windowSize);
		List<Integer> warVec = getVector("war", corpus, dimensions, windowSize);
		
		System.out.println("President / President : " + calculateSimilarity(presidentVec, presidentVec));
		System.out.println("President / Obama : " + calculateSimilarity(presidentVec, obamaVec));
		System.out.println("President / Bush : " + calculateSimilarity(presidentVec, bushVec));
		System.out.println("President / Afghanistan : " + calculateSimilarity(presidentVec, afghanistanVec));
		System.out.println("President / Iraq : " + calculateSimilarity(presidentVec, iraqVec));
		System.out.println("President / War : " + calculateSimilarity(presidentVec, warVec));
		
		System.out.println("Obama / Afghanistan : " + calculateSimilarity(obamaVec, afghanistanVec));
		System.out.println("Obama / bush : " + calculateSimilarity(obamaVec, bushVec));
		System.out.println("Obama / Iraq : " + calculateSimilarity(obamaVec, iraqVec));
		System.out.println("Obama / war : " + calculateSimilarity(obamaVec, warVec));
		
		System.out.println("Bush / Afghanistan : " + calculateSimilarity(bushVec, afghanistanVec));
		System.out.println("Bush / iraq : " + calculateSimilarity(bushVec, iraqVec));
		System.out.println("Bush / war : " + calculateSimilarity(bushVec, warVec));
		System.out.println("afghanistan / iraq : " + calculateSimilarity(afghanistanVec, iraqVec));
		System.out.println("afghanistan / war : " + calculateSimilarity(afghanistanVec, warVec));
		System.out.println("iraq / war : " + calculateSimilarity(iraqVec, warVec));
	}
}
