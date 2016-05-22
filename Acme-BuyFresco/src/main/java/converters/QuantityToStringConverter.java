/* 
* Autogenerated converter code 
* Variables (text between %) must have been replaced when code is autogenerated
*/
package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Quantity;

@Component
@Transactional
public class QuantityToStringConverter implements Converter<Quantity, String> {

	@Override
	public String convert(Quantity entity) {
		String result;

		if (entity == null)
			result = null;
		else
			result = String.valueOf(entity.getId());

		return result;
	}

}