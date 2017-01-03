package br.gov.sp.policiamilitar.cpocpp.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Variety.findAll", query="SELECT a FROM Variety a")
public class Variety {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = null;
    private String name = null;
    
    
    public Variety() {
        super();
    }


    public Long getId() {
        return this.id;
    }
    public void setId(final Long id) {
        this.id = id;
    }


    public String getName() {
        return this.name;
    }
    public void setName(final String name) {
        this.name = name;
    }
    
}
