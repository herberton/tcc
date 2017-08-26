package br.com.herberton.tcc.puc.poc.business;

import static org.apache.commons.lang3.StringUtils.defaultString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

import br.com.herberton.tcc.puc.poc.business.contract.ILoginBusiness;
import br.com.herberton.tcc.puc.poc.dto.UserDTO;

@Service
public class LoginBusiness implements ILoginBusiness {
	
	@Autowired
	private CacheManager cacheManager;
	
	
	@Override
	public boolean login(String jSessionId, UserDTO user) {
		
		if(jSessionId == null) {
			return false;
		}
		
		Cache cache = this.getCache();
		
		String cacheKey = this.getCacheKey(jSessionId);
		
		if(cache.get(cacheKey) != null) {
			return true;
		}
		
		if(defaultString(user.getLogin()).equals("admin") 
				&& defaultString(user.getPassword()).equals("admin")) {
			cache.put(cacheKey, user);
			return true;
		}
		
		cache.evict(cacheKey);
		return false;
		
	}
	
	@Override
	public UserDTO getLoggedUser(String jSessionId) {
		
		if(jSessionId == null) {
			return null;
		}
		
		Cache cache = this.getCache();
		
		String cacheKey = this.getCacheKey(jSessionId);
		
		return cache.get(cacheKey, UserDTO.class);
		
	}
	
	@Override
	public void removeLoggedUser(String jSessionId) {

		if(jSessionId == null) {
			return;
		}
		
		Cache cache = this.getCache();
		
		String cacheKey = this.getCacheKey(jSessionId);
		
		cache.evict(cacheKey);

	}
	
	
	private Cache getCache() {
		return cacheManager.getCache("MyCache");
	}
	
	private String getCacheKey(String jSessionId) {
		return "JSESSIONID="+jSessionId;
	}
	
}
