////////////////////////////////////////////////////////////////////////////////////////////////////////////
                      // DESIGN LOGGER (Chain Responsibility Pattern)
                      
                      
// --------> Related Question:- ATM / Vending Machine / Design Logger
   

// client --> sends the request to the ( handler/processor/Receiver ) lets Say HPR
// if first HPR doesnt fullfil the request than it goes to the second HPR than third and so on.......


// ---------------------------------------------------------------------------------------------------


///////////////////////////////////////////////////////////////////////////////////////////////////////////

abstract class Logger{

    final static String INFO  = "INFO";
    final static String DEBUG = "DEBUG";
    final static String TRACE = "TRACE";
    abstract void log(String loglevel,String message);
    final Logger nextLogger;
    
    Logger(Logger nextLogger){
        this.nextLogger = nextLogger;
    }
}

class InfoLogger extends Logger{
    
    InfoLogger(Logger nextLogger){
        super(nextLogger);
    }
    
    @Override
    public void log(String loglevel,String message){
        
        if( loglevel.compareTo(Logger.INFO )  == 0 ){
            System.out.println( Logger.INFO + " : " + message);
        }
        else{
            if(nextLogger==null) System.out.println("Logger doesn't exist");
            else                 nextLogger.log(loglevel,message);
        }
    }
}

class DebugLogger extends Logger{
    
    DebugLogger(Logger nextLogger){
        super(nextLogger);
    }
    
    @Override
    public void log(String loglevel,String message){
        
        if( loglevel.compareTo( Logger.DEBUG ) == 0 ){
            System.out.println( Logger.DEBUG + " : " + message);
        }
        else{
            if(nextLogger==null) System.out.println("Logger doesn't exist");
            else                 nextLogger.log(loglevel,message);
        }
    }
}

class TraceLogger extends Logger{
    
    TraceLogger(Logger nextLogger){
         super(nextLogger);
    }
    
    @Override
    public void log(String loglevel,String message){
        
        if( loglevel.compareTo( Logger.TRACE )  == 0 ){
            System.out.println( Logger.TRACE + " : " + message);
        }
        else{
            if(nextLogger==null) System.out.println("Logger doesn't exist");
            else                 nextLogger.log(loglevel,message);
        }
    }
}

public class Main{
    
	public static void main(String[] args) {
		Logger logger = new InfoLogger(new DebugLogger(new TraceLogger(null)));
		logger.log("DUMMY","dummy message");
	}
}
