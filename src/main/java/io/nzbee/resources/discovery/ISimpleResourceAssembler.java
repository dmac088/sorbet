package io.nzbee.resources.discovery;

import java.util.Map;

public interface ISimpleResourceAssembler<T> {

	T toModel(Map<String, ?> m);

}
