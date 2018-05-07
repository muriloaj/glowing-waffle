package br.com.boletoapi.commons;

import java.util.UUID;

public interface UUIDProvider {
    
    UUID getUUID();
    
    UUID fromString(String uuid);
    
    boolean isValid(String uuid);
    
}
