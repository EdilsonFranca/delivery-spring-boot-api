package br.com.delivery.app.Security;
import br.com.delivery.app.Model.User;
import br.com.delivery.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(email);
		if (user == null){
			 user = userRepository.findByName(email)
					.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com nome : " + email));
		}

		return UserDetailsImpl.build(user);
	}

}