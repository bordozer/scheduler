package scheduler.web.controllers.rest.translator;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/translator")
public class TranslatorController {

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public TranslationDTO getDefaultLogin(final TranslationDTO dto) {
		return new TranslationDTO(Maps.transformValues(dto.getTranslations(), this::translate));
	}

	private String translate(final String nerd) {
		return nerd; // TODO: translate
	}
}
