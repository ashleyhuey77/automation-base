package commonClasses.sharedUtils.builders;

import commonClasses.sharedUtils.enums.Protocol;

public class EmailServer {
	
	private static Protocol protocol;
	private static String host;
    private static String port;

    public Protocol getProtocol() {
        return protocol;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public EmailServer(Protocol protocol, String host, String port) throws Exception {
    	EmailServer.protocol = protocol;
    	EmailServer.host = host;
    	EmailServer.port = port;
    }
}
