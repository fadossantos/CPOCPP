package br.gov.sp.policiamilitar.cpocpp.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import br.gov.sp.policiamilitar.cpocpp.business.entities.Permissoes;
import br.gov.sp.policiamilitar.cpocpp.business.entities.RetornoAD;
import br.gov.sp.policiamilitar.cpocpp.business.entities.repositories.PermissoesRepository;


@Component
public class ADAuthenticationManager implements AuthenticationProvider{

	@Autowired
	PermissoesRepository permissoesDAO;
	
	 @Value("${serverAD}")
	 private String serverAD;

	 @Value("${serverADName}")
	 private String serverADName;
	 
	 
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		String username = auth.getName();
        String password = auth.getCredentials().toString();
        AuxAD ldapContxCrtn = new AuxAD();
	//	RetornoAD retornoAD = ldapContxCrtn.validaUsuarioAD("10.61.236.250", username, password, "cmdo.policiamilitar.sp.gov.br");
		RetornoAD retornoAD = ldapContxCrtn.validaUsuarioAD(serverAD, username, password, serverADName);
		
		if(retornoAD.getMensagem() == AuxAD.SUCESSO)
		{       
			List<Permissoes> listaPermissoes = permissoesDAO.findByCpf(Long.valueOf(username));
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        for(Permissoes perm : listaPermissoes)
        {
        	grantedAuths.add(new SimpleGrantedAuthority(perm.getPermissao()));
        }
        return new UsernamePasswordAuthenticationToken(retornoAD.getPrimeiroNome(), password, grantedAuths);
		}
		else
		{
			throw new AuthenticationCredentialsNotFoundException(retornoAD.getMensagem());
		}
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
