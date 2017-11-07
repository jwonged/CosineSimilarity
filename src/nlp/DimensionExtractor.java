package nlp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DimensionExtractor {
	private HashMap<String, Integer> countWordFrequencies(final List<String> tokWords) {
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
	public List<Word> getNMostFreq(final List<String> tokWords) {
		HashMap<String, Integer> wordFrequencies = countWordFrequencies(tokWords);
		ArrayList<Word> wordList = new ArrayList<>();
		for (Map.Entry<String, Integer> word : wordFrequencies.entrySet()) {
			wordList.add(new Word(word.getKey(), word.getValue()));
		}
		Collections.sort(wordList);
		return wordList.subList(0, 19);
	}
}
