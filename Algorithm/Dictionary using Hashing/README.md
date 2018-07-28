Dictionary using Hashing
===

Description
---
1. Build Hashing with a Text File Input test.txt
    + 定義Token & Words in Dictionary
    + Hashing (K=s0s1s2…sn)
        * Hash function 1: ![h1](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/h1.png) , ord(_c_) means ASCII code of character c

        * Hash function 2: ![h2](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/h2.png) , where 257≤p<q, p and q (hash table sizes) are prime numbers, ![](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/ie.png)


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
![p=7](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/CollisionPic/p7.png)
![p=11](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/CollisionPic/p11.png)
![p=13](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/CollisionPic/p13.png)

2. 執行效率:不同Hash function執行時間比較
![p=7](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/TimePic/p7.png)
![p=11](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/TimePic/p11.png)
![p=13](https://github.com/guiruli08650129/NUTN-Course/blob/master/Algorithm/Dictionary%20using%20Hashing/TimePic/p13.png)


