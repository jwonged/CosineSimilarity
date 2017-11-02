from nltk import word_tokenize
import operator

with open('sv2corpus.txt', 'r') as corpus:
    with open('tokenizedText.txt', 'w') as output:
        tokenizedText = word_tokenize(rawText)
        
        for item in tokenizedText:
            output.write(item+'\n')


    