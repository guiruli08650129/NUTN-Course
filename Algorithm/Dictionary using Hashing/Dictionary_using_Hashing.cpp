#include<iostream>
#include <fstream>
#include <sstream>
#include <string>
#include<cstdlib>
#include<string>
#include<cstdio>
#include <Windows.h>
#include<iomanip>
using namespace std;
const int TABLE_SIZE = 229;
const int PRIME_NUM = 13;

class HashNode
{
public:
	int key;
	string s;
	HashNode* next;
	HashNode(int key, string s)
	{
		this->key = key;
		this->s = s;
		this->next = NULL;
	}
};

class HashMap
{
private:
	HashNode** htable;
protected:
	int colli = 0;
public:
	HashMap()
	{
		htable = new HashNode*[TABLE_SIZE];
		for (int i = 0; i < TABLE_SIZE; i++)
			htable[i] = NULL;
	}
	~HashMap()
	{
		for (int i = 0; i < TABLE_SIZE; ++i)
		{
			HashNode* entry = htable[i];
			while (entry != NULL)
			{
				HashNode* prev = entry;
				entry = entry->next;
				delete prev;
			}
		}
		delete[] htable;
	}

	int getColli() { return colli; };

	void resetColli() { colli = 0; };

	int HashFunc1(string s)
	{
		unsigned int temp = 0;
		for (int i = 0; i < s.length(); i++)
			temp = temp += s[i];
		return temp % TABLE_SIZE;
	}
	int HashFunc2(string s)
	{
		unsigned long long temp = 0;
		int end = s.length();
		for (int i = end - 1; i >= 0; i--)
			temp = temp * PRIME_NUM + s[i];
		return temp % TABLE_SIZE;
		
	}

	void Insert(int key, string s, int hashFunc)
	{
		int hash_val = 0;
		hashFunc == 1 ? hash_val = HashFunc1(s) : hash_val = HashFunc2(s);
		HashNode* prev = NULL;
		HashNode* entry = htable[hash_val];
		while (entry != NULL)
		{
			colli += 1;
			if (entry->s != s)
			{
				prev = entry;
				entry = entry->next;
			}
			else
				break;
		}
		if (entry == NULL)
		{
			entry = new HashNode(key, s);
			if (prev == NULL)
			{
				htable[hash_val] = entry;
			}
			else
			{
				prev->next = entry;
			}
		}
		else
		{
			entry->s = s;
		}
	}

	void Remove(string s, int hashFunc)//Q:無法移除不是最後一的node
						//Q:當所有元素被刪光後再搜尋已經刪除的元素時會出現錯誤
	{
		int hash_val = 0;
		hashFunc == 1 ? hash_val = HashFunc1(s) : hash_val = HashFunc2(s);
		HashNode* entry = htable[hash_val];
		HashNode* prev = NULL;
		if (entry == NULL || entry->key != hash_val)
		{
			cout << "No Element found at key " << hash_val << endl;
			return;
		}
		while (entry->next != NULL)
		{
			prev = entry;
			entry = entry->next;
		}
		if (prev != NULL)
		{
			prev->next = entry->next;
		}
		delete entry;
		cout << "Element "<< s << " has been deleted" << endl;
	}

	int Search(string s, int hashFunc)
	{
		bool flag = false;
		bool find = false;
		int hash_val = 0;
		hashFunc == 1 ? hash_val = HashFunc1(s) : hash_val = HashFunc2(s);
		HashNode* entry = htable[hash_val];
		while (entry != NULL)
		{
			if (entry->key == hash_val)
			{
				if (entry->s == s)
				{
					cout << entry->s << " is in HashMap.\n";
					find = true;
				}

				flag = true;
			}
			entry = entry->next;
		}
		if (!find)
		{
			cout << s << " isn't in the HashMap.\n";
			return 0;
		}
		if (!flag)
			return -1;
	}
	void load_factor()
	{
		int load = 0;
		HashNode* entry = htable[0];
		for (int i = 0; i < TABLE_SIZE; i++)
		{
			if (htable[i] == NULL)
				load += 1;
		}
		cout << "Average number of load factor is " << (double)(TABLE_SIZE - load) / (double)TABLE_SIZE << endl;
	}
};

void search_file( int ans, HashMap hash, int hashFunc)
{
	string temp, s;
	bool isEqual = true;
	double total = 0;
	double failed = 0;
	
	ifstream fopen;
	if (ans == 1)
		fopen.open("TestFile11.txt", ios::in);
	else
		fopen.open("TestFile12.txt", ios::in);

	if (fopen.fail())
	{
		cout << "Input file opening failed.\n";
		exit(1);
	}

	while (!fopen.eof())
	{
		getline(fopen, temp);
		stringstream ss(temp);
		while (getline(ss, s, ' '))
		{
			if (hash.Search(s, hashFunc) == 0)
			{
				failed += 1;
				isEqual = false;
			}
			total += 1;
		}
	}

	if (isEqual == false)
	{
		cout << "\nTestFile1" << ans << " isn't part of TestFile.\n";
		cout << "The probability of sucessful searching is " << (total - failed) / total << endl;
		cout << "The probability of unsucessful searching is " << failed / total << endl;
	}
	else
		cout << "\nTestFile1" << ans << " is part of TestFile.\n";
}
int main()
{
	HashMap hash;
	string s;
	int choice, choose_text, hashFunc;
	bool ans = true;
	string temp;

	ifstream fin;
	fin.open("TestFile1.txt", ios::in);
	if (fin.fail())
	{
		cout << "Input file opening failed.\n";
		exit(1);
	}
	
	cout << "Please choose HashFunc1 or HashFunc2 (enter 1 or 2):";
	cin >> hashFunc;
	while (hashFunc != 1 && hashFunc != 2)
	{
		cout << "Input error!! Please enter 1 or 2:";
		cin >> hashFunc;
	}

	while (!fin.eof())
	{
		getline(fin, temp);
		
		stringstream ss(temp);
		while (getline(ss, s, ' '))
		{
			hashFunc == 1 ? hash.Insert(hash.HashFunc1(s), s, hashFunc) : hash.Insert(hash.HashFunc2(s), s, hashFunc);
			temp.erase(0, s.length());
		}
	}
	
	cout << "Collision happened " << hash.getColli() << " times to initialize HashMap." << endl;
	cout << "Average of searching and insertion number is " << (double)hash.getColli() / (double)TABLE_SIZE << endl;
	hash.load_factor();

	do
	{
		cout << "\n---------------------------" << endl;
		cout << " Operations on Hash Table" << endl;
		cout << "---------------------------" << endl;
		cout << "1.Insert element into the table" << endl;
		cout << "2.Search element from the key" << endl;
		cout << "3.Delete element at a key" << endl;
		cout << "4.Search with an input file" << endl;
		cout << "5.Exit" << endl;
		cout << "Enter your choice: ";
		cin >> choice;
		switch (choice)
		{
			case 1:
			{
				cout << "Enter word to be inserted: ";
				cin >> s;
				hash.resetColli();
				hashFunc == 1 ? hash.Insert(hash.HashFunc1(s), s, hashFunc) : hash.Insert(hash.HashFunc2(s), s, hashFunc);
				break;
			}
			case 2:
			{
				cout << "Enter word to be searched: ";
				cin >> s;
				if (hash.Search(s, hashFunc) == -1)
				{
					cout << "No element found at hash table " << endl;
					continue;
				}
				break;
			}
			case 3:
			{
				cout << "Enter word to be deleted: ";
				cin >> s;
				hash.Remove(s, hashFunc);
				break;
			}
			case 4:
			{
				LARGE_INTEGER startTime, endTime, fre;
				double times;					
				cout << "Choose TestFile11 or TestFile12 (enter 1 or 2):";				
				cin >> choose_text;

				while (choose_text != 1 && choose_text != 2)
				{
					cout << "Input error!! Please enter 1 or 2:";
					cin >> choose_text;
				}
				if (choose_text == 1)
				{
					QueryPerformanceFrequency(&fre); 
					QueryPerformanceCounter(&startTime);
					search_file(1, hash, hashFunc);
					QueryPerformanceCounter(&endTime); 
					times = ((double)endTime.QuadPart - (double)startTime.QuadPart) / fre.QuadPart;
					cout << "It takes " << fixed << setprecision(20) << times << " seconds to search." << endl;
				}
				else
				{
					QueryPerformanceFrequency(&fre);
					QueryPerformanceCounter(&startTime);
					search_file(2, hash, hashFunc);
					QueryPerformanceCounter(&endTime);
					times = ((double)endTime.QuadPart - (double)startTime.QuadPart) / fre.QuadPart;
					cout << "It takes " << fixed << setprecision(20) << times << " seconds to search." << endl;
				}
				break;
			}

			case 5:
			{
				ans = false;
				exit(1);
				break;
			}
			default:
			{
				cout << "\nEnter correct option\n";
				break;
			}
		}
	}	while (ans == true);

	system("pause");
	return 0;
}