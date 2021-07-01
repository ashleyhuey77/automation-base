package com.warnermedia.mira;

import com.warnermedia.config.SHelper;
import com.warnermedia.config.TestException;
import com.warnermedia.config.report.LocalReport;
import com.warnermedia.mira.web.GlobalData;
import com.warnermedia.mira.web.MiraGlobalNav;
import com.warnermedia.mira.web.inputs.MiraRecordDetails;
import com.warnermedia.selenium.shared.Via;
import com.warnermedia.selenium.text.Variable;

public class MiraRecordDetailsComp extends AbstractMiraPage {

    public MiraRecordDetailsComp() {

    }

    public MiraRecordDetailsComp getTheRecordId() throws TestException {
        try {
            switchToMediaTaskArea();
            String value = SHelper.get().text(Variable.ELEMENT, Via.SELENIUM).getFrom(MiraRecordDetails.RECORD_ID.element());
            GlobalData.setRecordId(value.trim());
        } catch (Exception e) {
            throw LocalReport.getReport().reportException(e);
        }
        return this;
    }

}
