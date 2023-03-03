package utils;

import utils.test_exceptions.InvalidArgumentException;

import java.util.Arrays;

public class CucumberTools {

    public static<T extends Enum<T>> T convertQuotedParameterToEnum(String stringValue, Class<T> enumClass) {
        stringValue = stringValue.substring(1, stringValue.length() - 1); // Remove outer " " from argument
        stringValue = stringValue.toUpperCase();
        try {
            return Enum.valueOf(enumClass, stringValue);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException(
                    String.format("%s is an invalid value for the %s parameter. Choose from: %s"
                            , stringValue
                            , enumClass.getSimpleName()
                            , Arrays.toString(enumClass.getEnumConstants())), e);
        }
    }

    public static<T extends Enum<T>> T convertParameterToEnumWithPlurals(String stringValue, Class<T> enumClass) {
        stringValue = stringValue.toUpperCase();
        stringValue = Utils.convertToSingular(stringValue);

        try {
            return Enum.valueOf(enumClass, stringValue);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException(
                    String.format("%s is an invalid value for the %s parameter. Choose from: %s, and the plural forms."
                            , stringValue
                            , enumClass.getSimpleName()
                            , Arrays.toString(enumClass.getEnumConstants())), e);
        }
    }

    public static<T extends Enum<T>> T convertParameterToEnum(String stringValue, Class<T> enumClass) {
        stringValue = stringValue.toUpperCase();
        try {
            return Enum.valueOf(enumClass, stringValue);
        } catch (IllegalArgumentException e) {
            throw new InvalidArgumentException(
                    String.format("%s is an invalid value for the %s parameter. Choose from: %s"
                            , stringValue
                            , enumClass.getSimpleName()
                            , Arrays.toString(enumClass.getEnumConstants())), e);
        }
    }

}
