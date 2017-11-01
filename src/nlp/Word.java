package nlp;

public class Word implements Comparable<Word> {
	private String val;
	private int count;
	public Word(final String val, final int count) {
		this.val = val;
		this.count = count;
	}
	public String getVal() {
		return this.val;
	}
	public int getCount() {
		return this.count;
	}
	@Override
	public int compareTo(Word word) {
		return word.count - this.count;
	}
}

