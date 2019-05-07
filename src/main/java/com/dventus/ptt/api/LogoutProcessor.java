package com.dventus.ptt.api;

import com.dventus.ptt.servlet.Context;
import com.dventus.ptt.servlet.ResponseStatus;
import com.dventus.ptt.servlet.StatusMessages;

public class LogoutProcessor extends ProcessorImp {
    public LogoutProcessor(Context context) {
        super(context);
    }

    @Override
    public PTTResponse process(PTTRequest request) {
        return new PTTResponse(ResponseStatus.OK, StatusMessages.LOGOUT_SUCCESS);
    }
}
