package com.gabrieldeoliveira.cursospring.services.validation;

public class Validation {
    
    static class CPF {

        public static boolean isValid(String cpf) {
            cpf = getOnlyDigits(cpf);
            if (hasNotThisQuantityOfDigits(cpf, 11)) {
                return false;
            } else if (hasOnlyRepeatedDigitIn(cpf)) {
                return false;
            }
            String givenDigits = cpf.substring(9);
            String calculatedDigits = calcDigits(cpf);
            return givenDigits.equals(calculatedDigits);
        }

        private static String calcDigits(String cpf) {
            String base = getBase(cpf);
            int d1 = getDigit(base, 10);
            int d2 = getDigit(base + d1, 11);
            return String.format("%d%d", d1, d2);
        }

        private static int getDigit(String base, int factor) {
            int baseSum = getBaseSum(base, factor);
            int rest = baseSum * 10 % 11;
            return rest < 10 ? rest : 0;
        }

        private static int getBaseSum(String base, int factor) {
            int baseSum = 0;
            for (char x : base.toCharArray()) {
                baseSum += multiply(x, factor);
                factor--;
            }
            return baseSum;
        }

        private static String getBase(String cpf) {
            if (hasNotThisQuantityOfDigits(cpf, 11)) {
                return null;
            }
            return cpf.substring(0, 9);
        }
    }

    static class CNPJ {

        private static int[] FACTORS_FIRST_DIGIT_ARRAY = { 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };
        private static int[] FACTORS_SECOND_DIGIT_ARRAY = { 6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2 };

        public static boolean isValid(String cnpj) {
            cnpj = getOnlyDigits(cnpj);
            if (hasNotThisQuantityOfDigits(cnpj, 14)) {
                return false;
            } else if (hasOnlyRepeatedDigitIn(cnpj)) {
                return false;
            }
            String givenDigits = cnpj.substring(12);
            String calculatedDigits = calcDigits(cnpj);
            return givenDigits.equals(calculatedDigits);
        }

        private static String calcDigits(String cnpj) {
            String base = cnpj.substring(0, 12);
            int d1 = getDigit(base, FACTORS_FIRST_DIGIT_ARRAY);
            int d2 = getDigit(base + d1, FACTORS_SECOND_DIGIT_ARRAY);
            return String.format("%d%d", d1, d2);
        }

        private static int getDigit(String base, int[] factorsArray) {
            if (base.length() != factorsArray.length) {
                throw new IllegalArgumentException("The base length and factors array length are differents");
            }
            int baseSum = 0;
            for (int i = 0; i < base.length(); i++) {
                baseSum += multiply(base.charAt(i), factorsArray[i]);
            }

            int rest = baseSum % 11;
            return (rest == 0 || rest == 1) ? 0 : 11 - rest;
        }
    }

    private static Integer multiply(char c, int i) {
        return parseInt(c) * i;
    }

    private static Integer parseInt(char c) {
        return Integer.parseInt(String.valueOf(c));
    }

    private static boolean hasOnlyRepeatedDigitIn(String string) {
        int length = string.length();
        String regex = "(\\d)\\1{" + (length - 1) + "}$";
        return string.matches(regex);
    }

    private static boolean hasNotThisQuantityOfDigits(String digits, int quantity) {
        String onlyThisQuantity = "\\d{" + quantity + "}$";
        return !digits.matches(onlyThisQuantity);
    }

    private static String getOnlyDigits(String string) {
        String toReplace = "\\D";
        return string.replaceAll(toReplace, "");
    }
}
