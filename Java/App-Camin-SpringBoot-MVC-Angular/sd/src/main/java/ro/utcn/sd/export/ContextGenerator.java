package ro.utcn.sd.export;

import ro.utcn.sd.controller.UserController;
import ro.utcn.sd.entity.User;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.logging.Logger;

public class ContextGenerator {

    private final static Logger LOGGER = Logger.getLogger(ContextGenerator.class.getName());

    private GenerateFile generateFile;

    public ContextGenerator(GenerateFile generateFile)
    {
        this.generateFile=generateFile;
    }

    public void executeGenerateFile(List<User> userList)
    {
        try {
            generateFile.generateFile(userList);
            LOGGER.info("new PDF generated");
        } catch (FileNotFoundException e) {
            LOGGER.severe("error generating the file");
        }
    }
}
