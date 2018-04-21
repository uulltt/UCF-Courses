--Aaron Varkonyi
--aa344615
--3480260
--COP 4020 Spring 2018
--Haskell Math
import Data.List

--our integer list
integers = [324, 610, 855, 770, 902, 238, 155, 554, 130]
--our float list
floats = [6.9, 33.9, 4.30, 81.8, 8.20, 34.1, 2.64, 53.4, 69.3, 69.3]

--max value function
maxVal :: (Ord y) => [y] -> y
maxVal [] = error "Maximum of Empty List"
maxVal [x] = x
maxVal (x:xs)
 |x > maxTail = x --set maxval of xs to x if x is greater than maxtail
 |otherwise = maxTail --otherwise keep it to maxtail
 where maxTail = maxVal xs

--min value function 
minVal :: (Ord y) => [y] -> y
minVal [] = error "Minimum of Empty List"
minVal [x] = x
minVal (x:xs)
 |x < minTail = x --same as max function but looking for something less than mintail
 |otherwise = minTail
 where minTail = minVal xs

--mean value function 
meanVal :: (Real a, Fractional b) => [a] -> b
meanVal xs = realToFrac (sum xs)/ (genericLength xs) --taking the sum of the list and dividing it by its length

--a quicksort function needed for the median function, inspired by the one on the learnyouahaskell website
quicksort :: (Ord y) => [y] -> [y]  
quicksort [] = []  
quicksort (x:xs) =  (quicksort [y | y <- xs, y <= x]) ++ [x] ++ (quicksort [y | y <- xs, y > x])

--median value function
medVal :: (Real a, Fractional b) => [a] -> b
medVal xs = if odd (length xs) --this is technically two different functions depending on whether the length of xs is odd or even
 then realToFrac (quicksort xs !!  (length xs `div` 2)) --if the length is odd, look for the element at the middle index
 else realToFrac ((quicksort xs !! (length xs `div` 2)) + ((quicksort xs !! ((length xs `div` 2)-1))))/2 --if the length is even, look for the two elements at the middle index and average them out

--a subtract mean function needed for the standard deviation function
subtractMean :: (Real a, Fractional b) => [a] -> [b]
subtractMean xs = map ((subtract (meanVal xs)) . realToFrac) (xs) --subtracts the mean of a list from each of its values

--standard deviation function
stdVal :: (Real a, Fractional b) => [a] -> b
stdVal xs = realToFrac (sqrt ((sum (map (^2) (subtractMean xs))) / (genericLength xs))) --we get the square root of the sum of the squares of the subtractedmean list divided by its length
 
main = do
putStr "The Integer List: " 
print integers
putStr "The Float List: "
print floats
putStr "Max Integer Value: "
print (maxVal integers)
putStr "Min Integer Value: "
print (minVal integers)
putStr "Mean Integer Value: "
print (meanVal integers)
putStr "Median Integer Value: "
print (medVal integers)
putStr "Max Float Value: "
print (maxVal floats)
putStr "Min Float Value: "
print (minVal floats)
putStr "Mean Float Value: "
print (meanVal floats)
putStr "Median Float Value: "
print (medVal floats)
putStr "Integer Standard Deviation: "
print (stdVal integers)
putStr "Float Standard Deviation: "
print (stdVal floats)