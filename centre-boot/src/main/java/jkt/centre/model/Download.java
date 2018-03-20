package jkt.centre.model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.engine.jdbc.NonContextualLobCreator;

@Entity
public class Download {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DWL_ID")
	private long id;

	@Column(name = "DWL_FILE", nullable = false, length = 50)
	private String file;
	private String description;
	
	@Column(name="DWL_NAME")
	private String name;
	
	@Enumerated(EnumType.STRING)
	@Column(name="DWL_CATEGORY")
	private DownloadCategoryEnum category;
	
	@Column(name="DWL_SIZE")
	private int size;
	
	@Column(name="DWL_VERSION")
	private String version;
	
	@Column(name="DWL_COMPATIBILES")
	private String compatibiles;
	
	@Column(name="DWL_TYPE_MIME")
	private String typeMime;
	
	@Column(name="DWL_CONTENT_FILE")
	private Blob contentFile;


	public long getId() {
		return this.id;
	}

	public void setId(final long id) {
		this.id = id;
	}


	public String getFile() {
		return this.file;
	}

	public void setFichier(final String file) {
		this.file = file;
	}
	
    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	public DownloadCategoryEnum getCategory() {
		return this.category;
	}

	public void setCategory(final DownloadCategoryEnum category) {
		this.category = category;
	}

	public int getSize() {
		return this.size;
	}

	public void setSize(final int size) {
		this.size = size;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(final String version) {
		this.version = version;
	}

	public String getCompatibiles() {
		return this.compatibiles;
	}

	public void setCompatibiles(final String compatibiles) {
		this.compatibiles = compatibiles;
	}

	public String getTypeMime() {
		return this.typeMime;
	}

	public void setTypeMime(final String typeMime) {
		this.typeMime = typeMime;
	}
	
    public Blob getContentFile() {
        return this.contentFile;
    }

    public void setContentFile(final Blob contentFile) {
        this.contentFile = contentFile;
    }
    
    public void setContentFileAsStream(final InputStream contentFileStream, long length) throws IOException {
        this.contentFile = NonContextualLobCreator.INSTANCE.createBlob(contentFileStream, length);
    }
}
