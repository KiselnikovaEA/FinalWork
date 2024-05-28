import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    private static final int fields_number = 6;

    public static void main(String[] args) {

        System.out.println("Введите данные в виде: Фамилия Имя Отчество ДатаРождения НомерТелефона Пол");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        String[] fields = input.split(" ");
        if (fields.length != fields_number) {
            System.err.println("Вы ввели неверное количество полей. Их " + fields.length + ", а нужно " + fields_number);
            return;
        }

        String lastName = fields[0];
        String firstName = fields[1];
        String middleName = fields[2];

        LocalDate birthDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            birthDate = LocalDate.parse(fields[3], formatter);
        }
        catch (DateTimeParseException e) {
            System.err.println("Неверный формат даты. Правильный формат: dd.MM.yyyy");
            return;
        }

        long phoneNumber;
        try {
            phoneNumber = Long.parseLong(fields[4]);
        }
        catch (NumberFormatException e) {
            System.err.println("Неверный формат номера телефона. Нужно вводить только цифры. Например: 89995557733");
            return;
        }

        String gender = fields[5];
        if (!"m".equals(gender) && !"f".equals(gender)) {
            System.err.println("Неверный формат пола. Введите f или m");
            return;
        }

        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(lastName + " " + firstName + " " + middleName + " " + birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE) + " " + phoneNumber + " " + gender);
            writer.newLine();
        }
        catch (IOException e) {
            System.err.println("Ошибка записи!");
        }
    }
}