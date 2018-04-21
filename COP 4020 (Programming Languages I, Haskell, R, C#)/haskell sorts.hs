--Aaron Varkonyi
--COP 4020 Spring 2018
--Haskell Sorts

import Data.List

bubblesort list = bubb list 0 1
bubb::(Ord y) => [y] -> Int -> Int -> [y]  
bubb list inc1 inc2
 | inc1 >= (length list)-1 && inc2 >= length list = list --if both values have basically reached the end, return the list
 | inc1 < (length list)-1 && inc2 >= length list = bubb list (inc1+1) (inc1+2) --if only the second value has reached the end, increment the first value and make the second one one step above the first one
 | list !! inc1 > list !! inc2 = bubb (swap) inc1 (inc2+1) --if out first index has a greater value than our second, we swap the two values
 | otherwise = bubb list inc1 (inc2+1) --otherwise we just increment the 2nd value
 where swap = (take inc1 list)++[list !! inc2]++(take ((inc2-inc1)-1)(drop (inc1+1) list))++[list !! inc1]++(drop (inc2+1) list)

insertsort list = ins list 0 1

ins::(Ord y) => [y] -> Int -> Int -> [y] 
ins list inc1 inc2
 | inc1 >= (length list) - 1 = list --if we have reached the end of the list
 | inc2 <= 0 = ins list (inc1+1) (inc1+2) --if we have reached the beginning of the list with the 2nd value, we move a step up
 | list !! inc2 < list !! (inc2-1) = ins (swap) (inc1) (inc2-1) --if the element we are looking at is less than the one behind it, swap them and decrement to follow the element
 | otherwise = ins list (inc1+1) (inc1+2) --otherwise we move up a step
 where swap = (take (inc2 - 1) list) ++ [list !! inc2, list !! (inc2 - 1)] ++ (drop (inc2+1) list)

selectionsort list = sel [] list
sel::(Ord y) => [y] -> [y] -> [y]
sel newlist oldlist
 |null (oldlist) = newlist --if we took all the elements out of the old list, we return the newlist
 |otherwise = sel (newlist++[minimum oldlist]) (delete (minimum oldlist) oldlist) --otherwise we take the minimum of the oldlist and put it in the new list, and delete it from the old list

 