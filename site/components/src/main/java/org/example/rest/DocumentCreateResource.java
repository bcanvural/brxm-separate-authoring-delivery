package org.example.rest;

import org.apache.cxf.validation.ResponseConstraintViolationException;
import org.example.beans.Banner;
import org.example.model.BannerModel;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.annotations.Persistable;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.manager.workflow.WorkflowPersistenceManager;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;

import javax.jcr.RepositoryException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@Path("/create/")
public class DocumentCreateResource extends BaseRestResource {

    @POST
    @Persistable
    @Path("/banner")
    public Response index(@Valid BannerModel bannerModel) throws RepositoryException, ObjectBeanManagerException {

        final HstRequestContext rc = RequestContextProvider.get();
        final WorkflowPersistenceManager wpm = (WorkflowPersistenceManager) getPersistenceManager(rc);
        final String bannerFolderPath = "/" + rc.getSiteContentBasePath() + "/banners";
        final String beanPath = wpm.createAndReturn(bannerFolderPath, "myproject:bannerdocument", bannerModel.getTitle(), true);
        Banner createdBanner = (Banner) wpm.getObject(beanPath);
        createdBanner.setTitle(bannerModel.getTitle());
        wpm.update(createdBanner);
        wpm.save();
        createdBanner = (Banner) wpm.getObject(beanPath); // get it again
        return Response.ok(createdBanner).build(); //return created bean for now
    }

}
