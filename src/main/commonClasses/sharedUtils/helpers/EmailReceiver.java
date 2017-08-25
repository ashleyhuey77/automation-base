package commonClasses.sharedUtils.helpers;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import commonClasses.sharedUtils.enums.Protocol;

public class EmailReceiver {
	public ThreadLocal < Properties > property;
    private Set < Properties > properties = Collections.newSetFromMap(new ConcurrentHashMap < > ());

    public EmailReceiver(Protocol protocol, String host, String port, Boolean isSSLRequired) throws Exception {
    	
    	Properties props = new Properties();
    	 
    	String proto = protocol.toString();
        if (protocol.equals(Protocol.smtp)) {
        	props.put("mail.transport.protocol", "smtp");
        	props.put("mail.smtp.auth", "true");
        	props.put("mail.smtp.ssl.enable", "false");
        } else if (protocol.equals(Protocol.imap)) {
        	props.put("mail.store.protocol", "imap");
        }
        props.put(String.format("mail.%s.host", proto), host);
        props.put(String.format("mail.%s.port", proto), port);
        props.put(String.format("mail.%s.starttls.enable", proto), "true");
        
        if (isSSLRequired) {
	        props.setProperty(
	                String.format("mail.%s.socketFactory.class", proto),
	                "javax.net.ssl.SSLSocketFactory");
	        props.setProperty(
	                String.format("mail.%s.socketFactory.fallback", proto),
	                "false");
	        props.setProperty(
	                String.format("mail.%s.socketFactory.port", proto),
	                String.valueOf(port));
        }
 
       property = new InheritableThreadLocal < Properties > () {
            @Override
            protected Properties initialValue() {
            	properties.add(props);
                return props;
            }
        };
    }
}
