package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    NumberWorker numberWorker = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 5, 19, 3637, Integer.MAX_VALUE})
    void isPrimeForPrimes(int number){
        assertTrue(numberWorker.isPrime(number));
    }


    @ParameterizedTest
    @ValueSource(ints = {4, 9, 1000, 6211 * 19, 31110899, 69103})
    void isPrimeForNotPrimes(int number){
        assertFalse(numberWorker.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-19, 0, 1, -4})
    void isPrimeForIncorrectNumbers(int number){
        assertThrows(IllegalArgumentException.class, () -> {
            numberWorker.isPrime(number);
        });
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void digitsSumTest(int number, int expected){
        assertEquals(expected, numberWorker.digitsSum(number));
    }


}