package guru.qa.data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum ModalWindowOptions {
    YES("Да"),
    NO("Нет");

    private final String value;

    ModalWindowOptions(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static List<String> getOptions() {
        return Arrays.stream(values()).map(ModalWindowOptions::getValue).collect(Collectors.toList());
    }
}
