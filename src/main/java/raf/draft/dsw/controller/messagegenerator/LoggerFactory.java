package raf.draft.dsw.controller.messagegenerator;

import raf.draft.dsw.core.ApplicationFramework;

public class LoggerFactory {
    public static Logger create(String type) {
        Logger logger = null;
        if (type.equalsIgnoreCase("ConsoleLogger")) {
            logger = new ConsoleLogger();
        }
        else if (type.equalsIgnoreCase("FileLogger")){
            logger = new FileLogger();
        }

        return logger;
    }
}
