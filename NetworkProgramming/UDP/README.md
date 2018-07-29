LibraryManagementSystem
===

Setup
---
1. Build 2 project: udpServer and udpClient
2. Put java files which in udpServer folder into udpServer project. MediaList folder is also under udpServer project, too.
3. Put java files which in udpClient folder into udpClient project.
4. Main function "gui" is in udpClient project.

Description
---
1. 以Java UDP datagrampacket 和dataproramsocket來設計網路音樂/電影伺服器和客戶端點播界面。
2. 當客戶端點播圖形界面與多媒體伺服器連上後，會出現伺服器傳送的mp3音樂(或電影)清單，點選其中一首音樂(或電影)，當下載完成時，啟動客戶端Windows media player 播放這首音樂(或電影)。
3. 以多個執行緒支援多人同時上網點播，且客戶端可不斷執行，直至自願離開。

Step
---
1. 啟動Server端，按下確定後才會啟動。
2. Client尚未與Server端連線時影音清單為空白。
3. Client與Server連線後會回傳所有影音資料夾中的檔案。
4. 選擇檔案下載，下載完畢後自動開啟。

:::danger
注意：
1. 影音的檔名加附檔名不可超過16個字。
2. 一個Client一次只能下載一個檔案。
:::


