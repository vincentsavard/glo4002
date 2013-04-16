package ca.ulaval.glo4002.centralServer.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

import ca.ulaval.glo4002.centralServer.communication.Communicator;

import com.sun.jersey.core.spi.component.ComponentContext;
import com.sun.jersey.spi.inject.Injectable;
import com.sun.jersey.spi.inject.PerRequestTypeInjectableProvider;

@Provider
public class CommunicatorProvider extends PerRequestTypeInjectableProvider<Context, Communicator> {

    private Communicator communicator = new Communicator();

    public CommunicatorProvider() {
        super(Communicator.class);
    }

    @Override
    public Injectable<Communicator> getInjectable(ComponentContext injectableContexte, Context annotationInstance) {
        return new Injectable<Communicator>() {

            @Override
            public Communicator getValue() {
                return communicator;
            }
        };
    }

}
