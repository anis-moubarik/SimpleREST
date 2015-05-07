package fi.helsinki.lib.simplerest;

import java.sql.SQLException;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.dspace.authenticate.*;
import org.dspace.core.Context;
import org.dspace.eperson.EPerson;
import org.restlet.Request;
import org.restlet.data.Method;
import org.restlet.security.SecretVerifier;
import org.restlet.security.User;

public class MyVerifier extends SecretVerifier {

    private static Logger log = Logger.getLogger(MyVerifier.class);

    @Override
    public int verify(String identifier, char[] secret) {
        /* Uses the DSpace AuthenticationManager to check for valid credentials,
         successful authentications also put the user's ID into the internal
         list of parameters to be used by getAuthenticatedContext() in
         BaseResource */

        Context context;
        int status;

        try {
            context = new Context();
            status = AuthenticationManager.authenticate(context,
                    identifier, String.valueOf(secret),
                    "Authentication for SimpleRest", null);
        } catch (SQLException e) {
            log.log(Level.INFO, e.getMessage());
            return RESULT_INVALID;
        }

        if (status == AuthenticationMethod.SUCCESS) {
            EPerson ePerson = context.getCurrentUser();
            User user = new User(identifier, secret);
            Request request = Request.getCurrent();
            request.getAttributes().put("currentId", ePerson.getID());

            // Every request goes through the verifier so log it here
            Method method = request.getMethod();
            if (method != Method.GET) {
                log.log(Level.INFO, user.getIdentifier() + ": " + method.toString() + " " + request.getResourceRef().getRemainingPart());
            }

            return RESULT_VALID;
        } else {
            return RESULT_INVALID;
        }
    }
}
