#include <fstream>
#include <iostream>
#define N 11
#define swap(x, y) {int temp = x; x = y, y = temp;}
using namespace std;
void print(int *a)
{
	for (int i = 0; i < N; i++)
		cout << a[i] << " ";
	cout << endl;
}
void sort(int *a, int left, int right)
{
	if (left < right)
	{
		int s = a[(left + right) / 2];
		int i = left - 1;
		int j = right + 1;
		while (1)
		{
			while (a[++i] < s);
			while (a[--j] > s);
			if (i >= j)
				break;
			swap(a[i], a[j]);
		}
		print(a);
		sort(a, left, i - 1);
		sort(a, j + 1, right);
	}
}
int main()
{
	fstream fin;
	fin.open("test.txt");

	int *a;
	a = new int[N];
	for (int i = 0; i < N; i++)
	{
		fin >> a[i];
	}

	sort(a, 0, N-1);
	print(a);


	system("pause");
	return 0;
}