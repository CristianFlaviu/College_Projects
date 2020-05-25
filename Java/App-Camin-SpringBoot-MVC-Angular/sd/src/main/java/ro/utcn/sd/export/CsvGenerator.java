package ro.utcn.sd.export;

import ro.utcn.sd.entity.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CsvGenerator implements GenerateFile {

    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }

    @Override
    public void generateFile(List<User> users) throws FileNotFoundException {
        List<String[]> dataLines = new ArrayList<>();
        dataLines.add(new String[]{"No", "firstName", "lastName", "email", "roomNumber"});

        StringBuilder sb = new StringBuilder();

        int index = 0;
        for (User user : users) {
            sb.append(String.valueOf(index++));
            sb.append(user.getFirstName());
            sb.append(user.getLastName());
            sb.append(user.getEmail());
            sb.append(user.getRoom().getRoomNumber());
            sb.append('\n');


        }

        File csvOutputFile = new File("Users.csv");

        PrintWriter pw = new PrintWriter(csvOutputFile);

        pw.write(sb.toString());


    }
}
