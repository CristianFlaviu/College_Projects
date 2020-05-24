package ro.sd.a2.util;

import ro.sd.a2.entity.Factura;
import ro.sd.a2.entity.SpendingAccount;
import ro.sd.a2.entity.Tranzaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GenerateCSV implements GenerateFile {

    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }


    public void generateFile(List<Tranzaction> tranzactionsList) throws FileNotFoundException {

        List<String[]> dataLines = new ArrayList<>();

        dataLines.add(new String[]{"ID","DATE","ACCOUNT ID ","FACTURA ID"});
        for (Tranzaction tranzaction : tranzactionsList)
            dataLines.add(new String[]{tranzaction.getId(), tranzaction.getDate().toString(), String.valueOf(tranzaction.getGeneralAccount().getId()), tranzaction.getFactura().getId()});

        File csvOutputFile = new File("Transactions.csv");

        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);


        }
    }


}
