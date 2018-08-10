#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <iostream>
#include <fstream>
#include <sstream>
#include <string>
#include <math.h>
#include <bitset>
#include <stdlib.h>
#include <cstdlib>
using namespace std;
int A = 0x000000;
int X = 0x000000;
int B = 0x000000;
int S = 0x000000;
int T = 0x000000;
int F = 0x000000;
int PC = 0x000000;
int SW = 0x000000;
int L = 0xffffff;
int arr[2][100];//保留記憶體
int OPtable[36][2]{{ 144, 2}, { 180, 2 }, { 160, 2 }, { 156, 2 }, { 152, 2 }, { 172, 2 }, { 148, 2 }, { 184, 2 },
                    { 24, 7 }, { 64, 7 }, { 40, 7 }, { 36, 7 }, { 60, 7 }, { 48, 7 }, { 52, 7 }, { 56, 7 }, { 72, 7 },
                    { 0, 7 }, { 104, 7 }, { 80, 7 }, { 8, 7 }, { 108, 7 }, { 116, 7 }, { 4, 7 }, { 32, 7 }, { 76, 7 }, { 12, 7 },
                    { 120, 7 }, { 84, 7 }, { 20, 7 }, { 124, 7 }, { 232, 7 }, { 132, 7 }, { 16, 7 }, { 28, 7 }, { 44, 7 }};
int OPcode(int n)//用OP code判斷format
{
    int format = -1;
    int k = n & 0xFC;//假設n,i都為0找出OP code
    char op1;

    (n / 16) < 10 ? op1 = (n / 16) + 48 : op1 = (n / 16) + 55;//使用mask取出第一個字元

    //判斷format
    if (op1 == '9' || op1 == 'A' || op1 == 'B')
        format = 2;
    else if (op1 == 'C' || op1 == 'F')
        format = 1;
    else
        format = 7;

    cout << "OP CODE = " << hex << k << endl;
    return format;
}
int Reg_Judge(int num)//判斷暫存器
{
    switch (num)
    {
    case 0:
        return A; break;
    case 1:
        return X; break;
    case 2:
        return L; break;
    case 3:
        return B; break;
    case 4:
        return S; break;
    case 5:
        return T; break;
    case 6:
        return F; break;
    case 7:
        return F; break;
    case 8:
        return PC; break;
    case 9:
        return SW; break;
    default:
        return -1;  break;
    }
}
void format2(int n1, int n2)//format2指令的功能
{
    int k = n1 & 0xFC;//假設n,i都為0找出OP code
    int R1 = Reg_Judge(n2 / 16);//找出第一個暫存器
    int R2 = Reg_Judge(n2 % 16);//找出第一個暫存器

    cout << "R1: " << R1 << " " << "R2: " << R2 << endl;

    if (k == OPtable[0][0])
        R2 = (R1 + R2);
    else if (k == OPtable[1][0])
        R1 = 0x000000;
    else if (k == OPtable[2][0])
    {	//比較R1和R2
        if (R1 == R2)
            SW = 0x0000C0;
        else
            (R1 > R2) ? SW = 0x000080 : SW = 0x000040;
    }
    else if (k == OPtable[3][0])
        R2 = (R2 / R1);
    else if (k == OPtable[4][0])
        R2 = R1 * R2;
    else if (k == OPtable[5][0])
        R2 = R1;
    else if (k == OPtable[6][0])
        R2 = (R2 - R1);
    else if (k == OPtable[7][0])
    {
        X = X + 1;
        //比較X和R1
        if (X == R1)
            SW = 0x0000C0;
        else
            (X > R1) ? SW = 0x000080 : SW = 0x000040;
    }
    else
        ;
}
int format3(int n1, int n2, int n3)//format3判斷定址
{
    int TA = 0;
    bitset<sizeof(n2 / 16)> mode(n2 / 16);//用第三個字元找出x, b, p, e
    int disp = (n2 % 16)*pow(16, 2) + n3;//取出disp
    bitset<sizeof(n1 % 16)> s(n1 % 16);//取出n, i

    if (s[0] == 1 && s[1] == 0)//n=0, i=1 立即定址
        TA = disp;
    else
    {
        if (mode[1] == 1 && mode[2] == 0)//b=1, p=0 PC相對定址
            (mode[3] == 0) ? TA = disp + PC + 3 : TA = disp + PC + 3 + X;
        else if (mode[1] == 0 && mode[2] == 1)//b=0, p=1 基底相對定址
            (mode[3] == 0) ? TA = disp + B : TA = disp + B + X;
    }

    cout << "format3 TA = " << hex << TA << endl;
    return TA;
}
int format4(int n1, int n2, int n3, int n4)
{
    int TA = (n2 % 16)*pow(16, 4) + n3*pow(16, 2) + n4;//取出address
    cout << "format4 TA = " << hex << TA << endl;
    return TA;
}
int transfer_char_to_hex(char c)//把字元轉成數字
{
    int num = (int)c - 48;
    (num < 10) ? num = num : num = (num - 7);
    return num;
}
int Judge_n_i(int num)//由n, i判斷定址模式
{
    int NIvalue = 0;
    NIvalue = num & 0x3;

    if(NIvalue == 1)
        cout << "Immediate addressing\n";
    else if(NIvalue == 2)
        cout << "simple addressing\n";
    else if(NIvalue == 3)
        cout << "indirect addressing\n";
    else
        ;
    return NIvalue;
}
int OPeffect(int TA, int n1)//OP code指令的功能
{
    int k = n1 & 0xFC;//假設n,i都為0找出OP code

    if (k == OPtable[8][0])
        A = A + TA;
    else if (k == OPtable[9][0])
        A = A & TA;
    else if (k == OPtable[10][0])
    {//比較A和TA
        if (A == TA)
            SW = 0x0000C0;
        else
            (A > TA) ? SW = 0x000080 : SW = 0x000040;
    }
    else if (k == OPtable[11][0])
        A = A / TA;
    else if (k == OPtable[12][0])
    {//PC<-m
        PC = TA & 0x3;
    }
    else if (k == OPtable[13][0])
    {
        if (SW == 0x0000C0)
            PC = TA & 0x3;
    }
    else if (k == OPtable[14][0])
    {
        if (SW == 0x000080)
            PC = TA & 0x3;
    }
    else if (k == OPtable[15][0])
    {
        if (SW == 0x000040)
            PC = TA & 0x3;
    }
    else if (k == OPtable[16][0])
    {
        L = PC;
        PC = TA % 256;
    }
    else if (k == OPtable[17][0])
        A = TA;
    else if (k == OPtable[18][0])
        B = TA;
    else if (k == OPtable[19][0])
    {
        A = (A & 0xFFFF00) + (TA & 0x0FF);
    }
    else if (k == OPtable[20][0])
        L = TA;
    else if (k == OPtable[21][0])
        S = TA;
    else if (k == OPtable[22][0])
        T = TA;
    else if (k == OPtable[23][0])
        X = TA;
    else if (k == OPtable[24][0])
        A = A * TA;
    else if (k == OPtable[25][0])
        PC = L;
    else if (k == OPtable[26][0])
        TA = A;
    else if (k == OPtable[27][0])
        TA = B;
    else if (k == OPtable[28][0])
    {
        TA = (TA & 0xF00) + (A & 0x0000FF);
    }
    else if (k == OPtable[29][0])
        TA = L;
    else if (k == OPtable[30][0])
        TA = S;
    else if (k == OPtable[31][0])
        TA = SW;
    else if (k == OPtable[32][0])
        TA = T;
    else if (k == OPtable[33][0])
        TA = X;
    else if (k == OPtable[34][0])
        A = A - PC;
    else if (k == OPtable[35][0])
    {
        X = X + 1;
        if (X == TA)
            SW = 0x0000C0;
        else
            (X > TA) ? SW = 0x000080 : SW = 0x000040;
    }
    else
        ;

    return TA;
}
void initialize(ifstream& fin)//把檔案內容從文字檔取出並存入maintain的陣列裡
{
    string s;
    string fileName, fileLength, fileStart;
    char H;

    fin >> H >> s;
    fileName = s.substr(0, 6);
    fileStart = s.substr(6, 6);
    fileLength = s.substr(12, 6);

    char recode;
    string s2;
    string target;
    fin >> recode;
    do{
        if (recode == 'T')
        {
            fin >> s2;
            s2.erase(0, 8);
            target += s2;
        }
        fin >> recode;
    } while (recode != 'E');

    char excutable[7];
    if (recode == 'E')
    {
        fin >> excutable;
        long long a = strtol(excutable, NULL,16);
        PC = a;
    }

    for (int i = 0; i < (target.length() / 2); i++)
    {
        string temp = target.substr(2 * i, 2);
        arr[0][i] = i;
        arr[1][i] = transfer_char_to_hex(temp[0]) * 16 + transfer_char_to_hex(temp[1]);
    }
}
void output_to_file()//將結果輸出到檔案
{
    ofstream fout("output.txt");
    if (!fout){
        cout << "File output.txt can't open.\n";
    }

    fout << "Reg A = " << hex << A << endl;
    fout << "Reg X = " << hex << X << endl;
    fout << "Reg B = " << hex << B << endl;
    fout << "Reg S = " << hex << S << endl;
    fout << "Reg T = " << hex << T << endl;
    fout << "Reg F = " << hex << F << endl;
    fout << "Reg PC = " << hex << PC << endl;
    fout << "Reg SW = " << hex << SW << endl;
    fout << "Reg L = " << hex << L << endl;
    fout << "____________________________________________" << endl;
    fout.close();
}

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
}

MainWindow::~MainWindow()
{
    delete ui;
}


void MainWindow::on_fileOpenButton_clicked()//選擇要載入的檔案
{
    QString FileName;
    FileName = QFileDialog::getOpenFileName(this, "Open my file");//選擇要開啟的檔案
    if(!FileName.isEmpty())//若有讀到檔名則初始所有暫存器的值
    {
        ui->fileNameTextBox->setText(FileName);
        QString AA = QString::number(A,16);
        QString XX = QString::number(X,16);
        QString LL = QString::number(L,16);
        QString BB = QString::number(B,16);
        QString SS = QString::number(S,16);
        QString TT = QString::number(T,16);
        QString FF = QString::number(F,16);
        QString PCPC = QString::number(PC,16);
        QString SWSW = QString::number(SW,16);
        ui->labelA_2->setText(AA);
        ui->labelX_2->setText(XX);
        ui->labelL_2->setText(LL);
        ui->labelB_2->setText(BB);
        ui->labelS_2->setText(SS);
        ui->labelT_2->setText(TT);
        ui->labelF_2->setText(FF);
        ui->labelPC_2->setText(PCPC);
        ui->labelSW_2->setText(SWSW);
    }

    string s = FileName.toStdString();
    ifstream fin(s);//開啟要載入的檔案
    if (!fin){
        cout << "File FileName.txt can't open.\n";
    }
    initialize(fin);//把檔案內容從文字檔取出並存入maintain的陣列裡
    fin.close();
}

void MainWindow::on_fileOpenButton_3_clicked()//清除視窗的數字
{
    ui->fileNameTextBox->clear();
    ui->ShowCommand->clear();
    ui->labelA_2->clear();
    ui->labelX_2->clear();
    ui->labelL_2->clear();
    ui->labelB_2->clear();
    ui->labelS_2->clear();
    ui->labelT_2->clear();
    ui->labelPC_2->clear();
    ui->labelSW_2->clear();
}

void MainWindow::on_fileOpenButton_2_clicked()
{
    int formatVal = 0;
    int TA = 0;
    int  NIvalue = 0;
    int value;
    cout << "_____________________________________________\n";
    formatVal = OPcode(arr[1][PC]);//讀入前兩個字元

    if(formatVal == 2)
    {
        QString s1 = QString::number(arr[1][PC], 16);
        QString s2 = QString::number(arr[1][PC+1], 16);
        ui->ShowCommand->setText(s1+s2);//顯示目前執行的指令
        cout << "Oprand: " << arr[1][PC] << arr[1][PC+1] << endl;
        cout << "format = " << formatVal << endl;
        format2(arr[1][PC], arr[1][PC + 1]);
        PC += 2;
    }
    else if (formatVal == 7)
    {
        bitset<sizeof(arr[1][PC + 1] / 16)> judge_format(arr[1][PC + 1] / 16);

        if (judge_format[0] == 0)
        {            
            QString s1 = QString::number(arr[1][PC], 16);
            QString s2 = QString::number(arr[1][PC+1], 16);
            QString s3 = QString::number(arr[1][PC+2], 16);
            ui->ShowCommand->setText(s1+s2+s3);//顯示目前執行的指令
            cout << "Oprand: " << arr[1][PC] << arr[1][PC+1] << arr[1][PC+2] << endl;
            formatVal = 3;
            cout << "format = " << formatVal << endl;
            NIvalue = Judge_n_i(arr[1][PC] % 16);// NIvalue = 0(SIC), 1(immediate), 2(indirect), 3(simple)
            TA = format3(arr[1][PC], arr[1][PC + 1], arr[1][PC + 2]);//calculate TA

            if (NIvalue == 1)
            {
                value = OPeffect(TA, arr[1][PC]);
                arr[1][30] = value / 65536;
               arr[1][30 + 1] = (value / 256) % 65536;
                arr[1][30 + 2] = value % 256;
            }
            if (NIvalue == 3)
            {
                int temp;
                int m = TA & 0x00F;

                for (int j = 0; j < 100; j++)
                {
                    if (m == arr[0][j])
                    {
                        TA = arr[1][j] * pow(16, 4) + arr[1][j + 1] * pow(16, 2) + arr[1][j + 2];
                        temp = j;
                    }
                }

                value = OPeffect(TA, arr[1][PC]);
                arr[1][30] = value / 65536;
                arr[1][30 + 1] = (value / 256) % 65536;
                arr[1][30 + 2] = value % 256;
            }
            else if (NIvalue == 2)
            {
                int temp;
                int m = TA & 0x00F;

                for (int j = 0; j < 100; j++)
                {
                    if (m == arr[0][j])
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            if (arr[0][j] == arr[0][i])
                                TA = arr[1][i] * pow(16, 4) + arr[1][i + 1] * pow(16, 2) + arr[1][i + 2];
                            temp = i;
                        }
                    }
                }

                value = OPeffect(TA, arr[1][PC]);
                arr[1][30] = value / 65536;
                arr[1][30 + 1] = (value / 256) % 65536;
                arr[1][30 + 2] = value % 256;
            }
            else
                ;
            PC += 3;
        }
        else
        {            
            QString s1 = QString::number(arr[1][PC], 16);
            QString s2 = QString::number(arr[1][PC+1], 16);
            QString s3 = QString::number(arr[1][PC+2], 16);
            QString s4 = QString::number(arr[1][PC+3], 16);
            ui->ShowCommand->setText(s1+s2+s3+s4);//顯示目前執行的指令
            cout << "Oprand: " << arr[1][PC] << arr[1][PC+1] << arr[1][PC+2] << arr[1][PC+3] << endl;
            formatVal = 4;
            cout << "format = " << formatVal << endl;
            NIvalue = Judge_n_i(arr[1][PC] % 16);// NIvalue = 0(SIC), 1(immediate), 2(indirect), 3(simple)
            TA = format4(arr[1][PC], arr[1][PC + 1], arr[1][PC + 2], arr[1][PC + 3]);//calculate TA

            if (NIvalue == 1)
            {
                value = OPeffect(TA, arr[1][PC]);
                arr[1][TA] = value / 65536;
                arr[1][TA + 1] = (value / 256) % 65536;
                arr[1][TA + 2] = value % 256;
            }
            else if (NIvalue == 3)
            {
                int temp;
                int m = TA & 0x00F;

                for (int j = 0; j < 100; j++)
                    if (m == arr[0][j])
                    {
                    TA = arr[1][j] * pow(16, 4) + arr[1][j + 1] * pow(16, 2) + arr[1][j + 2];
                    temp = j;
                    }

                value = OPeffect(TA, arr[1][PC]);
                arr[1][temp] = value / 65536;
                arr[1][temp + 1] = (value / 256) % 65536;
                arr[1][temp + 2] = value % 256;
            }
            else if (NIvalue == 2)
            {
                int temp;
                int m = TA & 0x00F;

                for (int j = 0; j < 100; j++)
                {
                    if (m == arr[0][j])
                    {
                        for (int i = 0; i < 100; i++)
                        {
                            if (arr[0][j] == arr[0][i])
                                TA = arr[1][i] * pow(16, 4) + arr[1][i + 1] * pow(16, 2) + arr[1][i + 2];
                            temp = i;
                        }
                    }
                }
                value = OPeffect(TA, arr[1][PC]);
                arr[1][temp] = value / 65536;
                arr[1][temp + 1] = (value / 256) % 65536;
                arr[1][temp + 2] = value % 256;
            }
            PC += 4;
        }
    }
    else
    {
        cout << "ERROR FORMAT!!!!!!!!!!!!!!" << endl;
    }

    output_to_file();
    cout << "A: " << A << endl;
    cout << "X: " << X << endl;
    cout << "L: " << L << endl;
    cout << "B: " << B << endl;
    cout << "S: " << S << endl;
    cout << "T: " << T << endl;
    cout << "F: " << F << endl;
    cout << "PC: " << PC << endl;
    cout << "SW: " << SW << endl;

    //將暫存器改變的數值顯示在視窗上
    QString AA = QString::number(A,16);
    QString XX = QString::number(X,16);
    QString LL = QString::number(L,16);
    QString BB = QString::number(B,16);
    QString SS = QString::number(S,16);
    QString TT = QString::number(T,16);
    QString FF = QString::number(F,16);
    QString PCPC = QString::number(PC,16);
    QString SWSW = QString::number(SW,16);
    ui->labelA_2->setText(AA);
    ui->labelX_2->setText(XX);
    ui->labelL_2->setText(LL);
    ui->labelB_2->setText(BB);
    ui->labelS_2->setText(SS);
    ui->labelT_2->setText(TT);
    ui->labelF_2->setText(FF);
    ui->labelPC_2->setText(PCPC);
    ui->labelSW_2->setText(SWSW);
}
