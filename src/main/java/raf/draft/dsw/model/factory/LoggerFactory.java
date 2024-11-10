package raf.draft.dsw.model.factory;

import raf.draft.dsw.controller.logger.ConsoleLogger;
import raf.draft.dsw.controller.logger.FileLogger;
import raf.draft.dsw.model.logger.Logger;

public class LoggerFactory {
    public static Logger create(String type) {
        Logger logger = null;
        if (type.equalsIgnoreCase("ConsoleLogger")) {
            logger = new ConsoleLogger();
        } else if (type.equalsIgnoreCase("FileLogger")) {
            logger = new FileLogger();
        }

        return logger;
    }
}
