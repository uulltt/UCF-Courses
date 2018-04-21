#Aaron Varkonyi
#COP 4020 Test 3
#I am taking this with SAS and as such I should not get the late penalty. thank you.

test3Problem1 <- function(){
  #read in the data file
  data <- read.csv("test3data.csv")
  #find wind data of even numbered days in june
  winddata <- data$Wind[seq(33, 61, 2)]
  #return the mean of the wind data with a value greater than 9
  return (mean(winddata[winddata>9]))
}

test3Problem2 <- function(){
  data <- read.csv("test3data.csv")
  #removes the na stuff
  remove.na <- data[rowSums(is.na(data)) <= 0,]
  #finds the minimum
  mins <- sapply(remove.na, FUN = min)
  #finds the maximum
  maxs <- sapply(remove.na, FUN = max)
  #actually makes the data frame
  return(rbind(mins,maxs)[,c("Ozone","Solar.R","Wind","Temp")])
}



test3Problem3 <- function(){
  Future500 <- read.csv("Future-500.csv")
  F500 <- Future500
  #reduces the revenue to just numbers
  F500$Revenue <- as.numeric(gsub("[$,]", "", F500$Revenue))
  #subs the na values in the revenue with the mean values of the non-na data
  F500$Revenue[is.na(F500$Revenue)]<-mean(as.numeric(F500[,"Revenue"]), na.rm = TRUE)
  #reduces the expenses to just numbers
  F500$Expenses <- as.numeric(gsub("[^.0-9]", "", F500$Expenses))
  #subs the na values in expenses
  F500$Expenses[is.na(F500$Expenses)]<-mean(as.numeric(F500[,"Expenses"]), na.rm = TRUE)
  #subs the na values in profit
  F500$Profit[is.na(F500$Profit)]<-mean(as.numeric(F500[,"Profit"]), na.rm = TRUE)
  #orders by profit in ascending order
  return(F500[order(F500$Profit),])
}




test3Problem4 <- function(){
  cherrydata <- read.csv("cherry.csv")
  #finds the lm
  equationoflm <- lm(Height ~ Volume, cherrydata)
  #plots the data
  return(plot(cherrydata$Volume, cherrydata$Height, xlab = "Volume", ylab = "Height", abline(equationoflm)))
}


