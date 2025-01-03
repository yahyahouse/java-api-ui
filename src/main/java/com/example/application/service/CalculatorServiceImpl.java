package com.example.application.service;

import com.example.application.model.ResultPossibility;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Override
    public ResultPossibility calculatePossibleCombinations(String input) {
        ResultPossibility resultPossibility = new ResultPossibility();
        int n = input.length();
        int base = 2;
        resultPossibility.setResult((int) Math.pow(base, n - 1));

        List<String> combinations = new ArrayList<>();
        for (int i = 0; i < resultPossibility.getResult(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(input.charAt(j));
                if (j < n - 1 && (i & (1 << j)) != 0) {
                    sb.append('.');
                }
            }
            combinations.add(sb.toString());
        }
        resultPossibility.setPossibilities(combinations);
        return resultPossibility;
    }

    @Override
    public int add(int a, int b) {
        return 0;
    }

    @Override
    public int subtract(int a, int b) {
        return 0;
    }

    @Override
    public int multiply(int a, int b) {
        return 0;
    }

    @Override
    public int divide(int a, int b) {
        return 0;
    }

    @Override
    public int modulo(int a, int b) {
        return 0;
    }

    @Override
    public int power(int a, int b) {
        return 0;
    }

    @Override
    public int factorial(int a) {
        return 0;
    }

    @Override
    public int permutation(int n, int r) {
        return 0;
    }

    @Override
    public int combination(int n, int r) {
        return 0;
    }

    @Override
    public int gcd(int a, int b) {
        return 0;
    }

    @Override
    public int lcm(int a, int b) {
        return 0;
    }

    @Override
    public int log(int a, int b) {
        return 0;
    }

    @Override
    public int sqrt(int a) {
        return 0;
    }

    @Override
    public int cbrt(int a) {
        return 0;
    }

    @Override
    public int abs(int a) {
        return 0;
    }

    @Override
    public int ceil(double a) {
        return 0;
    }

    @Override
    public int floor(double a) {
        return 0;
    }

    @Override
    public int round(double a) {
        return 0;
    }

    @Override
    public int max(int a, int b) {
        return 0;
    }

    @Override
    public int min(int a, int b) {
        return 0;
    }

    @Override
    public int random(int a, int b) {
        return 0;
    }

    @Override
    public int sum(int[] a) {
        return 0;
    }

    @Override
    public int mean(int[] a) {
        return 0;
    }

    @Override
    public int median(int[] a) {
        return 0;
    }

    @Override
    public int mode(int[] a) {
        return 0;
    }

    @Override
    public int range(int[] a) {
        return 0;
    }

    @Override
    public int variance(int[] a) {
        return 0;
    }

    @Override
    public int standardDeviation(int[] a) {
        return 0;
    }

    @Override
    public int correlation(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int covariance(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int leastSquaresRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int exponentialRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int logarithmicRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int powerRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int quadraticRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int cubicRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int polynomialRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int linearInterpolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int quadraticInterpolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int cubicInterpolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int polynomialInterpolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int linearExtrapolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int quadraticExtrapolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int cubicExtrapolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int polynomialExtrapolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int linearRegression(int[] a, int[] b) {
        return 0;
    }

    @Override
    public int interpolation(int[] a, int[] b, int c) {
        return 0;
    }

    @Override
    public int extrapolation(int[] a, int[] b, int c) {
        return 0;
    }
}
