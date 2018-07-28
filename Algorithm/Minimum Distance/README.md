Minimum Distance
===

Description
---
1. Implement the minimum distance with following algorithm:
```
dmin ← ∞
	for i ← 0 to n-1 do
		for j ← i+1 to n-1 do
			if i ≠ j and |A[i] – A[j]| < dmin
			dmin ← |A[i] – A[j]|
return dmin

```

2. Improve the minimum distance with following algorithm:
```
if (left < right) do
    s = A[(left + right) / 2]   i = left – 1   j = right + 1;
    while 1 do
        while (A[++i] < s)
        while (A[--j] > s)
        if (i >= j)  break
        swap(A[i], A[j])
    quickSort(A, left, i - 1)
    quickSort(A, j + 1, right)
    
for k ← 0 to n-1 do
    if |A[k] - A[k + 1]| < dmin 
    dmin = |A[k] - A[k + 1]|
```
3. Compare two algorithm and output the execution time for each.

Result
---
- Space Complexity: algorithm 1 and algorithm 2 are both O(n).
- Time Complexity:
    * Algorithm 1: ![algo1](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Minimum%20Distance/picture/algo1.png)
    * Algorithm 2: ![algo2](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Minimum%20Distance/picture/algo2.png)

- Algorithm 1 is slower than algorithm 2.
![result](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Minimum%20Distance/picture/result.png)
