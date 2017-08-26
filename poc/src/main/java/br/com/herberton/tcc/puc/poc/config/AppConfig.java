package br.com.herberton.tcc.puc.poc.config;

import static org.apache.commons.lang3.StringUtils.equalsAny;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import br.com.herberton.tcc.puc.poc.helper.contract.INetworkHelper;

@Configuration
@EnableCaching
@ImportResource("/WEB-INF/applicationContext.xml")
public class AppConfig extends WebMvcConfigurerAdapter {

	@Autowired
	private INetworkHelper networkHelper;
	
	@Bean
	public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() throws Exception {
		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
		
		String ehCacheConfigFile = networkHelper.getNetworkAddress();
		
		if(equalsAny(ehCacheConfigFile, "10.128.0.2", "10.128.0.3")) {
			ehCacheConfigFile += ".xml";
		} else {
			ehCacheConfigFile = "dev.xml";
		}
		
		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("/ehcache/" + ehCacheConfigFile));
		
		ehCacheManagerFactoryBean.setShared(true);
		return ehCacheManagerFactoryBean;
	}

	@Bean
	public CacheManager ehCacheCacheManager() throws Exception {
		return new EhCacheCacheManager(ehCacheManagerFactoryBean().getObject());
	}

}