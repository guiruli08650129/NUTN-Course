PageRank
===


Description
---
1. Subset of a web graph:
    - 875,713 node and 5105,039 edge
    - Max(id) = 916428
    - From Node(A) to Node(B) separated by space.
2. Target:
    - Implement PageRank algorithm.
    - Find the top 20 webpage nodes and list their rank values. (Let beta = 0.8)
    - Change beta from 0.7, 0.75, 0.8, 0.85 to 0.9 and show top 20 rank values with graph.
3. Environment:
    - Java version: 1.8.0_111
    - R Package: iGraph, arules, Matrix, expm.
    - OS: windows 7


Result
---
![result1](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Plagiarism%20Detection%20based%20on%20Edit%20Distance/picture/testFile1.png)
![result2](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Plagiarism%20Detection%20based%20on%20Edit%20Distance/picture/testFile2.png)

Analysis
---
- Java Preprocess:
    - Read: 1s
    - Compute weight & Output: 9445 s，about 2 hr 37 m 
- When iterations=15, the rank doesn't changed with the same beta.
- While the value of beta is changed, it will out of top20.



