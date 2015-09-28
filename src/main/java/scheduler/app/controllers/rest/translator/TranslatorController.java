package scheduler.app.controllers.rest.translator;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/rest/translator" )
public class TranslatorController {

	@RequestMapping( method = RequestMethod.GET, value = "/" )
	public TranslationDTO getDefaultLogin( final TranslationDTO dto ) {

		return new TranslationDTO( Maps.transformValues( dto.getTranslations(), new Function<String, String>() {

			@Override
			public String apply( final String nerd ) {
				return nerd; // TODO: translate
			}
		} ) );
	}
}
