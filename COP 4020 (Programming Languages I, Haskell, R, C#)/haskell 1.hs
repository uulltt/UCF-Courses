--Aaron Varkonyi
--aa344615
--3480260
--COP 4020 Spring 2018
--Getting Started With Haskell

--Our List of Colors
colors = ["Red","Green","Blue","White","Black","Yellow","Magenta","Cyan","Gray","Salmon","Purple","Lime"]
--Our list of Months
months = ["January","February","March","April","May","June","July","August","September","October","November","December"]
--combining the list into a list of tuples
colorMonths = zip colors months
--reversing the list of tuples
reverseColorMonths = reverse colorMonths
--finding the length of the lists
lastIndex = length colorMonths
main = do
putStrLn "Hello. My name is Aaron Varkonyi."
putStr "The Tuple List: "
print (colorMonths)
putStr "First index in tuple: "
print (colorMonths !! 0)
putStr "Last index in tuple: "
print (colorMonths !! (lastIndex - 1))
putStr "Third index in tuple: "
print (colorMonths !! 2)
putStr "Reversed list: "
print (reverseColorMonths)
