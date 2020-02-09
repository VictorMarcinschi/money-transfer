package transfer.testutil;

import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Component;
import transfer.rest.RestModule;

@Component(modules = RestModule.class)
public interface ObjectMapperFactory {

    ObjectMapper mapper();
}
