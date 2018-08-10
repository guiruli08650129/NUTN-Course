FTP_Client
===

Setup
---
1. 請下載並將FileZilla安裝至預設路徑：C:\Program Files (x86)\ FileZilla Server
2. 啟動FileZilla，設定Host：localhost，Post：14147，按下Connect
3. 點選「Edit」>「Users」進行使用者設定，在「General」的索引標籤，點選「Add」，建立使用者帳號(user)並設定密碼(密碼可隨使用者喜好設置，但不可沒有密碼)
4. 在「Share folders」的索引標籤，點選「Add」，建立使用者目錄。
5. 請將遠端測試資料中的kay資料夾置於C:\Program Files (x86)\FileZilla Server\之下，並將FileZilla Server的根目錄資料夾設定為user資料夾。
6. 確認根目錄的位置，並勾選資料夾的權限。
7. 點選「Speed Limits」的索引標籤，輸入上、下載的速度皆為100 kB/s，最後點選「OK」，完成使用者帳號的建立。
8. Local端的資料請放在JAVA專案的工作目錄下。
9. Main function在gui.java


Description
---
使用Socket模擬FTP Sever和Client的互動，並實作FTP的通訊協定命令。
1. 設計一個具有圖形使用者介面FTP Client程式，內含下列ftp通訊協定命令：
    - USER, PASS, CWD, QUIT, PORT, TYPE, RETR, STOR, DELE, RMD, MKD, PWD, LIST, NLST.。
    - 為測試方便，請自行安裝FTPserver（可參考FileZilla）.
2. 程式開發以上課講義為基礎，禁用sun.net.ftp等類似開發套件。







