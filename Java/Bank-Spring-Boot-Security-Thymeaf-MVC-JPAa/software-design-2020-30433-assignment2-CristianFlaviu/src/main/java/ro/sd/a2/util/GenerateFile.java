package ro.sd.a2.util;

import ro.sd.a2.entity.Tranzaction;

import java.io.FileNotFoundException;
import java.util.List;

public interface GenerateFile {

    public void generateFile(List<Tranzaction> tranzactions) throws FileNotFoundException;
}
