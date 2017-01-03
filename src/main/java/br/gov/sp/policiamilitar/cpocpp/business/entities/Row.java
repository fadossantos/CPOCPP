package br.gov.sp.policiamilitar.cpocpp.business.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="Row.findAll", query="SELECT a FROM Row a")
public class Row {

	


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "Variety_id")
    private Variety variety = null;
    
    private Integer seedsPerCell = null;
    
    
    
    
    public Row() {
        super();
    }


    public Variety getVariety() {
        return this.variety;
    }


    public void setVariety(final Variety variety) {
        this.variety = variety;
    }

    public Integer getSeedsPerCell() {
        return this.seedsPerCell;
    }

    public void setSeedsPerCell(final Integer seedsPerCell) {
        this.seedsPerCell = seedsPerCell;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    @Override
    public String toString() {
        return "Row [variety=" + this.variety + ", seedsPerCell=" + this.seedsPerCell + "]";
    }

    
}
