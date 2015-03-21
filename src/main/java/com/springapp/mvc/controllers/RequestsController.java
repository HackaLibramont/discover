package com.springapp.mvc.controllers;

import com.springapp.mvc.dao.AbstractDAO;
import com.springapp.mvc.dao.ActivityDAO;
import com.springapp.mvc.dao.data.Filter;
import com.springapp.mvc.data.activity.*;
import com.springapp.mvc.data.utils.ActivitiesRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
public class RequestsController {
    @RequestMapping(value = "activities" , method = RequestMethod.GET)
    public @ResponseBody List<Activity> getActivities(@RequestBody ActivitiesRequest request){

        /*List<Activity> activities = new ArrayList<Activity>();
        Location loc = new Location(2., 3.);
        Contact contact = new Contact();
        activities.add(new Place(1L, "Visite d'un truc", loc, contact, 2., 3., "bite"));
        activities.add(new Place(2L, "Visite d'un machin", loc, contact, 2., 3., "couille"));
        activities.add(new Leisure(4L, "Restaurant", loc, contact,  2., 3.,new Timestamp(1891518874L), new Timestamp(1891528874L), "ceci est une url"));
        */

        double ratio = 1./111.;
        double halfSideKm = ((double)request.getMaxTravelDistance())/1000;
        double halfSideCoord = halfSideKm * ratio;

        Filter filter = new Filter();
        filter.addQuadriFilter(((double)request.getLongitude())-halfSideCoord, ((double)request.getLatitude())-halfSideCoord, ((double)request.getLongitude())+halfSideCoord, ((double)request.getLatitude())+halfSideCoord);

        ActivityDAO dao = new ActivityDAO();
        return dao.filter(filter, AbstractDAO.LANG.FR);
    }
}
