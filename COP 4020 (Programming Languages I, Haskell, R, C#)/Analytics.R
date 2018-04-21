#Analytics in R
#Aaron Varkonyi, COP 4020, UCF
#Please make sure to set the working directory to where review.data is
#newReviews.data is the json file written from the results


library(jsonlite)
library(stringr)

#reads in reviews file
Reviews = stream_in(file('review.data'))
#filters out reviews less than 100 words long
# Calculate how many words in a string
nwords <- function(string, pseudo=F){
  ifelse( pseudo, 
          pattern <- "\\S+", 
          pattern <- "[[:alpha:]]+" 
  )
  str_count(string, pattern)
}
longReviews = subset(Reviews, nwords(Reviews$reviewText) > 100)
#orders reviews by their overall value
orderedReviews <- longReviews[order(longReviews$overall),]
splitReviews <- split(orderedReviews, orderedReviews$overall, drop = FALSE)
write_json(splitReviews, paste0(getwd(), "/newReviews.data"))