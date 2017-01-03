/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package br.gov.sp.policiamilitar.cpocpp.business.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQuery(name="SeedStarter.findAll", query="SELECT a FROM SeedStarter a")
public class SeedStarter {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id = null;
    private Date datePlanted = null;
    private Boolean covered = null;
    @Enumerated(EnumType.STRING)
    private Type type = Type.PLASTIC;
    @ElementCollection(targetClass = Feature.class)
    @JoinTable(name = "SeedStarter_Feature", joinColumns = @JoinColumn(name = "SeedStarter_ID"))
    @Enumerated(EnumType.STRING)
    private List<Feature> features = null;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="SedStarter_HAS_Rows", joinColumns={@JoinColumn(name="SeedStarter_ID", referencedColumnName="id")}, inverseJoinColumns={@JoinColumn(name="Row_ID", referencedColumnName="id")})
    private List<Row> rows = new ArrayList<Row>();
    
    
    public SeedStarter() {
        super();
    }


    public Long getId() {
        return this.id;
    }


    public void setId(final Long id) {
        this.id = id;
    }


    public Date getDatePlanted() {
        return this.datePlanted;
    }


    public void setDatePlanted(final Date datePlanted) {
        this.datePlanted = datePlanted;
    }


    public Boolean getCovered() {
        return this.covered;
    }


    public void setCovered(final Boolean covered) {
        this.covered = covered;
    }

    public Type getType() {
        return this.type;
    }


    public void setType(final Type type) {
        this.type = type;
    }

    public List<Feature> getFeatures() {
        return this.features;
    }


    public void setFeatures(final List<Feature> features) {
        this.features = features;
    }


    public List<Row> getRows() {
        return this.rows;
    }


    @Override
    public String toString() {
        return "SeedStarter [id=" + this.id + ", datePlanted=" + this.datePlanted
                + ", covered=" + this.covered + ", type=" + this.type + ", features="
                + this.features.toString() + ", rows=" + this.rows + "]";
    }

}
