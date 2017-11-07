# NLPWordSimilarity

Calculating word similarities through distributional semantics (cosine similarity)  

Corpus from http://www.cl.cam.ac.uk/~ltf24/sv2corpus.html  

* Window size = 5 (n=2 for lookahead / prior)
* 20 Most frequent words as vector dimensions
* Closed class / Stop words & Punctuation removed

## Results

President / President : 0.9999999999999998  
President / Obama : 0.7017519358823336  
President / Bush : 0.27708548465883925  
President / Afghanistan : 0.4653789209955172  
President / Iraq : 0.21846572437632575  
President / War : 0.503789467685854  
Obama / Afghanistan : 0.5422034324408617  
Obama / bush : 0.677365935537998  
Obama / Iraq : 0.22048945572563855  
Obama / war : 0.398295068583001  
Bush / Afghanistan : 0.2766475001553213  
Bush / iraq : 0.2091386551365156  
Bush / war : 0.2497876857366484  
afghanistan / iraq : 0.3787048546649207  
afghanistan / war : 0.5564148840746571  
iraq / war : 0.4029962965556805 
