import Data.Char
import Data.List
--Aaron Varkonyi
--Haskell test 2
--I am taking this test with SAS, and as such I am alloted extra time. Thank you.

--problem 1
numPowers n = length [x | x <-(takeWhile(<=(10^n)-1) $ map(^n)[1..]), (length . show) x == n]
problem1 = sum $ takeWhile(>0) [numPowers x|x<-[1..]]

--problem 2
isBouncy :: Integer -> Bool
isBouncy n = (bounce . (map digitToInt) . show) n

bounce :: [Int] -> Bool
bounce (x:xs)
 | length xs <= 1 = False
 | (y > x && y > z) || (y < x && y < z) = True
 | otherwise = bounce (x : tail xs)
  where y = head xs
        z = (head . tail) xs

find99 = snd . head . filter (\(a,b)-> a >= 0.99 * realToFrac b) . zip [1..]

problem2 = find99 (filter(isBouncy) [1..])

{-
Problem 3

Functors include containers, such as lists and maybes, that can be mapped over.
If one has made their own container and wants it to be mappable (such as a binary tree),
they will have to define a functor for that container as well as how the fmap function treats that container.
In general, fmap lets one apply a function to a container.
-}


{-
Problem 4
A monad consists of a type constructor, a bind operation (>>=), and a return operation.
The return operation just sends its result to the next function (so return x >>= f = f x).
Returning the result of an action is equivalent to just doing the action (so m >>= return = m).
The bind operation is also associative, so do {x<- m1; y<- m2; m3) is equal to do {y <- do { x <- m1; m2} m3}.
-}

