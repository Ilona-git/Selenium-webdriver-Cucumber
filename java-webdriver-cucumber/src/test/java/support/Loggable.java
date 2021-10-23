package support;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggable {

  default Logger getLogger(){
        return LogManager.getLogger(this.getClass());
    }
}
