package ro.utcn.sd.export;

import ro.utcn.sd.entity.User;

import java.io.FileNotFoundException;
import java.util.List;

public interface GenerateFile {

    public void generateFile(List<User> users) throws FileNotFoundException;


}
