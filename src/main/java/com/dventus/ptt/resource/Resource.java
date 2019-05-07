package com.dventus.ptt.resource;

import com.dventus.ptt.api.PTTRequest;
import com.dventus.ptt.api.PTTResponse;
import com.dventus.ptt.servlet.Context;
import org.apache.log4j.Logger;

public interface Resource {


    static final Logger logger = Logger.getLogger(Resource.class);

    PTTResponse add(PTTRequest request, Context processContext);

}
