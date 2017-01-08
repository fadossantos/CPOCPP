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
package br.gov.sp.policiamilitar.cpocpp.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.policiamilitar.cpocpp.business.entities.ProcessoCPOCPP;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.ProcessoCPOCPPRepository;

@Service
public class ProcessoCPOCPPService {
    
    @Autowired
    private ProcessoCPOCPPRepository processoCPOCPPRepository; 
  
    public ProcessoCPOCPPService() {
        super();
    }  
        
    public Iterable<ProcessoCPOCPP> findAll() {
        return this.processoCPOCPPRepository.findAll();
    }
    
    public Iterable<ProcessoCPOCPP> findByNomeInteressado(String nomeInteressado) {
        return this.processoCPOCPPRepository.findByNomeInteressado(nomeInteressado);
    }
    
    public Iterable<ProcessoCPOCPP> findByReInteressado(Long ReInteressado) {
        return this.processoCPOCPPRepository.findByReInteressado(ReInteressado);
    }
    
    
    public Iterable<ProcessoCPOCPP> findByIdSecCom(Long idSecCom) {
        return this.processoCPOCPPRepository.findByIdSecCom(idSecCom);
    }
    
    
    public Iterable<ProcessoCPOCPP> findByIdStatus(Long idStatus) {
        return this.processoCPOCPPRepository.findByIdStatus(idStatus);
    }
    
    
    public ProcessoCPOCPP findOne(Long id)
    {
    	return this.processoCPOCPPRepository.findOne(id);
    }

    public void addOrUpdate(final ProcessoCPOCPP processoCPOCPP) {
          this.processoCPOCPPRepository.save(processoCPOCPP);
    }
    
    public void remove(final Long id){
    	this.processoCPOCPPRepository.delete(id);
    }
    
}
