package models;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Class that describes the response of an anonymous user.
 */
@Entity
@Table(name = "Response")
public class Response implements Serializable {

	private static final long serialVersionUID = -1196258910301472969L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private int id;
	
    @Column(name = "value", unique = false, nullable = false, length = 200)
    private String value;

	public Response() {
    	
    }
	
    public Response(String value) {
        this.value = value;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Response other = (Response) obj;
		if (id != other.id)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

}