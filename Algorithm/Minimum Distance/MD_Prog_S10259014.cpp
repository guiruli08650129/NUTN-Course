#include <cstdio>
#include <cstdlib>
#include <cmath>
#include <iostream>
#include <iomanip>
#include <Windows.h>
#define swap(x,y) {int t; t = x; x = y; y = t;}
using namespace std;

void quickSort(int* A, int left, int right);
int MinDistance(int* A, int n, int dmin);

int main()
{
	int n;// Size of array
	int dmin = 2147483647;//Max integer in the range of int 

	cout << "This is a program to find the distance between the two closest elements in in an array of numbers," 
			"and you can choose the size of array." << endl;
	cout << "Please enter the size of array:";

	cin >> n;
	cout << "Please enter the element of integer:\n";

	//宣告動態陣列並寫入資料
	int *A;
	A = new int [n];
	
	for (int w = 0; w < n; w++)
	{
		cin >> A[w];
	}
	
	//--------------------------計算Algorithm1的時間---------------------------------------
	LARGE_INTEGER startTime, endTime, fre;
	double times;
	QueryPerformanceFrequency(&fre); //取得CPU頻率
	QueryPerformanceCounter(&startTime); //取得開機到現在經過幾個CPU Cycle
	dmin = MinDistance(A, n, dmin);
	QueryPerformanceCounter(&endTime); //取得開機到程式執行完成經過幾個CPU Cycle
	times = ((double)endTime.QuadPart - (double)startTime.QuadPart) / fre.QuadPart;
	//--------------------------Algorithm1的計算時間結束---------------------------------------


	//--------------------------計算Algorithm2的時間---------------------------------------
	LARGE_INTEGER startTime2, endTime2, fre2;
	double times2;
	QueryPerformanceFrequency(&fre2); //取得CPU頻率
	QueryPerformanceCounter(&startTime2); //取得開機到現在經過幾個CPU Cycle
	quickSort(A, 0, n - 1);
	for (int k = 0; k < n - 1; k++)
	{
		if (abs(A[k] - A[k + 1]) < dmin)
		{
			dmin = abs(A[k] - A[k + 1]);
		}
	}
	QueryPerformanceCounter(&endTime2); //取得開機到程式執行完成經過幾個CPU Cycle
	times2 = ((double)endTime2.QuadPart - (double)startTime2.QuadPart) / fre2.QuadPart;
	//--------------------------Algorithm2的計算時間結束---------------------------------------
	
	cout << "dmin = " << dmin << endl;
	cout << "MD_algorithm1_duration is " << fixed << setprecision(20) << times << 's' << endl;
	cout << "MD_algorithm2_duration is " << fixed << setprecision(20) << times2 << 's' << endl;

	delete[] A;
	system("pause");
	return 0;
}
void quickSort(int* A, int left, int right) {
	if (left < right)
	{
		int s = A[(left + right) / 2];
		int i = left - 1;
		int j = right + 1;

		while (1)
		{
			while (A[++i] < s);  // 向右找 
			while (A[--j] > s);  // 向左找 
			if (i >= j)
				break;
			swap(A[i], A[j]);
		}

		quickSort(A, left, i - 1);   // 對左邊進行遞迴 
		quickSort(A, j + 1, right);  // 對右邊進行遞迴 
	}
}
int MinDistance(int* A, int n, int dmin)
{
	int a, b;//the closest elements
	for (int i = 0; i < n - 1; i++)
	{
		for (int j = i + 1; j < n - 1; j++)
		{
			if (abs(A[i] - A[j]) < dmin)
			{
				dmin = abs(A[i] - A[j]);
				a = A[i];
				b = A[j];
			}
		}
	}
	cout << "The two closest elements in in an array of numbers is " << a << " and " << b << endl;
	return dmin;
}