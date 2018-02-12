package jkt.centre.model;

/**
 * Type de fichier téléchargeable.
 * 
 * @author Erwin
 */
public enum DownloadCategoryEnum {
    MAP(1),         // Fichier 
    MAP_PLAYER(2);
    
    private final int code;
    
    /**
     * Constructor.
     */
    private DownloadCategoryEnum(final int code) {
        this.code = code;
    }
    
    public int getCode() {
        return code;
    }
    
    /**
     * Name getter for JSP usage.
     * 
     * @return this.name()
     */
    public String getName() {
        return this.name();
    }
}
