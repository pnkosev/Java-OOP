package pr05_telephony;

import java.util.List;
import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
        StringBuilder sb = new StringBuilder();

        for (String url : this.urls) {
            if (!url.matches(".*\\d.*")) {
                sb.append("Browsing: ")
                        .append(url)
                        .append("!");
            } else {
                sb.append("Invalid URL!");
            }

            sb.append(System.lineSeparator());
        }
        return sb.toString().trim();
    }

    @Override
    public String call() {
        StringBuilder sb = new StringBuilder();


        for (String number : this.numbers) {
            if (!number.matches("[0-9]+")) {
                sb.append("Invalid number!");
            } else {
                sb.append("Calling... ")
                        .append(number);
            }
            sb.append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
