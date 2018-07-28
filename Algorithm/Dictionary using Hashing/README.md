Dictionary using Hashing
===

Description
---
1. Build Hashing with a Text File Input test.txt
    + 定義Token & Words in Dictionary
    + Hashing (K=s0s1s2…sn)
        * Hash function 1: ![h1](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/h1.png =200x30) , ord(_c_) means ASCII code of character c

        * Hash function 2: ![h2](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/h2.pngg =500x30) , where 257≤p<q, p and q (hash table sizes) are prime numbers, ![](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/ie.png =300x30)


    + 實作 b 兩個Hashing， 討論不同的p(=11, 13, 97, 101, 149, 151, 199, 211, ...) and q(=211, 223, 227, 229, 233, ...)
        * 當發生Collision時的處理方式(利用linked list)
        * 統計Collision次數、平均Load Factor、平均Searching and Insertion的次數。

2. Add a word (input from file or keyboard )
3. Delete a word (input from file or keyboard)
4. Search a word (input from file or keyboard)
5. Searching with an input test file, part of the TextFile test.txt

Result
---
1. 功能討論:比較不同p和q發生Collision的次數
![p=7](https://i.imgur.com/q5L8r33.png =400x250)
![p=11](https://i.imgur.com/7AUW4wE.png =400x250)
![p=13](https://i.imgur.com/SKxWydJ.png =400x250)

2. 執行效率:不同Hash function執行時間比較
![p=7](https://i.imgur.com/a1KIwKv.png =400x250)
![p=11](https://i.imgur.com/MmE6ChV.png =400x250)
![p=13](https://i.imgur.com/j4wUgmm.png =400x250)


