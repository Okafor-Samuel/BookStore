package com.prunny.bookstore.Util;

public class PriceUtil {
    public static void validatePrices(double startPrice, double endPrice) {
        if (startPrice <= 0) {
            throw new InvalidPriceException("Start price must be greater than zero");
        }
        if (endPrice <= 0) {
            throw new InvalidPriceException("End price must be greater than zero");
        }
        if (startPrice > endPrice) {
            throw new InvalidPriceException("Start price must not be more than end price");
        }
    }

    public static class InvalidPriceException extends RuntimeException {
        public InvalidPriceException(String message) {
            super(message);
        }
    }
}
