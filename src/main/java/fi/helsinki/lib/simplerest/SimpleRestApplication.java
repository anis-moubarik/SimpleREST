/**
 * A RESTful web service on top of DSpace. Copyright (C) 2010-2014 National
 * Library of Finland This program is free software: you can redistribute it
 * and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 *
 */
package fi.helsinki.lib.simplerest;

import org.restlet.Application;
import org.dspace.authenticate.AuthenticationManager;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.routing.Router;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.security.Verifier;
import org.restlet.util.Series;
import org.restlet.data.Parameter;
import org.restlet.ext.crypto.*;

import org.apache.log4j.Logger;
import org.restlet.data.MediaType;

public class SimpleRestApplication extends Application {

    private static Logger log = Logger.getLogger(SimpleRestApplication.class);

    @Override
    public synchronized Restlet createInboundRoot() {
        Router router = new Router(getContext());
        System.setProperty("http.keepAlive", "true");
        getMetadataService().setDefaultMediaType(MediaType.TEXT_HTML);
        router.attach("", RootResource.class);
        router.attach("/rootcommunities", RootCommunitiesResource.class);
        router.attach("/collections", AllCollectionsResource.class);
        router.attach("/items", AllItemsResource.class);
        router.attach("/community/{communityId}", CommunityResource.class);
        router.attach("/community/{communityId}/logo",
                CommunityLogoResource.class);
        router.attach("/community/{communityId}/communities",
                CommunitiesResource.class);
        router.attach("/community/{communityId}/collections",
                CollectionsResource.class);

        router.attach("/collection/{collectionId}", CollectionResource.class);
        router.attach("/collection/{collectionId}/logo",
                CollectionLogoResource.class);
        router.attach("/collection/{collectionId}/items", ItemsResource.class);

        router.attach("/item/{itemId}", ItemResource.class);
        router.attach("/handle/{handle}", HandleResource.class);

        router.attach("/bundle/{bundleId}", BundleResource.class);

        router.attach("/bitstream/{bitstreamIdDotFormat}",
                BitstreamResource.class);

        router.attach("/metadataschemas", MetadataSchemasResource.class);
        router.attach("/metadataschema/{metadataSchemaId}",
                MetadataSchemaResource.class);

        router.attach("/metadatafields", MetadataFieldsResource.class);
        router.attach("/metadatafield/{metadataFieldId}",
                MetadataFieldResource.class);
        router.attach("/users", UsersResource.class);
        router.attach("/user/{userId}", UserResource.class);
        router.attach("/groups", GroupsResource.class);
        router.attach("/group/{groupId}", GroupResource.class);

        Series<Parameter> params = getContext().getParameters();
        String username = params.getFirstValue("username");
        String password = params.getFirstValue("password");

        if (password == null || password.equals("")) {
            log.fatal("No password defined - you have to set it!!!");
            return null;
        }

        // Needs HTTPS!
        ChallengeAuthenticator authenticator = new ChallengeAuthenticator(
                getContext(), ChallengeScheme.HTTP_BASIC,
                "Authentication for SimpleRest");

        Verifier verifier = new MyVerifier();
        authenticator.setVerifier(verifier);
        authenticator.setNext(router);

        return authenticator;
    }
}
