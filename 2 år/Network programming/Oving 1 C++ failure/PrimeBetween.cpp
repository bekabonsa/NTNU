#include <iostream>
#include <vector>
#include <cmath>
#include <thread>
#include <atomic>
#include <set>
#include <chrono>
#include <mutex>

using namespace std;

set<int> primes;
mutex mtx;
atomic<int> next_prime(2);

void sieveOfEratosthenes(int start, int end) {
    int n = end;
    // Create a boolean array "prime[start..end]" and initialize all entries it as true.
    vector<bool> prime(n - start + 1, true);
    int upperLimit = static_cast<int>(sqrt(n)) + 1;

    int current_prime;
    while ((current_prime = next_prime++) < upperLimit) {
        if (current_prime >= start) {
            for (int j = max(current_prime*current_prime, (start / current_prime) * current_prime); j <= n; j += current_prime) {
                prime[j - start] = false;
            }
        }
    }

    // Add the primes to a list
    for (int i = max(start, 2); i <= n; i++) {
        if (prime[i - start]) {
            lock_guard<mutex> lock(mtx);
            primes.insert(i);
        }
    }
}

int main() {
    int start, end, numThreads;
    cout << "Enter the first number: ";
    cin >> start;
    cout << "Enter the last number: ";
    cin >> end;
    cout << "Enter the number of threads: ";
    cin >> numThreads;

    auto startTime = chrono::high_resolution_clock::now();

    int chunkSize = (end - start) / numThreads;
    int startIndex = start;
    int endIndex = startIndex + chunkSize;
    vector<thread> threads;

    for (int i = 0; i < numThreads; i++) {
        threads.emplace_back(sieveOfEratosthenes, startIndex, endIndex);
        startIndex = endIndex;
        endIndex += chunkSize;
    }

    for (auto &thread : threads) {
        thread.join();
    }

    cout << "Prime numbers between " << start << " and " << end << ": ";
    for (int prime : primes) {
        cout << prime << " ";
    }
    cout << endl;

    auto stopTime = chrono::high_resolution_clock::now();
    auto duration = chrono::duration_cast<chrono::milliseconds>(stopTime - startTime);
    cout << "Time elapsed: " << duration.count() << " ms" << endl;

    return 0;
}