package nlp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CorpusRetriever {
	public List<String> cleanTokens(final List<String> tokWords) {
		HashSet<String> stopWords = new HashSet<>();
		try (BufferedReader br = new BufferedReader(new FileReader("stopWords.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				stopWords.add(line);
			}
			java.util.Collections.addAll(stopWords,
					",", ".", "``", "$", "'s", "n't",
					"(", ")", ":", "''", "[", "]", "%",
					";", "!", "#", "*", "'ll", "...", "'d");
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		
		List<String> cleaned = new ArrayList<>();
		for (String tok : tokWords) {
			if (!stopWords.contains(tok)) cleaned.add(tok);
		}
		return cleaned;
	}
	public List<String> getTokWords(final String fileName) {
		ArrayList<String> tokenizedWords = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
			String line;
			while ((line = br.readLine()) != null) {
				tokenizedWords.add(line);
			}
		} catch (java.io.IOException e) {
			e.printStackTrace();
		}
		return tokenizedWords;
	}
}
