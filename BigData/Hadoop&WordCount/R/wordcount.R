wordcount <- function(des) {

  temple.text<-scan(file = des, what=character(),sep = "\n")
  temple.text<-tolower(temple.text)
  temple.words.list<-strsplit(temple.text,"\\s+", perl=TRUE)
  temple.words.vector<-unlist(temple.words.list)
  temple.freq.list<-table(temple.words.vector)
  temple.sorted.freq.list<-sort(temple.freq.list, decreasing=TRUE)
  temple.sorted.table<-paste(names(temple.sorted.freq.list), temple.sorted.freq.list, sep="\t")
  cat("Word\tFREQ", temple.sorted.table, file=choose.files(), sep="\n")

}

