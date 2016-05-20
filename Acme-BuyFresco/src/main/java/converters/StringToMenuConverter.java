/* 
* Autogenerated converter code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import repositories.MenuRepository;
import domain.Menu;

@Component
@Transactional
public class StringToMenuConverter implements Converter<String, Menu> {

	@Autowired
	MenuRepository menuRepository;

	@Override
	public Menu convert(String text) {
		Menu result;
		int id;

		try {
			id = Integer.valueOf(text);
			result = menuRepository.findOne(id);
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
