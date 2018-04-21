import Data.List
import Data.Char

--Aaron Varkonyi
--COP 4020 Spring 2018

--Caesar Cipher

let2nat :: Char -> Int
let2nat a = (ord $ toLower a) - 97

nat2let :: Int -> Char 
nat2let i = (chr (i+97))

shift :: Int -> Char -> Char
shift i a = if isLetter a then nat2let $ ((let2nat a) + i) `mod` 26 else a

encode :: Int -> String -> String
encode i s =  [shift i a | a <- s]

decode :: Int -> String -> String
decode i s = encode (-i) s

--Frequency Analysis
table :: [Float]
table = [8.2,1.5,2.8,4.3,12.7,2.2,2.0,6.1,7.0,0.2,0.8,4.0,2.4,6.7,7.5,1.9,0.1,6.0,6.3,9.1,2.8,1.0,2.4,0.2,2.0,0.1]

lowers :: String -> Int
lowers s = length $ filter(isLower) s

count :: Char -> String -> Int
count a s = length $ filter(==a) s

percent :: Int -> Int -> Float
percent i j = (fromIntegral i / fromIntegral j)*100

freqs :: String -> [Float]
freqs s = [percent (count a s) (lowers s) | a<-['a'..'z']]

rotate :: Int -> [a] -> [a]
rotate i s = (drop i s)++(take i s)

chisqr :: [Float] -> [Float] -> Float
chisqr os es = sum (zipWith (/) (map (^2) (zipWith (-) os es)) es)

position :: (Eq a)=> a -> [a] -> Int
position i list = ((length (takeWhile(/=i) list)))

crack :: String -> String
crack s = decode (((position (minimum f) f))) s
 where f = map(\x -> chisqr (rotate x (freqs s)) table)[0..25]