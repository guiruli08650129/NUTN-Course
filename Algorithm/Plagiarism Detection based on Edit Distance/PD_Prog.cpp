#include <iostream>
#include <string>
#include <fstream>
#include <sstream>
#include <math.h>
#include <iomanip>
#include <Windows.h>
using namespace std;
int md = 0;

int maxvalue(int a, int b) //比較大小並傳回最大值
{
	if (a > b)
		return a;
	else
		return b;
}
int minvalue(int a, int b, int c) //比較大小並傳回最小值
{
	int min = a;
	int q[3] = { a, b, c };
	for (int k = 0; k < 3; k++)
	{
		if (q[k] < min)
			min = q[k];
	}
	return min;
}
void freeSpace(int **A, int n, int m) //釋放記憶體
{
	for (int i = 0; i < n; i++)
	{
		delete[] A[i];
	}
	delete[] A;
}
int D(int ** A, string s1, string s2, int n, int m, int cho2) //Compute Edit Distance
{

	for (int i = 1; i < n; i++)
	{
		for (int j = 1; j < m; j++)
		{
			int k = 0;
			if ((s1[i - 1] != s2[j - 1]) && cho2 == 1)
				k = 2;
			if ((s1[i - 1] != s2[j - 1]) && cho2 == 2)
				k = 1;

			A[i][j] = minvalue((A[i - 1][j] + 1), (A[i][j - 1] + 1), (A[i - 1][j - 1] + k));
		}
	}

	cout << "Minimum Edit Distance is " << A[n - 1][m - 1] << endl;
	return A[n - 1][m - 1];
}
int main()
{
	double dot = 0;
	int cho1, cho2, cho3;
	cout << "This program is implement Edit Distance.\n";
	cout << "Do you want to compare string or implement Plagiarism Detection?\n"
		<< "1 > compare string , 2 > Plagiarism Detection\n>";
	cin >> cho1;
	while ((cho1 != 1) && (cho1 != 2))
	{
		cout << "ERROR!!\n" << "Enter 1 for compare string or enter 2 for Plagiarism Detection.\n>";
		cin >> cho1;
	}

	if (cho1 == 1) //compare 2 string from keyboard
	{
		string s1, s2;
		cout << "Enter two string you want to compare \nEnter s1 >";
		cin >> s1;
		cout << "Enter s2 >";
		cin >> s2;

		int n = s1.length() + 1;
		int m = s2.length() + 1;

		int **A;
		A = new int*[n];
		for (int i = 0; i < n; i++)// initialize array
		{
			A[i] = new int[m];
		}

		A[0][0] = 0;

		for (int i = 0; i < n; i++)
		{
			A[i][0] = i;
		}
		for (int j = 0; j < m; j++)
		{
			A[0][j] = j;
		}

		cout << "Do you want to use Levenshtein distance?\nEnter 1 for yes and 2 for no >";
		cin >> cho2;
		while ((cho1 != 1) && (cho1 != 2))
		{
			cout << "ERROR!!\n" << "Enter 1 to use Levenshtein distance or enter 2 to not use.\n>";
			cin >> cho2;
		}
		md = D(A, s1, s2, n, m, cho2);
		freeSpace(A, n, m);
	}

	else  //Plagiarism Detection
	{
		ifstream fin1;
		fin1.open("TestFile1.txt", ios::in);
		if (fin1.fail())
		{
			cout << "Input file opening failed.\n";
			exit(1);
		}

		ifstream fin2;
		fin2.open("TestFile12.txt", ios::in);
		if (fin2.fail())
		{
			cout << "Compare file opening failed.\n";
			exit(1);
		}

		cout << "Enter 1 for comparing file by paragraph, and enter 2 for comparing whole file.\n>";
		cin >> cho3;
		while ((cho3 != 1) && (cho3 != 2))
		{
			cout << "ERROR!!\n" << "Enter 1 for comparing file by paragraph or enter 2 for comparing whole file.\n>";
			cin >> cho3;
		}

		if (cho3 == 1) // paragraph comparism
		{
			string temp1, temp2;
			int w1 = 0;
			double *d;
			d = new double[100];//紀錄每個段落比較的ED中的最大值
			double temp_max = 0;
			double MAX_k = 0;

			LARGE_INTEGER startTime, endTime, fre;
			double times;
			QueryPerformanceFrequency(&fre);
			QueryPerformanceCounter(&startTime);


			while (!fin1.eof()) //逐段比對，共比對n*m次
			{
				getline(fin1, temp1);
				int n = temp1.length() + 1;

				fin2.close();

				ifstream fin2;
				fin2.open("TestFile12.txt", ios::in);
				if (fin2.fail())
				{
					cout << "Compare file opening failed.\n";
					exit(1);
				}

				while (!fin2.eof())
				{
					getline(fin2, temp2);
					int m = temp2.length() + 1;

					int **A;
					A = new int*[n];
					for (int i = 0; i < n; i++)// initialize array
					{
						A[i] = new int[m];
					}

					A[0][0] = 0;

					for (int i = 0; i < n; i++)
					{
						A[i][0] = i;
					}
					for (int j = 0; j < m; j++)
					{
						A[0][j] = j;
					}

					md = D(A, temp1, temp2, n, m, 1);
					temp_max = 1.00 - ((double)md / (double)maxvalue(temp1.length(), temp2.length()));
					if (temp_max < 0)
						temp_max = 0;
					if (temp_max > MAX_k)
						MAX_k = temp_max;
					freeSpace(A, n, m);
				}
				d[w1] = MAX_k;
				w1++;
				fin2.close();
			}

			QueryPerformanceCounter(&endTime);
			times = ((double)endTime.QuadPart - (double)startTime.QuadPart) / fre.QuadPart;
			cout << "MD_algorithm1_duration is " << fixed << setprecision(20) << times << 's' << endl;


			for (int i = 0; i < w1; i++)
			{
				dot += d[i];
			}
			double avg = (dot / (w1 + 1));
			double sigma = 0;
			for (int i = 0; i < w1; i++)
			{
				sigma += (d[i] - avg) * (d[i] - avg);
			}
			double var = sqrt(sigma);

			cout << "Degree of plagiarism: " << avg << endl;
			cout << "Variance of plagiarism for each paragraph: " << sigma << endl;
			cout << "Standard deviation of plagiarism for each paragraph: " << var << endl << endl;
			delete [] d;
		}

		else //compare whole file
		{
			string temp1, temp2;
			string s1, s2;

			while (!fin1.eof())
			{
				getline(fin1, temp1);
				s1 += temp1;
			}
			while (!fin2.eof())
			{
				getline(fin2, temp2);
				s2 += temp2;
			}

			int n = s1.length() + 1;
			int m = s2.length() + 1;
			int **A;
			A = new int*[n];
			for (int i = 0; i < n; i++)// initialize array
			{
				A[i] = new int[m];
			}

			A[0][0] = 0;

			for (int i = 0; i < n; i++)
			{
				A[i][0] = i;
			}
			for (int j = 0; j < m; j++)
			{
				A[0][j] = j;
			}

			LARGE_INTEGER startTime2, endTime2, fre2;
			double times2;
			QueryPerformanceFrequency(&fre2);
			QueryPerformanceCounter(&startTime2);

			md = D(A, s1, s2, n, m, 1);
			dot = 1.00 - ((double)md / (double)maxvalue(s1.length(), s2.length()));
			if (dot < 0)
				dot = 0;
			QueryPerformanceCounter(&endTime2);
			times2 = ((double)endTime2.QuadPart - (double)startTime2.QuadPart) / fre2.QuadPart;
			cout << "MD_algorithm2_duration is " << fixed << setprecision(20) << times2 << 's' << endl;
			cout << "Degree of plagiarism: " << dot << endl;
			freeSpace(A, n, m);
		}
	}
	system("pause");
	return 0;
}