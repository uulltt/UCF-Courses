import Data.List
import Data.Char

--Aaron Varkonyi
--Haskell Test 1

--Problem 1
intList n = filter(\x -> (x `mod` 3 == 0 || x `mod` 7 == 0) && x `mod` 42 /= 0 && (x < 305 || x > 411))[1..n]
--as the problem specifies, we have use a filter seeing if x is a multiple of 3 or 7 but not 42, and it is either less than 305 or greater than 411

--Problem 2
fibs = 1 : 1 : zipWith (+) fibs (tail fibs) --our fib list

oddFibsUpTo n = takeWhile(<=n) $ filter(odd) fibs --our list of odd fibs up until n

everyThirdOddFib n = e3of 0 (length (ofut)) (ofut) (ofut) --the main function to be called from the problem
 where ofut = oddFibsUpTo n

e3of :: Int -> Int -> [Int] -> [Int] -> [Int]
e3of inc len list original
 | inc >= len = list --if we have reached the length of the original list, we just return our initial list
 | inc `mod` 3 /= 2 = e3of (inc+1) len (delete (original !! inc) list) original --if we are not on the third Fibonacci number, we increment the counter and delete the number at the index of the counter from the main list
 | otherwise = e3of (inc+1) len list original --otherwise, we just increment
 
--Problem 3
allDigitsEvenAndNo5 n = aDEN5 $ show n

aDEN5 "0" = False --checking to make sure it is not a multiple of 5, which if it were because of being even, would have a 0 as the last digit
aDEN5 [] = True --if we have an empty list, we return true
aDEN5 (x:xs)
 | odd $ digitToInt x = False --if we find an odd digit, we return false
 | otherwise = aDEN5 xs --otherwise, we move to the next digit

sumList n = sum $ filter(allDigitsEvenAndNo5) [4..n] --the main function
--this problem says to have numbers from 1 to n that are not prime, do not have 5 as a factor, and have digits that are all even numbers
--we only need to check for digits that only have even numbers (and are not multiples of 5), as all primes except for 2 are odd numbers, and therefore have at least one odd digit
--1 would never be included in this list, as it is odd, so we could start from 2. but 2 is prime, and would not be included, so we could start from 3
--but 3 is also prime and is odd, so it is best to start from 4