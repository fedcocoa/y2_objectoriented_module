#!/usr/bin/python3.8

def mySort(inputList):
	if len(inputList) <= 1:
		return inputList
	else:
		pivot = inputList[len(inputList) // 2]
		left = []
		right = []
		for element in inputList:
			if element != pivot:
				if element < pivot:
					left.append(element)
				else:
					right.append(element)
		return mySort(left)+[pivot]+mySort(right)


print(mySort([1,0,2,9,3,8,4,7,5,6]))
