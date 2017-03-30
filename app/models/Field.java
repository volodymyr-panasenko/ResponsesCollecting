package models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.*;

/**
 * Class that describes Field.
 */
@Entity
@Table(name = "Field")
public class Field implements Serializable {

	private static final long serialVersionUID = -4374133251628574903L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "field_id")
    private int id;
	
    @Column(name = "label", unique = true, nullable = false, length = 200)
    private String label;

    @Column(name = "type", unique = false, nullable = false)
	@Enumerated(EnumType.STRING)
	private FieldType type;
    
    @Column(name = "required", nullable = false)
    private boolean isRequired;
    
    @Column(name = "active", nullable = false)
    private boolean isActive;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "field_options", joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "option_id"))
    private Collection<Option> options = new ArrayList<>();
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "field_responses", joinColumns = @JoinColumn(name = "field_id"),
            inverseJoinColumns = @JoinColumn(name = "response_id"))
    private Collection<Response> responses = new ArrayList<>();

	public Field() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public boolean getRequired() {
		return isRequired;
	}

	public void setRequired(boolean isRequired) {
		this.isRequired = isRequired;
	}

	public boolean getActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public Collection<Option> getOptions() {
		return options;
	}

	public void setOptions(Collection<Option> options) {
		this.options = options;
	}
	
	public Collection<Response> getResponses() {
		return responses;
	}

	public void setResponses(Collection<Response> responses) {
		this.responses = responses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + (isActive ? 1231 : 1237);
		result = prime * result + (isRequired ? 1231 : 1237);
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((options == null) ? 0 : options.hashCode());
		result = prime * result + ((responses == null) ? 0 : responses.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Field other = (Field) obj;
		if (id != other.id)
			return false;
		if (isActive != other.isActive)
			return false;
		if (isRequired != other.isRequired)
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (options == null) {
			if (other.options != null)
				return false;
		} else if (!options.equals(other.options))
			return false;
		if (responses == null) {
			if (other.responses != null)
				return false;
		} else if (!responses.equals(other.responses))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
