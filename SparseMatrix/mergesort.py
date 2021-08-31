#!/c/Python38/python
a = [1,3,5,7,9]
b = [2,4,6,8,10]

a_pointer = 0
b_pointer = 0

c = []

while len(c) != len(a)+len(b):
	if a[a_pointer] < b[b_pointer]:
		c.append(a[a_pointer])
		a_pointer+=1
	else:
		c.append(b[b_pointer])
		b_pointer+=1
	print(c)

print(c)