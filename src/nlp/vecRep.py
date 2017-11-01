from nltk import word_tokenize
import operator

with open('sv2corpus.txt', 'r') as corpus:
    with open('tokenizedText.txt', 'w') as output:
        #lowercase and remove empty lines
        rawText = corpus.read().lower()
        #cleanedText = [line for line in rawText.split('\n') if line.strip() != '']
        
        #tokenize
        #singleCleanedText = ''
        #for item in cleanedText:
        #        singleCleanedText += item
        tokenizedText = word_tokenize(rawText)
        
        for item in tokenizedText:
            output.write(item+'\n')


    

#Get frequency of each word
wordFreqs = {}
for word in tokenizedText:
    if word in wordFreqs:
        wordFreqs[word] = wordFreqs[word] + 1
    else:
        wordFreqs[word] = 1

#Sort by frequency
sortedWordFreq = sorted(wordFreqs.items(), key=operator.itemgetter(1))

with open('sortedWordFrequencies.txt', 'w') as output:
    for item in tokenizedText:
        output.write(item+'\n')

#Dimensions
context = sortedWordFreq[-20:]


print(context)
    